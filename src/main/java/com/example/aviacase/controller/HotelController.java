package com.example.aviacase.controller;

import com.example.aviacase.dto.HotelDto;
import com.example.aviacase.mapper.HotelMapper;
import com.example.aviacase.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelService hotelService;
    @Autowired
    HotelMapper hotelMapper;

    @GetMapping("/hotels")
    public List<HotelDto> findAllHotels() {
        return hotelService.findAllHotels()
                .stream()
                .map(hotel -> hotelMapper.toHotelDto(hotel))
                .toList();
    }

    @GetMapping("/{id}")
    public HotelDto findHotel(@PathVariable Long id){
        return hotelMapper.toHotelDto(hotelService.findHotel(id));
    }

    @GetMapping("/hotels/filter")
    public List<HotelDto> findHotelsByFilter(@RequestParam(required = false) String location,
                                             @RequestParam(required = false) String name){
        return hotelService.findHotelsByFilter(location, name)
                .stream()
                .map(hotel -> hotelMapper.toHotelDto(hotel))
                .toList();
    }

    @PostMapping
    public HotelDto createHotel(@RequestBody HotelDto hotelDto) {
        return hotelMapper.toHotelDto(hotelService.addHotel(hotelDto.getName(), hotelDto.getLocation(),
                hotelDto.getStar(), hotelDto.getFeed(), hotelDto.getPrice()));
    }

    @PutMapping(value = "/{id}")
    public HotelDto editHotel(@PathVariable Long id, @RequestBody HotelDto hotelDto){
        return hotelMapper.toHotelDto(hotelService.editHotel(id, hotelDto.getName(), hotelDto.getLocation(),
                hotelDto.getStar(), hotelDto.getFeed(), hotelDto.getPrice()));
    }

    @PutMapping(value = "/{id}/upload", consumes = {MULTIPART_FORM_DATA_VALUE})
    public HotelDto uploadPhotoForHotel(@PathVariable Long id, @RequestParam(required = false) MultipartFile photo){
        return hotelMapper.toHotelDto(hotelService.uploadPhotoForHotel(id, photo));
    }

    @DeleteMapping("/{id}")
    public void deleteHotel(@PathVariable Long id){
        hotelService.deleteHotel(id);
    }
}
