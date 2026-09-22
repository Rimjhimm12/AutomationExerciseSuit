package com.qa.ae.pages;

import com.qa.ae.constants.AppConstants;
import com.qa.ae.exceptions.BrowserExceptions;
import com.qa.ae.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final ElementUtil elementUtil;

    private final By NEW_USER_SIGNUP_HEADER = By.xpath("//h2[text()='" + AppConstants.SIGNUP_PAGE_HEADER + "']");
    private final By USER_LOGIN_HEADER = By.xpath("//h2[text()='" + AppConstants.LOGIN_PAGE_HEADER + "']");
    private final By NEW_USER_NAME = By.xpath("//input[@name='name']");
    private final By NEW_USER_EMAIL = By.xpath("//input[@name='email' and @data-qa='signup-email']");
    private final By SIGNUP_BUTTON = By.xpath("//button[text()=\"Signup\"]");
    private final By USER_NAME = By.name("email");
    private final By USER_EMAIL = By.name("password");
    private final By LOGIN_BUTTON = By.xpath("//button[text()=\"Login\"]");
    private final By LOGIN_ERROR_MESSAGE = By.xpath("//p[text()='Your email or password is incorrect!']");
    private final By SIGNUP_ERROR_MESSAGE = By.xpath("//p[text()='Email Address already exist!']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);

    }

    /**
     * Get login page header
     * @return login page header text
     */

    public String getLoginPageHeader() {
        String loginHeader = elementUtil.waitForElementVisible(USER_LOGIN_HEADER, 10).getText();
        //System.out.println("Login page header is: " + loginHeader);
        return loginHeader;
    }

    /**
     * Login button visibility check
     */
    public boolean isLoginButtonVisible() {
        return elementUtil.waitForElementVisible(LOGIN_BUTTON, 10).isDisplayed();
    }

    /**
     * login private method
     */

    private void login(String userName, String userEmail) {
        elementUtil.doSendKeys(USER_NAME, userName, 10);
        elementUtil.doSendKeys(USER_EMAIL, userEmail, 10);
        elementUtil.doClick(LOGIN_BUTTON);
    }

    /**
     * @param userName  a username
     * @param userEmail an Email id
     * @return do log-in an existing user.
     */

    public HomePage doUserLogin(String userName, String userEmail) {
        login(userName, userEmail);
        /**
         * if (elementUtil.isElementDisplayed(LOGIN_ERROR_MESSAGE)) {
            throw new BrowserExceptions("Login failed: " + elementUtil.doGetText(LOGIN_ERROR_MESSAGE));
        }*/
        return new HomePage(driver);
    }

    /**
     * Negative login test with incorrect credentials
     * @param userName  an incorrect username
     * @param userEmail an incorrect email id
     * @return login page with error message
     */
    public LoginPage doNegativeLogin(String userName, String userEmail) {
        login(userName, userEmail);
        return this;
    }

    /**
     * Get login error message
     * @return login error message
     */

    public String getLoginErrorMessage() {
        return elementUtil.waitForElementVisible(LOGIN_ERROR_MESSAGE, 10).getText();
    }

    /**
     * Get sign-up page header
     * @return sign-up page header is displayed
     */
    public boolean getSignupPageHeader() {
        return elementUtil.waitForElementVisible(NEW_USER_SIGNUP_HEADER, 10).isDisplayed();
    }

    /**
     * Private sign-up method
     */
    private void  signUp(String userName, String userEmail) {
        elementUtil.doSendKeys(NEW_USER_NAME, userName, 10);
        elementUtil.doSendKeys(NEW_USER_EMAIL, userEmail, 10);
        elementUtil.doClick(SIGNUP_BUTTON);
    }



    /**
     * Perform user sign-up
     * @param userName  enter a new username
     * @param userEmail set a password
     * @return navigate to the real registration page
     */
    public SignUpPage doUserSignUp(String userName, String userEmail) {
        signUp(userName, userEmail);
        return new SignUpPage(driver);
    }

    /**
     * Login with existing user email ID.
     * @param userName  enter a new username
     * @param userEmail set a password
     * @return login page with error message if the user already exists
     */

    public LoginPage doOldUserSignUp(String userName, String userEmail) {
        signUp(userName, userEmail);
        return this;
    }

    /**
     * Get sign-up error message
     * @return sign-up error message is displayed
     */
    public boolean getSignUpErrorMessage() {
        return elementUtil.waitForElementVisible(SIGNUP_ERROR_MESSAGE, 10).isDisplayed();
    }

}