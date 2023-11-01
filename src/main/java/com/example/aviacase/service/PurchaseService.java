package com.example.aviacase.service;

import com.example.aviacase.model.Hotel;
import com.example.aviacase.model.Purchase;
import com.example.aviacase.model.Tour;
import com.example.aviacase.model.User;
import com.example.aviacase.repository.PurchaseRepository;
import com.example.aviacase.service.exception.HotelNotFoundException;
import com.example.aviacase.service.exception.PurchaseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        Purchase purchase = new Purchase(date, nights, tourists, sum, hotel.getName(), user, tour);
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
        purchase.setHotel(hotel.getName());
        purchase.setUser(user);
        purchase.setTour(tour);
        return purchaseRepository.save(purchase);
    }
}
