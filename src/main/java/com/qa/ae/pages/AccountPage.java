package com.qa.ae.pages;

import com.qa.ae.exceptions.BrowserExceptions;
import com.qa.ae.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPage {
    private final WebDriver driver;
    private final ElementUtil elementUtil;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);
    }

    private final By ACCOUNT_CREATED_HEADER = By.xpath("//b[text()='Account Created!']");
    private final By ACCOUNT_DELETED_HEADER = By.xpath("//b[text()='Account Deleted!']");
    private final By CONTINUE_BUTTON = By.linkText("Continue");
    private final By AD_IFRAME = By.cssSelector("iframe");
    private final By AD_CLOSE_BUTTON = By.cssSelector("[id*='close'], [class*='close'], .close");


    /**
     * Check if account is created successfully by verifying the presence of "Account Created!" header.
     * @return boolean value
     */
    public boolean isAccountCreated() {
        WebElement element = elementUtil.waitForElementVisible(ACCOUNT_CREATED_HEADER, 10);
        if (element.isDisplayed()) {
            boolean flag = element.isEnabled();
            String accountCreatedHeader = elementUtil.doGetText(ACCOUNT_CREATED_HEADER);
            System.out.println("Account created header is: " + accountCreatedHeader);
            return flag;
        }
        else {
            throw new BrowserExceptions("Account creation failed, header not displayed.");
        }

    }

    /**
     * Check if account is deleted successfully by verifying the presence of "Account Deleted!" header.
     * @return boolean value
     */

    public boolean isAccountDeleted() {
        elementUtil.closeAdIfPresent(AD_IFRAME, AD_CLOSE_BUTTON);
        scrollDeletedHeaderIntoView();
        WebElement element = elementUtil.waitForElementVisible(ACCOUNT_DELETED_HEADER, 10);
        if (element.isDisplayed()) {
            boolean flag = element.isEnabled();
            String accountDeletedHeader = elementUtil.doGetText(ACCOUNT_DELETED_HEADER);
            System.out.println("Account deleted header is: " + accountDeletedHeader);
            return flag;
        }
        else {
            throw new BrowserExceptions("Account deletion failed, header not displayed.");
        }

    }

    /**
     * Click on Continue button after account creation or deletion and navigate to Home page.
     * @return After clicking "Continue" button, it returns an instance of HomePage class.
     */

    public HomePage doContinue(){
        elementUtil.doActionsClick(CONTINUE_BUTTON);
        return new HomePage(driver);

    }

    private void scrollDeletedHeaderIntoView() {
        try {
            WebElement deletedHeader = driver.findElement(ACCOUNT_DELETED_HEADER);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deletedHeader);
        } catch (Exception e) {
            driver.switchTo().defaultContent();
        }
    }




}
