package com.example.aviacase.dto;

import lombok.Data;

@Data
public class ReportTourPurchasesDto {
    private String tourName;
    private int amountPurchases;
    private float sum;
}
