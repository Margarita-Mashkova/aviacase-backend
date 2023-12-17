package com.example.aviacase.controller;

import com.example.aviacase.dto.ReportTourPurchasesDto;
import com.example.aviacase.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public List<ReportTourPurchasesDto> makeTourPurchasesReport(@RequestParam String dateStart,
                                                                @RequestParam String dateEnd) throws ParseException {
        Date dateFrom = new SimpleDateFormat("yyyy MM dd", Locale.ENGLISH).parse(dateStart);
        Date dateTo = new SimpleDateFormat("yyyy MM dd", Locale.ENGLISH).parse(dateEnd);
        return purchaseService.makeTourPurchasesReport(dateFrom, dateTo);
    }

    @GetMapping
    public void saveReportExcel(@RequestParam String dateStart,
                                @RequestParam String dateEnd) throws ParseException {
        Date dateFrom = new SimpleDateFormat("yyyy MM dd", Locale.ENGLISH).parse(dateStart);
        Date dateTo = new SimpleDateFormat("yyyy MM dd", Locale.ENGLISH).parse(dateEnd);
        purchaseService.saveReportExcel(dateFrom, dateTo);
    }
}
