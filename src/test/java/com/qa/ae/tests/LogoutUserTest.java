package com.qa.ae.tests;

import com.qa.ae.base.BaseTest;
import com.qa.ae.constants.AppConstants;
import com.qa.ae.errors.AppErrors;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutUserTest extends BaseTest {
    @Test
    public void logOutUserTestFlow(){

        String homePageTitle = homePage.getHomePageTitle();
        //Verify that home page is visible successfully
        Assert.assertTrue(homePageTitle.contains(AppConstants.HOME_PAGE_TITLE), AppErrors.TITLE_NOT_FOUND);

        //Click on 'Signup / Login' button
        loginPage = homePage.doClickSignupButton();
        //Verify 'Login to your account' is visible
        Assert.assertTrue(loginPage.getLoginPageHeader().contains(AppConstants.LOGIN_PAGE_HEADER), AppErrors.HEADER_NOT_MATCH);

        //Enter correct email address and password
        //Click 'login' button
        homePage = loginPage.doUserLogin(prop.getProperty("username"), prop.getProperty("password"));
        //Verify that 'Logged in as username' is visible
        Assert.assertTrue(homePage.getLoggedInUser().contains("Logged in as"), AppErrors.INCORRECT_LOGGED_IN_USER_NAME);

        //Click 'Logout' button
        loginPage = homePage.doClickLogOutButton();
        //Verify that user is navigated to login page
        Assert.assertTrue(loginPage.isLoginButtonVisible(), AppErrors.LOGIN_BUTTON_NOT_VISIBLE);






    }
}
