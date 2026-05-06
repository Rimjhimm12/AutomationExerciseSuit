package com.qa.ae.base;

import com.qa.ae.factory.DriverFactory;
import com.qa.ae.pages.AccountPage;
import com.qa.ae.pages.HomePage;
import com.qa.ae.pages.LoginPage;
import com.qa.ae.pages.SignUpPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    DriverFactory factory;
    WebDriver driver;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected SignUpPage signUpPage;
    protected AccountPage accountPage;



    @BeforeTest
    public void setUp(){
        factory = new DriverFactory();
        driver = factory.initDriver("firefox");
        homePage = new HomePage(driver);

    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }




}
