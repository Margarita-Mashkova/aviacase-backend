package com.example.aviacase.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminToursPage {
    public WebDriver driver;
    @FindBy(xpath = "//*[@id=\"app\"]/div[3]/button")
    private WebElement btnAddTour;
    @FindBy(xpath = "//*[@id=\"app\"]/div[4]/div/div/div[2]/label[1]")
    private WebElement firstTourName;
    public AdminToursPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clickBtnAddTour() {
        btnAddTour.click();
    }
    public String getFirstTourName(){
        return firstTourName.getText();
    }
}
