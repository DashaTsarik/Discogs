package com.discogs.automation.utils;

import com.jayway.awaitility.core.ConditionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;

public class Awaitility {
    private static final WebDriver driver = Browser.getDriver();

    public static WebElement waitForElement(By by){
        getConditionFactory().until(()-> driver.findElement(by).isDisplayed());
        return driver.findElement(by);
    }

    public static WebElement waitForElement(WebElement element){
        getConditionFactory().until(()-> element.isDisplayed());
        return element;
    }
    public static List<WebElement> waitForElements(By by){
        getConditionFactory().until(()-> driver.findElements(by).size()>=1);
        return driver.findElements(by);
    }
    public static WebElement waitForElementBeClickable(By by){
        getConditionFactory().until(()-> ExpectedConditions.elementToBeClickable(by));
        return driver.findElement(by);
    }

    public static WebElement waitForNestedElementClickable(WebElement element,By by){
        getConditionFactory().until(() -> ExpectedConditions.elementToBeClickable(element.findElement(by)));
        return element.findElement(by);
    }

    private static ConditionFactory getConditionFactory(){
        return await().ignoreExceptions().atMost(30, TimeUnit.SECONDS);
    }

}
