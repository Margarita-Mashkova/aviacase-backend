package com.example.aviacase.service;

import com.example.aviacase.model.Hotel;
import com.example.aviacase.model.Tour;
import com.example.aviacase.repository.HotelRepository;
import com.example.aviacase.service.exception.HotelNotFoundException;
import com.example.aviacase.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Transactional(readOnly = true)
    public List<Hotel> findAllHotels() {
        return hotelRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Hotel findHotel(Long id){
        final Optional<Hotel> hotel = hotelRepository.findById(id);
        return hotel.orElseThrow(() -> new HotelNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Hotel> findHotelsByFilter(String location, String name) {
        List<Hotel> res = new ArrayList<>();
        if (location != null && name != null) {
            res = hotelRepository.findByLocationIgnoreCaseContainingAndNameIgnoreCaseContaining(location, name);
        } else if (location != null) {
            res = hotelRepository.findByLocationIgnoreCaseContaining(location);
        } else if (name != null) {
            res = hotelRepository.findByNameIgnoreCaseContaining(name);
        }
        return res;
    }

    @Transactional
    //List<MultipartFile> photos
    public Hotel addHotel(String name, String location, int star, boolean feed, float price) {
        Hotel hotel = new Hotel(name, location, star, feed, price);
        return hotelRepository.save(hotel);
    }

    @Transactional
    public Hotel editHotel(Long id, String name, String location, int star, boolean feed, float price){
        Hotel hotel = findHotel(id);
        hotel.setName(name);
        hotel.setLocation(location);
        hotel.setStar(star);
        hotel.setFeed(feed);
        hotel.setPrice(price);
        return hotelRepository.save(hotel);
    }

    @Transactional
    public Hotel uploadPhotoForHotel(Long id, MultipartFile photo){
        Hotel hotel = findHotel(id);
        hotel.setPhoto(fileUploadUtil.uploadFile(photo));
//        for (var photo : photos) {
//            hotel.getPhotos().add(fileUploadUtil.uploadFile(photo));
//        }
        return hotelRepository.save(hotel);
    }

    @Transactional
    public Hotel deleteHotel(Long id){
        final Hotel hotel = findHotel(id);
        hotelRepository.delete(hotel);
        return hotel;
    }

    @Transactional
    public void deleteAllHotels(){
        hotelRepository.deleteAll();
    }
}
