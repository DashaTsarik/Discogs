package com.discogs.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{
    /**
     * Constructor injecting the WebDriver interface
     *
     * @param driver
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }
}
