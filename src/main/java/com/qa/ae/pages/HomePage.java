package com.qa.ae.pages;

import com.qa.ae.constants.AppConstants;
import com.qa.ae.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;
    private final ElementUtil elementUtil;

    public HomePage(WebDriver driver) {

        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);
    }

    private final By SIGNUP_BUTTON = By.xpath("//a[text()='"+AppConstants.SIGNUP_LOGIN_BUTTON+"']");
    private final By SIGNED_USER = By.xpath("//a[i[contains(@class,'fa-user')] and contains(., 'Logged in as')]");
    private final By DELETE_ACCOUNT = By.linkText("Delete Account");



    public String getHomePageTitle(){
        String homePageTitle = elementUtil.waitForTitleContains(AppConstants.HOME_PAGE_TITLE,10);
        System.out.println("Home page title is: " + homePageTitle);
        return homePageTitle;
    }

    public LoginPage doClickSignupButton(){
        elementUtil.doClick(SIGNUP_BUTTON, 10);
        return new LoginPage(driver);
    }

    public String isUserLoggedIn(){
        String text = elementUtil.waitForElementVisible(SIGNED_USER, 10).getText();
        System.out.println("Logged in user is: " + text);
        return text;
    }

     /**
      * Get logged in username
      * Returns text like "Logged in as kiki"
      * XPath explanation: //a[contains(., 'Logged in as')]
      *   - Finds <a> element containing "Logged in as" text
      *   - The dot (.) includes all text nodes and descendant text
      *   - getText() will return the complete text with username
      */
    public String getLoggedInUser(){
        String text = elementUtil.waitForElementVisible(SIGNED_USER, 10).getText();
        System.out.println(text);
        return text;
    }

    public AccountPage doClickDeleteAccountButton(){
        elementUtil.doClick(DELETE_ACCOUNT, 10);
        return new AccountPage(driver);
    }








}
