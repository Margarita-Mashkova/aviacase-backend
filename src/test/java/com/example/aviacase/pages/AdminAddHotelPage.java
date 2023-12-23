package com.example.aviacase.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminAddHotelPage {
    public WebDriver driver;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/form/div/input[1]")
    private WebElement inputName;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/form/div/input[2]")
    private WebElement inputCountry;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/form/div/input[3]")
    private WebElement inputCity;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/form/div/input[4]")
    private WebElement inputPrice;
    @FindBy(xpath = "//*[@id=\"three\"]")
    private WebElement threeStars;
    @FindBy(xpath = "//*[@id=\"yes\"]")
    private WebElement feedYes;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/form/div/div[4]/button")
    private WebElement btnSaveHotel;
    public AdminAddHotelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void enterName(String name) {
        inputName.sendKeys(name);
    }
    public void enterCountry(String country) {
        inputCountry.sendKeys(country);
    }
    public void enterCity(String city) {
        inputCity.sendKeys(city);
    }
    public void chooseThreeStars() {
        threeStars.click();
    }
    public void chooseFeedYes() {
        feedYes.click();
    }
    public void enterPrice(String price) {
        inputPrice.sendKeys(price);
    }
    public void clickBtnSaveHotel() {
        btnSaveHotel.click();
    }
}
