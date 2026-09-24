package com.qa.ae.tests;

import com.qa.ae.base.BaseTest;
import com.qa.ae.constants.AppConstants;
import com.qa.ae.errors.AppErrors;
import com.qa.ae.pages.ContactPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ContactUsTest extends BaseTest {
    @DataProvider
    public Object[][] validContact() {
        return new Object[][]{{"John Snow", "johnsnow@got.com", "Product is not found", "Some products are not found"}
        };
    }

    @Test(dataProvider = "validContact")
    public void contactUsTestFlow(String firstName, String email, String subject, String message) {
        // Verify that home page is visible successfully
        Assert.assertEquals(homePage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE, AppErrors.TITLE_NOT_FOUND);
        // Click on 'Contact Us' button
        contactPage = homePage.doClickContactButton();
        // Verify 'GET IN TOUCH' is visible
        Assert.assertTrue(contactPage.isGetInTouchHeaderVisible(), AppErrors.HEADER_NOT_MATCH);
        //Enter name, email, subject and message and Upload file
        contactPage.fillContactForm(firstName, email, subject, message);
        // Click 'Submit' button and accept the alert
        contactPage.clickSubmitButton();
        //Verify success message 'Success! Your details have been submitted successfully.' is visible
        Assert.assertTrue(contactPage.isSuccessMessageDisplay(),AppErrors.SUCCESS_MESSAGE_NOT_DISPLAYED);
        //Click 'Home' button and verify that landed to home page successfully
        homePage = contactPage.navigateToHomePage();
        Assert.assertEquals(homePage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE, AppErrors.TITLE_NOT_FOUND);






    }
}
