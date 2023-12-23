package com.example.aviacase.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BuyTourPage {
    public WebDriver driver;
    @FindBy(xpath = "//*[@id=\"app\"]/div[4]/div/div/div[2]/div[2]/button")
    private WebElement btnChooseTour;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/div/div/div[2]/div[2]/button")
    private WebElement btnConfirmChooseTour;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/div[2]/div/div[2]/div[2]/button")
    private WebElement btnChooseHotel;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/div[3]/div[1]/input")
    private WebElement inputTourists;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/div[3]/div[2]/input")
    private WebElement inputNights;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/div[4]/button")
    private WebElement btnMakePurchase;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/div[2]/button")
    private WebElement btnBuyTour;
    public BuyTourPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clickBtnChooseTour() {
        btnChooseTour.click();
    }
    public void clickBtnConfirmChooseTour(){
        btnConfirmChooseTour.click();
    }
    public void clickBtnChooseHotel(){
        btnChooseHotel.click();
    }
    public void enterTourists(String tourists) {
        inputTourists.sendKeys(tourists);
    }
    public void enterNights(String nights) {
        inputNights.sendKeys(nights);
    }
    public void clickBtnBuyTour(){
        btnBuyTour.click();
    }
    public void clickBtnMakePurchase(){
        btnMakePurchase.click();
    }
}
