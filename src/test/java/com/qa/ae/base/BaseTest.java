package com.qa.ae.base;

import com.qa.ae.factory.ConfigReader;
import com.qa.ae.factory.DriverFactory;
import com.qa.ae.pages.AccountPage;
import com.qa.ae.pages.HomePage;
import com.qa.ae.pages.LoginPage;
import com.qa.ae.pages.SignUpPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    WebDriver driver;
    protected Properties prop;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected SignUpPage signUpPage;
    protected AccountPage accountPage;





    @BeforeMethod
    public void setUp() throws IOException {

        ConfigReader cr = new ConfigReader();
        prop = cr.initProperties();

        DriverFactory df = new DriverFactory();
        driver = df.createDriver(prop.getProperty("browser"));

        driver.get(prop.getProperty("url"));
        homePage = new HomePage(driver);

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }




}
