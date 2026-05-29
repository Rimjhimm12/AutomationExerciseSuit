package com.qa.ae.pages;

import com.qa.ae.constants.AppConstants;
import com.qa.ae.exceptions.BrowserExceptions;
import com.qa.ae.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final ElementUtil elementUtil;

    private final By NEW_USER_SIGNUP_HEADER = By.xpath("//h2[text()='"+AppConstants.SIGNUP_PAGE_HEADER+"']");
    private final By USER_LOGIN_HEADER = By.xpath("//h2[text()='"+ AppConstants.LOGIN_PAGE_HEADER +"']");
    private final By NEW_USER_NAME = By.xpath("//input[@name='name']");
    private final By NEW_USER_PASSWORD = By.xpath("//input[@name='email' and @data-qa='signup-email']");
    private final By SIGNUP_BUTTON = By.xpath("//button[text()=\"Signup\"]");
    private final By USER_NAME = By.name("email");
    private final By USER_PASSWORD = By.name("password");
    private final By LOGIN_BUTTON = By.xpath("//button[text()=\"Login\"]");
    private final By LOGIN_ERROR_MESSAGE = By.xpath("//p[text()='Your email or password is incorrect!']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);

    }

    /**
     * Get login page header
     * @return login page header text
     */

    public String getLoginPageHeader(){
        String loginHeader = elementUtil.waitForElementVisible(USER_LOGIN_HEADER, 10).getText();
        System.out.println("Login page header is: " + loginHeader);
        return loginHeader;
    }

    /***
     *
     * @param userName existing username
     * @param userEmail an Email id
     * @return do log-in an existing user.
     */

    public HomePage doUserLogin(String userName, String userEmail){
        elementUtil.doSendKeys(USER_NAME, userName, 10);
        elementUtil.doSendKeys(USER_PASSWORD, userEmail, 10);
        elementUtil.doClick(LOGIN_BUTTON);
        if(elementUtil.isElementDisplayed(LOGIN_ERROR_MESSAGE)){
            throw new BrowserExceptions("Login failed: " + elementUtil.doGetText(LOGIN_ERROR_MESSAGE));
        }

        return new HomePage(driver);

    }

    /**
     * Negative login test with incorrect credentials
     * @param userName an incorrect username
     * @param userEmail an incorrect email id
     * @return login page with error message
     */
    public LoginPage doNegativeLogin(String userName, String userEmail) {
        elementUtil.doSendKeys(USER_NAME, userName, 10);
        elementUtil.doSendKeys(USER_PASSWORD, userEmail, 10);
        elementUtil.doClick(LOGIN_BUTTON, 10);
        return this;
    }

    public String getLoginErrorMessage() {
        return elementUtil.waitForElementVisible(LOGIN_ERROR_MESSAGE, 10).getText();
    }



    public String getSignupPageHeader(){
        String signupHeader = elementUtil.waitForElementVisible(NEW_USER_SIGNUP_HEADER, 10).getText();
        System.out.println("Sign Up page header is: " + signupHeader);
        return signupHeader;
    }

    /**
     *
     * @param userName enter a new username
     * @param userEmail set a password
     * @return navigate to the real registration page
     */

    public SignUpPage doUserSignUp(String userName, String userEmail){
        elementUtil.doSendKeys(NEW_USER_NAME, userName, 10);
        elementUtil.doSendKeys(NEW_USER_PASSWORD, userEmail, 10);
        elementUtil.doClick(SIGNUP_BUTTON);
        return new SignUpPage(driver);

    }
}
