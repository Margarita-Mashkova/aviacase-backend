package com.example.aviacase.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminHotelsPage {
    public WebDriver driver;
    @FindBy(xpath = "//*[@id=\"app\"]/div[3]/button")
    private WebElement btnAddHotel;
    @FindBy(xpath = "//*[@id=\"app\"]/div[4]/div/div/div[2]/label")
    private WebElement firstHotelName;
    public AdminHotelsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clickBtnAddHotel() {
        btnAddHotel.click();
    }
    public String getFirstHotelName(){
        return firstHotelName.getText();
    }
}
