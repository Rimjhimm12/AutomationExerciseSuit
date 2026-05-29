package com.qa.ae.tests;

import com.qa.ae.base.BaseTest;
import com.qa.ae.constants.AppConstants;
import com.qa.ae.errors.AppErrors;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginUserTest extends BaseTest {



    @Test
    public void loginUserFlowTest() {
        //Varifying home page is loaded successfully and title of the page is displaying.
        Assert.assertTrue(homePage.getHomePageTitle().contains(AppConstants.HOME_PAGE_TITLE), AppErrors.TITLE_NOT_FOUND);

        //Clicking Signup/Login button and verifying login page is loaded successfully and header of the page is displaying.
        loginPage = homePage.doClickSignupButton();
        Assert.assertEquals(loginPage.getLoginPageHeader(), AppConstants.LOGIN_PAGE_HEADER, AppErrors.HEADER_NOT_MATCH);

        //Login with valid credentials and verifying user is logged in successfully and correct username is displayed.
        homePage = loginPage.doUserLogin(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertTrue(homePage.getLoggedInUser().contains("Logged in as"), AppErrors.INCORRECT_LOGGED_IN_USER_NAME);

        //Clicking Delete Account button and verifying account is deleted successfully and correct header is displayed.
        accountPage = homePage.doClickDeleteAccountButton();
        Assert.assertTrue(accountPage.isAccountDeleted(), AppErrors.ACCOUNT_DELETION_FAILED);


    }
}
