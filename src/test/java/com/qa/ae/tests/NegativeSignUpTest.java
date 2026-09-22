package com.qa.ae.tests;

import com.qa.ae.base.BaseTest;
import com.qa.ae.constants.AppConstants;
import com.qa.ae.errors.AppErrors;
import com.qa.ae.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class NegativeSignUpTest extends BaseTest {

    @DataProvider
    public Object[][] getNegativeSignUpData() {
        return new Object[][]{
                {"Arya Stark", "aryastark@got.com"},
                {"Jon Snow", "jonsnow@got.com"}
        };
    }

    @Test(dataProvider = "getNegativeSignUpData")
    public void NegativeSignUpTestFlow(String username, String email) {
        //Verify that home page is visible successfully
        Assert.assertTrue(homePage.getHomePageTitle().contains(AppConstants.HOME_PAGE_TITLE), AppErrors.TITLE_NOT_FOUND);

        //Click on 'Signup / Login' button
        loginPage = homePage.doClickSignupButton();
        //Verify 'New User Signup!' is visible
        Assert.assertTrue(loginPage.getSignupPageHeader(), AppErrors.HEADER_NOT_MATCH);
        // Enter name and already registered email address and Click 'Signup' button
        loginPage = loginPage.doOldUserSignUp(username,email);
        //Verify error 'Email Address already exist!' is visible
        Assert.assertTrue(loginPage.getSignUpErrorMessage(), AppErrors.SIGNUP_ERROR_MESSAGE_NOT_DISPLAYED);

    }



}
