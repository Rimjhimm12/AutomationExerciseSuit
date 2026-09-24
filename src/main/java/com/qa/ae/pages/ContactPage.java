package com.qa.ae.pages;

import com.qa.ae.constants.AppConstants;
import com.qa.ae.utils.ElementUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage {
    private final WebDriver driver;
    private final ElementUtil elementUtil;


    private final By GET_IN_TOUCH_HEADER = By.xpath("//h2[text() ='Get In Touch']");
    private final By NAME_FIELD = By.name("name");
    private final By EMAIL_FIELD = By.name("email");
    private final By SUBJECT_FIELD = By.name("subject");
    private final By MESSAGE_FIELD = By.name("message");
    private final By SUBMIT_BUTTON = By.name("submit");
    private final By UPLOAD_FILE_FIELD = By.name("upload_file");
    private final By SUCCESS_MESSAGE = By.xpath("//div[text()='"+AppConstants.CONTACT_US_SUCCESS_MESSAGE+"' and @class='status alert alert-success']");
    private final By HOME_BUTTON = By.xpath("//span[text()=' Home']");

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);
    }

    public boolean isGetInTouchHeaderVisible() {
        return elementUtil.waitForElementVisible(GET_IN_TOUCH_HEADER, 10).isDisplayed();
    }

    public void fillContactForm(String name, String email, String subject, String message) {
       elementUtil.doSendKeys(NAME_FIELD, name, 10);
       elementUtil.doSendKeys(EMAIL_FIELD, email, 10);
       elementUtil.doSendKeys(SUBJECT_FIELD, subject, 10);
       elementUtil.doSendKeys(MESSAGE_FIELD, message, 10);
       elementUtil.waitForElementVisible(UPLOAD_FILE_FIELD, 10).sendKeys(AppConstants.CONTACT_US_FILE);
    }

    public void clickSubmitButton() {
        elementUtil.doClick(SUBMIT_BUTTON);
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean isSuccessMessageDisplay(){
         return elementUtil.waitForElementVisible(SUCCESS_MESSAGE,10).isDisplayed();
    }

    public HomePage navigateToHomePage() {
        elementUtil.doClick(HOME_BUTTON, 10);
        return new HomePage(driver);
    }




}
