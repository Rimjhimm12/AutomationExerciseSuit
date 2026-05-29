package com.qa.ae.factory;

import com.qa.ae.errors.AppErrors;
import com.qa.ae.exceptions.BrowserExceptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class DriverFactory {

    WebDriver driver;


    public WebDriver createDriver(String browser) {
        if (browser == null || browser.trim().isEmpty()) {
            throw new BrowserExceptions("Browser value is null or empty.");
        }

        switch(browser.toLowerCase().trim()){
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                System.getProperty("webdriver.edge.driver, /Users/rimjhim/Desktop/edgedriver_mac64/msedgedriver");
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Please pass the correct browser; e.g: chrome, firefox, edge or safari");
                throw new BrowserExceptions(AppErrors.BROWSER_NOT_FOUND);
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }






}
