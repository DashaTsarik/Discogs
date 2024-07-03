package com.discogs.automation.components;

import com.discogs.automation.utils.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchBar extends BaseComponent{
    public static final String SEARCH_FORM_SELECTOR = "form[role='search']";
    private static final String SEARCH_INPUT_SELECTOR = "input:first-child";
    private static final String SUBMIT_SEARCH_BUTTON_SELECTOR = "button";
    private static final String ADVANCED_SEARCH_BUTTON_SELECTOR = "a._advanced_1otse_323";

    public SearchBar(WebElement holder, WebDriver driver) {
        super(holder,driver);
    }

    public SearchBar sendKeys(String key){
        findElement(By.cssSelector(SEARCH_INPUT_SELECTOR)).sendKeys(key);
        return this;
    }
    public void submitSearch(){
        findElement(By.cssSelector(SUBMIT_SEARCH_BUTTON_SELECTOR)).submit();
    }

    public void submitAdvancedSearch(){
        sendKeys("any");
        Awaitility.waitForElement(By.cssSelector(ADVANCED_SEARCH_BUTTON_SELECTOR)).click();
    }


}
