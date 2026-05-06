package com.qa.ae.tests;

import com.qa.ae.base.BaseTest;
import com.qa.ae.constants.AppConstants;
import com.qa.ae.errors.AppErrors;
import com.qa.ae.pages.LoginPage;
import com.qa.ae.utils.RandomStringUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterUserTest extends BaseTest{

    @Test(priority = 1)
    public void homePageTitleTest(){
        Assert.assertTrue(homePage.getHomePageTitle().contains("Automation"), AppErrors.TITLE_NOT_FOUND);

    }

    @Test(priority = 2)
    public void signUpHeaderTest(){
        loginPage = homePage.doClickSignupButton();
        Assert.assertEquals(loginPage.getSignupPageHeader(),AppConstants.SIGNUP_PAGE_HEADER, AppErrors.HEADER_NOT_MATCH);

    }

    @DataProvider
    public Object[][] getData(){
        return new Object[][]{
                {"Erwin"}
        };
    }

    @Test(priority = 3, dataProvider = "getData")
    public void isDetailsPageDisplayed(String username){
        signUpPage = loginPage.clickSignupButton(username, RandomStringUtil.getRandomEmail());
        Assert.assertTrue(signUpPage.getInformationHeader(), AppErrors.DETAILS_PAGE_NOT_DISPLAYED);

    }

    @DataProvider
    public Object[][] getRegisterData(){
        return new Object[][]{
                {"2","May","1995","Jay","Google","8202 S Santa Fe Dr","Apt 2","United States","California","Los Angeles","90001","9870659010"}
        };
    }

    @Test(priority = 4, dataProvider = "getRegisterData")
    public void registerUserTest(String days, String months, String years,
                                 String userLastName, String companyName, String address1, String address2,
                                 String countryName, String state, String city, String zipcode, String mobileNumber){
        accountPage = signUpPage.registerNewUsers(RandomStringUtil.getRandomText(),days,months,years,
                userLastName,companyName,address1,address2,countryName,state,city,zipcode,mobileNumber);
        Assert.assertTrue(accountPage.isAccountCreated(), AppErrors.ACCOUNT_CREATION_FAILED);

    }

    @Test(priority = 5)
    public void userSignedUpTest(){
        homePage = accountPage.doContinue();
        Assert.assertEquals(homePage.getLoggedInUser(), "Logged in as Erwin", AppErrors.HEADER_NOT_MATCH);
    }

    @Test(priority = 6)
    public void accountDeletionTest(){
        accountPage = homePage.doClickDeleteAccountButton();
        Assert.assertTrue(accountPage.isAccountDeleted(), AppErrors.ACCOUNT_DELETION_FAILED);
    }

    @Test(priority = 7)
    public void returnToHomePageTest(){
        homePage = accountPage.doContinue();
        Assert.assertTrue(homePage.getHomePageTitle().contains("Automation"), AppErrors.TITLE_NOT_FOUND);

    }

}

