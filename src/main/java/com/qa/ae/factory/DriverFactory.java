package com.qa.ae.factory;

import com.qa.ae.errors.AppErrors;
import com.qa.ae.exceptions.BrowserExceptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;



public class DriverFactory {

    WebDriver driver;

    // Known ad/tracker domains that inject banners, interstitials and popups
    // on automationexercise.com and interfere with element clicks in tests.
    private static final List<String> AD_DOMAINS = Arrays.asList(
            "doubleclick.net",
            "googlesyndication.com",
            "googleadservices.com",
            "google-analytics.com",
            "adservice.google.com",
            "amazon-adsystem.com",
            "adnxs.com",
            "taboola.com",
            "outbrain.com"
    );


    public WebDriver createDriver(String browser) {
        if (browser == null || browser.trim().isEmpty()) {
            throw new BrowserExceptions("Browser value is null or empty.");
        }

        switch(browser.toLowerCase().trim()){
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                options.addArguments(buildAdBlockHostResolverRule());
                driver = new ChromeDriver(options);
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

    /**
     * Builds a Chromium --host-resolver-rules flag that routes known ad
     * domains (and their subdomains) to a non-routable address, so ad
     * banners/popups never load and can't intercept clicks on page elements.
     * Native Chromium flag, independent of browser version (unlike CDP-based
     * network interception, which breaks whenever Selenium's bundled DevTools
     * jar doesn't match the installed Chrome version).
     */
    private static String buildAdBlockHostResolverRule() {
        String rules = AD_DOMAINS.stream()
                .flatMap(domain -> Arrays.asList(
                        "MAP " + domain + " 0.0.0.0",
                        "MAP *." + domain + " 0.0.0.0"
                ).stream())
                .collect(Collectors.joining(","));
        return "--host-resolver-rules=" + rules;
    }




}
