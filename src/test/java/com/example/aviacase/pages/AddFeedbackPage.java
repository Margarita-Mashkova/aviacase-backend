package com.example.aviacase.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddFeedbackPage {
    public WebDriver driver;
    @FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div/div/div[1]/div[2]/div[2]/button")
    private WebElement btnCreateFeedback;
    @FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div/div/div[2]/div/textarea")
    private WebElement inputFeedbackText;
    @FindBy(xpath = "//*[@id=\"four\"]")
    private WebElement radioFourRate;
    @FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div/div/div[2]/div/div[2]/button[2]")
    private WebElement btnSendFeedback;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/div[2]/div/div/div[2]/div/label")
    private WebElement feedBackText;
    public AddFeedbackPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clickBtnCreateFeedback(){
        btnCreateFeedback.click();
    }
    public void enterFeedbackText(String text){
        inputFeedbackText.sendKeys(text);
    }
    public void setFourRate(){
        radioFourRate.click();
    }
    public void clickBtnSendFeedback(){
        btnSendFeedback.click();
    }
    public String getFeedbackText(){
        return feedBackText.getText();
    }
}
