package com.example.aviacase.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    public WebDriver driver;
    @FindBy(xpath = "//*[@id=\"app\"]/div/form/div[1]/input")
    private WebElement inputName;
    @FindBy(xpath = "//*[@id=\"app\"]/div/form/div[2]/input")
    private WebElement inputSurname;
    @FindBy(xpath = "//*[@id=\"app\"]/div/form/div[3]/input")
    private WebElement inputLogin;
    @FindBy(xpath = "//*[@id=\"app\"]/div/form/div[4]/input")
    private WebElement inputPassword;
    @FindBy(xpath = "//*[@id=\"app\"]/div/form/div[5]/input")
    private WebElement inputPasswordR;
    @FindBy(xpath = "//*[@id=\"app\"]/div/form/div[6]/button")
    private WebElement btnRegistrate;
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void enterName(String name) {
        inputName.sendKeys(name);
    }
    public void enterSurname(String surname) {
        inputSurname.sendKeys(surname);
    }
    public void enterLogin(String login) {
        inputLogin.sendKeys(login);
    }
    public void enterPassword(String passwd) {
        inputPassword.sendKeys(passwd);
    }
    public void repeatPassword(String passwd) {
        inputPasswordR.sendKeys(passwd);
    }
    public void clickBtnRegistrate() {
        btnRegistrate.click();
    }
}
