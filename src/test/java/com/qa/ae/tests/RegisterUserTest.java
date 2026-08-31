package com.qa.ae.tests;

import com.qa.ae.base.BaseTest;
import com.qa.ae.constants.AppConstants;
import com.qa.ae.errors.AppErrors;
import com.qa.ae.utils.RandomStringUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterUserTest extends BaseTest {

    @DataProvider
    public Object[][] getRegisterUserData() {
        return new Object[][]{
                {"Erwin", "2", "May", "1995", "Jay", "Google", "8202 S Santa Fe Dr", "Apt 2",
                        "United States", "California", "Los Angeles", "90001", "9870659010"}
        };
    }




    @Test(dataProvider = "getRegisterUserData")
    public void registerUserFlowTest(String username, String days, String months, String years,
                                     String userLastName, String companyName, String address1, String address2,
                                     String countryName, String state, String city, String zipcode,
                                     String mobileNumber) {

        Assert.assertTrue(homePage.getHomePageTitle().contains(AppConstants.HOME_PAGE_TITLE), AppErrors.TITLE_NOT_FOUND);

        loginPage = homePage.doClickSignupButton();
        Assert.assertEquals(loginPage.getSignupPageHeader(), AppConstants.SIGNUP_PAGE_HEADER,
                AppErrors.HEADER_NOT_MATCH);

        signUpPage = loginPage.doUserSignUp(username, RandomStringUtil.getRandomEmail());
        Assert.assertTrue(signUpPage.getInformationHeader(), AppErrors.DETAILS_PAGE_NOT_DISPLAYED);

        accountPage = signUpPage.registerNewUsers(RandomStringUtil.getRandomText(), days, months, years,
                userLastName, companyName, address1, address2, countryName, state, city, zipcode, mobileNumber);
        Assert.assertTrue(accountPage.isAccountCreated(), AppErrors.ACCOUNT_CREATION_FAILED);

        homePage = accountPage.doContinue();
        Assert.assertEquals(homePage.getLoggedInUser(), "Logged in as " + username, AppErrors.INCORRECT_LOGGED_IN_USER_NAME);

        accountPage = homePage.doClickDeleteAccountButton();
        Assert.assertTrue(accountPage.isAccountDeleted(), AppErrors.ACCOUNT_DELETION_FAILED);

        homePage = accountPage.doContinue();
        Assert.assertTrue(homePage.getHomePageTitle().contains("Automation"), AppErrors.TITLE_NOT_FOUND);
    }
}
