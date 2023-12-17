package com.example.aviacase.mapper;

import com.example.aviacase.dto.MakePurchaseDto;
import com.example.aviacase.dto.PurchaseDto;
import com.example.aviacase.model.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PurchaseMapper {
    Purchase fromPurchaseDto(PurchaseDto purchaseDto);
    PurchaseDto toPurchaseDto (Purchase purchase);
    MakePurchaseDto toMakePurchaseDto (Purchase purchase);
}
