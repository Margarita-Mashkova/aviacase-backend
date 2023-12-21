package com.example.aviacase.service;

import com.example.aviacase.dto.ReportTourPurchasesDto;
import com.example.aviacase.model.Hotel;
import com.example.aviacase.model.Purchase;
import com.example.aviacase.model.Tour;
import com.example.aviacase.model.User;
import com.example.aviacase.repository.PurchaseRepository;
import com.example.aviacase.service.exception.HotelNotFoundException;
import com.example.aviacase.service.exception.PurchaseNotFoundException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TourService tourService;

    @Autowired
    private HotelService hotelService;

    @Transactional(readOnly = true)
    public List<Purchase> findAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Purchase findPurchase(Long id){
        final Optional<Purchase> purchase = purchaseRepository.findById(id);
        return purchase.orElseThrow(() -> new PurchaseNotFoundException(id));
    }

    @Transactional
    public Purchase makePurchase(int nights, int tourists, Long userId, Long tourId, Long hotelId){
        final User user = userService.findUser(userId);
        final Tour tour = tourService.findTour(tourId);
        final Hotel hotel = hotelService.findHotel(hotelId);
        Date date = new Date();
        float sum = (tour.getPrice() + hotel.getPrice()) * nights * tourists;
        Purchase purchase = new Purchase(date, nights, tourists, sum, hotel, user, tour);
        return purchaseRepository.save(purchase);
    }

    @Transactional
    public Purchase editPurchase(Long id, int nights, int tourists, Long userId, Long tourId, Long hotelId){
        Purchase purchase = findPurchase(id);
        final User user = userService.findUser(userId);
        final Tour tour = tourService.findTour(tourId);
        final Hotel hotel = hotelService.findHotel(hotelId);
        float sum = (tour.getPrice() + hotel.getPrice()) * nights * tourists;
        purchase.setNights(nights);
        purchase.setTourists(tourists);
        purchase.setSum(sum);
        purchase.setHotel(hotel);
        purchase.setUser(user);
        purchase.setTour(tour);
        return purchaseRepository.save(purchase);
    }

    @Transactional
    public List<Purchase> getUserPurchases(User user){
        return purchaseRepository.findAllByUserOrderByDateDesc(user);
    }

    @Transactional
    public List<ReportTourPurchasesDto> makeTourPurchasesReport(Date dateStart, Date dateEnd){
        List<ReportTourPurchasesDto> reportData = new ArrayList<>();
        // Покупки туров за определенный период
        List<Purchase> spanPurchases = purchaseRepository.findAllByDateBetween(dateStart, dateEnd);
        // Подсчет кол-ва проданных туров (Название тура, Кол-во продаж)
        Map<String, Integer> toursCount = new HashMap<>();
        // Подсчет суммы выручки за покупки определенного тура
        Map<String, Float> toursSum = new HashMap<>();
        for (Purchase purchase : spanPurchases) {
            Tour tour = tourService.findTour(purchase.getTour().getId());
            String tourName = tour.getName();
            if(!toursCount.containsKey(tourName)){
                toursCount.put(tourName, 1);
                toursSum.put(tourName, purchase.getSum());
            }else {
                int countTours = toursCount.get(tourName);
                float sumTours = toursSum.get(tourName);
                toursCount.put(tourName, countTours + 1);
                toursSum.put(tourName, sumTours + purchase.getSum());
            }
        }

        int amountAll = 0;
        float sumAll = 0;
        for (String tourName: toursCount.keySet()) {
            ReportTourPurchasesDto reportLine = new ReportTourPurchasesDto();
            reportLine.setTourName(tourName);
            reportLine.setAmountPurchases(toursCount.get(tourName));
            amountAll += toursCount.get(tourName);
            reportLine.setSum(toursSum.get(tourName));
            sumAll += toursSum.get(tourName);
            reportData.add(reportLine);
        }

        ReportTourPurchasesDto reportLineAll = new ReportTourPurchasesDto();
        reportLineAll.setTourName("Итого");
        reportLineAll.setAmountPurchases(amountAll);
        reportLineAll.setSum(sumAll);
        reportData.add(reportLineAll);

        return reportData;
    }

    @Transactional
    public void saveReportExcel(Date dateStart, Date dateEnd) {
        List<ReportTourPurchasesDto> reportData = makeTourPurchasesReport(dateStart, dateEnd);
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Отчет о покупках туров");

        // Styles
        // Cell bold font
        Font font = book.createFont();
        font.setBold(true);
        CellStyle styleBold = book.createCellStyle();
        styleBold.setFont(font);
        // Cell bold font and borders and color background
        CellStyle styleBorderAndBold = book.createCellStyle();
        styleBorderAndBold.setBorderBottom(BorderStyle.THIN);
        styleBorderAndBold.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styleBorderAndBold.setBorderLeft(BorderStyle.THIN);
        styleBorderAndBold.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        styleBorderAndBold.setBorderRight(BorderStyle.THIN);
        styleBorderAndBold.setRightBorderColor(IndexedColors.BLACK.getIndex());
        styleBorderAndBold.setBorderTop(BorderStyle.THIN);
        styleBorderAndBold.setTopBorderColor(IndexedColors.BLACK.getIndex());
        styleBorderAndBold.setFont(font);
        styleBorderAndBold.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        styleBorderAndBold.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //Cell borders
        CellStyle styleBorder = book.createCellStyle();
        styleBorder.setBorderBottom(BorderStyle.THIN);
        styleBorder.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styleBorder.setBorderLeft(BorderStyle.THIN);
        styleBorder.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        styleBorder.setBorderRight(BorderStyle.THIN);
        styleBorder.setRightBorderColor(IndexedColors.BLACK.getIndex());
        styleBorder.setBorderTop(BorderStyle.THIN);
        styleBorder.setTopBorderColor(IndexedColors.BLACK.getIndex());

        //Заголовок
        Row rowTitle = sheet.createRow(0);
        Cell cellTitle = rowTitle.createCell(0);
        cellTitle.setCellValue("Покупки туров");
        cellTitle.setCellStyle(styleBold);

        //Заголовки таблицы
        String[] tableTitles = {"Наименование тура", "Кол-во продаж", "Выручка"};
        Row rowTitlesTable = sheet.createRow(2);
        for (int i = 0; i < 3; i++) {
            Cell titleTableCell = rowTitlesTable.createCell(i);
            titleTableCell.setCellValue(tableTitles[i]);
            titleTableCell.setCellStyle(styleBorderAndBold);
        }

        //Данные покупок туров
        int rowIndex = 3;
        ReportTourPurchasesDto lineSummary = reportData.get(reportData.size()-1);
        reportData.remove(lineSummary);
        for (ReportTourPurchasesDto reportLine : reportData) {
            Row rowTour = sheet.createRow(rowIndex);

            Cell cellTourName = rowTour.createCell(0);
            cellTourName.setCellValue(reportLine.getTourName());
            cellTourName.setCellStyle(styleBorder);

            Cell cellAmountPurchases = rowTour.createCell(1);
            cellAmountPurchases.setCellValue(reportLine.getAmountPurchases());
            cellAmountPurchases.setCellStyle(styleBorder);

            Cell cellSum = rowTour.createCell(2);
            cellSum.setCellValue(reportLine.getSum());
            cellSum.setCellStyle(styleBorder);

            rowIndex++;
        }

        Row rowSummary = sheet.createRow(rowIndex);

        Cell cellTourName = rowSummary.createCell(0);
        cellTourName.setCellValue(lineSummary.getTourName());
        cellTourName.setCellStyle(styleBorderAndBold);

        Cell cellAmountPurchases = rowSummary.createCell(1);
        cellAmountPurchases.setCellValue(lineSummary.getAmountPurchases());
        cellAmountPurchases.setCellStyle(styleBorderAndBold);

        Cell cellSum = rowSummary.createCell(2);
        cellSum.setCellValue(lineSummary.getSum());
        cellSum.setCellStyle(styleBorderAndBold);

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }

        Path downloadsPath = Paths.get(System.getProperty("user.home"), "Downloads");
        Path parentPath = downloadsPath.getParent();
        String downloadsFolderPath = parentPath.toString() + "/Downloads/";
        String pathToSave = downloadsFolderPath.replace("\\", "/");

        //Запись в файл
        try (OutputStream fileOut = new FileOutputStream(pathToSave + "TourPurchasesReport.xlsx")) {
            book.write(fileOut);
            book.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error writing " + e);
        }
    }
}