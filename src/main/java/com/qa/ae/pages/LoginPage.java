package com.qa.ae.pages;

import com.qa.ae.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final ElementUtil elementUtil;

    private final By NEW_USER_SIGNUP_HEADER = By.xpath("//h2[text()='New User Signup!']");
    private final By NEW_USER_NAME = By.xpath("//input[@name='name']");
    private final By NEW_USer_PASSWORD = By.xpath("//input[@name='email' and @data-qa='signup-email']");
    private final By SIGNUP_BUTTON = By.xpath("//button[text()=\"Signup\"]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);

    }

    public String getLoginPageHeader(){
        String loginHeader = elementUtil.waitForElementVisible(NEW_USER_SIGNUP_HEADER, 10).getText();
        System.out.println("Sign Up page header is: " + loginHeader);
        return loginHeader;
    }

    public String getSignupPageHeader(){
        String signupHeader = elementUtil.waitForElementVisible(NEW_USER_SIGNUP_HEADER, 10).getText();
        System.out.println("Sign Up page header is: " + signupHeader);
        return signupHeader;
    }

    public SignUpPage clickSignupButton(String userName, String userEmail){
        elementUtil.doSendKeys(NEW_USER_NAME, userName, 10);
        elementUtil.doSendKeys(NEW_USer_PASSWORD, userEmail, 10);
        elementUtil.doClick(SIGNUP_BUTTON);
        return new SignUpPage(driver);

    }
}
