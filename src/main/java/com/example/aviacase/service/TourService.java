package com.example.aviacase.service;

import com.example.aviacase.model.Hotel;
import com.example.aviacase.model.Tour;
import com.example.aviacase.repository.TourRepository;
import com.example.aviacase.service.exception.TourNotFoundException;
import com.example.aviacase.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TourService {
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Transactional(readOnly = true)
    public List<Tour> findAllTours(){
        return tourRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Tour findTour(Long id){
        final Optional<Tour> tour = tourRepository.findById(id);
        return tour.orElseThrow(() -> new TourNotFoundException(id));
    }

    @Transactional
    public Tour addTour(String name, String country, Date startDate, float price, List<Hotel> hotels, MultipartFile photo) {
        final Tour tour = new Tour(name, country, startDate, price);
        tour.setHotels(hotels);
        tour.setPhoto(fileUploadUtil.uploadFile(photo));
        return tourRepository.save(tour);
    }

    @Transactional
    public Tour editTour(Long id, String name, String country, Date startDate, float price, List<Hotel> hotels, MultipartFile file){
        final Tour tour = findTour(id);
        tour.setName(name);
        tour.setCountry(country);
        tour.setStartDate(startDate);
        tour.setPrice(price);
        tour.setHotels(hotels);
        tour.setPhoto(fileUploadUtil.uploadFile(file));
        return tourRepository.save(tour);
    }

    @Transactional
    public Tour deleteTour(Long id){
        final Tour tour = findTour(id);
        tourRepository.delete(tour);
        return tour;
    }

    public void deleteAllTours(){
        tourRepository.deleteAll();
    }

}
