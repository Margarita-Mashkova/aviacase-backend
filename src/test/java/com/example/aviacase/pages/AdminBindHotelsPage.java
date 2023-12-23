package com.example.aviacase.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminBindHotelsPage {
    public WebDriver driver;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/form/select[1]/option")
    private WebElement tour;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/form/select[2]/option")
    private WebElement hotel;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/form/div/button")
    private WebElement btnBind;
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/ul/div/li[2]/a")
    private WebElement btnExit;
    public AdminBindHotelsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void chooseTour(){
        tour.click();
    }
    public void chooseHotel(){
        hotel.click();
    }
    public void clickBtnBind(){
        btnBind.click();
    }
    public void clickBtnExit(){
        btnExit.click();
    }
}
