package com.example.aviacase.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminAddTourPage {
    public WebDriver driver;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/form/div/input[1]")
    private WebElement inputName;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/form/div/input[2]")
    private WebElement inputCountry;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/form/div/input[3]")
    private WebElement inputDate;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/form/div/input[4]")
    private WebElement inputPrice;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/form/div/div[2]/button")
    private WebElement btnSaveTour;
    public AdminAddTourPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void enterName(String name) {
        inputName.sendKeys(name);
    }
    public void enterCountry(String country) {
        inputCountry.sendKeys(country);
    }
    public void enterDate(String date) {
        inputDate.sendKeys(date);
    }
    public void enterPrice(String price) {
        inputPrice.sendKeys(price);
    }
    public void clickBtnSaveTour() {
        btnSaveTour.click();
    }
}
