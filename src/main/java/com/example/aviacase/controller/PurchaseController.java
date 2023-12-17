package com.example.aviacase.controller;

import com.example.aviacase.AviacaseApplication;
import com.example.aviacase.dto.MakePurchaseDto;
import com.example.aviacase.dto.PurchaseDto;
import com.example.aviacase.mapper.PurchaseMapper;
import com.example.aviacase.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseMapper purchaseMapper;
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public MakePurchaseDto buyTour(@RequestBody MakePurchaseDto purchaseDto){
        return purchaseMapper.toMakePurchaseDto(
                purchaseService.makePurchase(purchaseDto.getNights(), purchaseDto.getTourists(),
                        AviacaseApplication.authenticateUser.getId(), purchaseDto.getTourId(), purchaseDto.getHotelId()));
    }

    @GetMapping
    public List<PurchaseDto> findAllPurchases(){
        return purchaseService.findAllPurchases()
                .stream()
                .map(purchase -> purchaseMapper.toPurchaseDto(purchase))
                .toList();
    }
}
