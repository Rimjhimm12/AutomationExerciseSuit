package com.qa.ae.pages;

import com.qa.ae.exceptions.BrowserExceptions;
import com.qa.ae.utils.ElementUtil;
import org.openqa.selenium.By;
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

    public boolean isAccountDeleted() {
        WebElement element = elementUtil.waitForElementVisible(ACCOUNT_DELETED_HEADER, 10);
        if (element.isDisplayed()) {
            boolean flag = element.isEnabled();
            String accountDeletedHeader = elementUtil.doGetText(ACCOUNT_DELETED_HEADER);
            System.out.println("Account created header is: " + accountDeletedHeader);
            return flag;
        }
        else {
            throw new BrowserExceptions("Account deletion failed, header not displayed.");
        }

    }

    public HomePage doContinue(){
        elementUtil.doActionsClick(CONTINUE_BUTTON);
        return new HomePage(driver);

    }




}
