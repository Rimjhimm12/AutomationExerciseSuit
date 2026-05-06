package com.qa.ae.pages;

import com.qa.ae.errors.AppErrors;
import com.qa.ae.utils.ElementUtil;
import com.qa.ae.utils.RandomStringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignUpPage {
    private final WebDriver driver;
    private final ElementUtil elementUtil;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);
    }

    private final By SIGNUP_PAGE_HEADER = By.xpath("//b[text()='Enter Account Information']");
    private final By USER_Title_MR =By.id("uniform-id_gender1");
    private final By USER_Title_MRS =By.id("uniform-id_gender2");
    private final By USER_NAME = By.id("name");
    private final By USER_PASSWORD = By.id("password");
    private final By USER_DOB_DAYS =By.id("uniform-days");
    private final By USER_DOB_MONTHS =By.id("uniform-months");
    private final By USER_DOB_YEARS =By.id("uniform-years");
    private final By NEWSLETTER_CHECKBOX = By.id("newsletter");
    private final By SPECIAL_OFFERS_CHECKBOX = By.id("optin");
    private final By FIRST_NAME = By.id("first_name");
    private final By LAST_NAME = By.id("last_name");
    private final By COMPANY_NAME =By.id("company");
    private final By ADDRESS1 = By.id("address1");
    private final By ADDRESS2 = By.id("address2");
    private final By COUNTRY = By.id("country");
    private final By STATE = By.id("state");
    private final By CITY = By.id("city");
    private final By ZIPCODE = By.id("zipcode");
    private final By MOBILE_NUMBER = By.id("mobile_number");
    private final By CREATE_ACCOUNT_BUTTON =By.xpath("//button[@type='submit' and @data-qa='create-account']");



    public boolean getInformationHeader() {
        boolean flag = false;
        WebElement element = elementUtil.waitForElementVisible(SIGNUP_PAGE_HEADER, 10);
        flag = element.isDisplayed();
        if (flag) {
            String infoHeader = elementUtil.doGetText(SIGNUP_PAGE_HEADER);
            System.out.println("Information header is: " + infoHeader);
            return true;
        }
        else {
            throw new RuntimeException(AppErrors.DETAILS_PAGE_NOT_DISPLAYED);
        }
    }

    public AccountPage registerNewUsers(String password, String days, String months, String years,
                                  String userLastName, String companyName, String address1, String address2,
                                  String countryName, String state, String city, String zipcode, String mobileNumber) {
        elementUtil.doClick(USER_Title_MR, 10);
        //elementUtil.doGetAttribute(USER_Title_MR, "value");
        String userName =  elementUtil.doGetAttribute(USER_NAME, "value");
        elementUtil.doSendKeys(USER_PASSWORD, password, 10);
        elementUtil.selectValueFromDropDownWithoutSelectClass(USER_DOB_DAYS, days);
        elementUtil.selectValueFromDropDownWithoutSelectClass(USER_DOB_MONTHS, months);
        elementUtil.selectValueFromDropDownWithoutSelectClass(USER_DOB_YEARS, years);
        elementUtil.doClick(NEWSLETTER_CHECKBOX);
        elementUtil.doClick(SPECIAL_OFFERS_CHECKBOX);
        elementUtil.doSendKeys(FIRST_NAME, userName, 10);
        elementUtil.doSendKeys(LAST_NAME, userLastName, 10);
        elementUtil.doSendKeys(COMPANY_NAME, companyName, 10);
        elementUtil.doSendKeys(ADDRESS1, address1, 10);
        elementUtil.doSendKeys(ADDRESS2, address2, 10);
        elementUtil.selectValueFromDropDownWithoutSelectClass(COUNTRY, countryName);
        elementUtil.doSendKeys(STATE, state, 10);
        elementUtil.doSendKeys(CITY, city, 10);
        elementUtil.doSendKeys(ZIPCODE, zipcode, 10);
        elementUtil.doSendKeys(MOBILE_NUMBER, mobileNumber, 10);
        elementUtil.doClick(CREATE_ACCOUNT_BUTTON);
        return new AccountPage(driver);

    }





}
