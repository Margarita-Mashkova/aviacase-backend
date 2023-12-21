package com.example.aviacase.controller;

import com.example.aviacase.dto.ListHotelsDto;
import com.example.aviacase.dto.TourDto;
import com.example.aviacase.mapper.HotelMapper;
import com.example.aviacase.mapper.TourMapper;
import com.example.aviacase.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/tour")
public class TourController {
    @Autowired
    private TourService tourService;
    @Autowired
    private TourMapper tourMapper;
    @Autowired
    private HotelMapper hotelMapper;

    @GetMapping("/tours")
    public List<TourDto> findAllTours(){
        return tourService.findAllTours()
                .stream()
                .map(tour -> tourMapper.toTourDto(tour))
                .toList();
    }

    @GetMapping("/{id}")
    public TourDto findTour(@PathVariable Long id){
        return tourMapper.toTourDto(tourService.findTour(id));
    }

    @GetMapping("/tours/filter")
    public List<TourDto> findToursByFilter(@RequestParam(required = false) String country,
                                           @RequestParam(required = false) String dateStart) throws ParseException {
        Date date = null;
        if(dateStart != null && !dateStart.isBlank()){
            date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateStart);
            System.out.println(date);
        }
        return tourService.findToursByFilter(country, date)
                .stream()
                .map(tour -> tourMapper.toTourDto(tour))
                .toList();
    }

    @PostMapping
    public TourDto createTour(@RequestBody TourDto tourDto){
        return tourMapper.toTourDto(tourService.addTour(tourDto.getName(), tourDto.getCountry(), tourDto.getStartDate(),
                tourDto.getPrice()));
    }

    @PutMapping(value = "/{id}")
    public TourDto editTour(@PathVariable Long id, @RequestBody TourDto tourDto){
        return tourMapper.toTourDto(tourService.editTour(id, tourDto.getName(), tourDto.getCountry(),
                tourDto.getStartDate(), tourDto.getPrice()));
    }

    @PutMapping(value = "/{id}/upload", consumes = {MULTIPART_FORM_DATA_VALUE})
    public TourDto uploadTourPhoto(@PathVariable Long id, @RequestParam(required = false) MultipartFile photo){
        return tourMapper.toTourDto(tourService.uploadPhotoForTour(id, photo));
    }

    @PutMapping("bind-hotels/{id}")
    public TourDto bindHotelsToTour(@PathVariable Long id, @RequestBody ListHotelsDto hotels){
        return tourMapper.toTourDto(tourService.bindHotels(id, hotels.getHotels()
                .stream()
                .map(hotelDto -> hotelMapper.fromHotelDto(hotelDto))
                .toList()));
    }

    @DeleteMapping("/{id}")
    public void deleteTour(@PathVariable Long id){
        tourService.deleteTour(id);
    }
}
