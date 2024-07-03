package com.discogs.automation.utils;

import com.discogs.automation.config.SuiteConfiguration;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Browser {

    private static Duration DEFAULT_WAIT_IN_SECONDS = Duration.ofSeconds(30);
    private static SuiteConfiguration suiteConfig = new SuiteConfiguration();

    private static WebDriver driver;
    private static JavascriptExecutor js;


    private Browser()  {
    }

    public static void initDriver() {
        WebDriverManager.setupWebDriver(suiteConfig);
        driver = WebDriverManager.getDriver(suiteConfig.getCapabilities());
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static JavascriptExecutor getJs(){
        js = (JavascriptExecutor) Browser.getDriver();
        return js;
    }

    public static void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebElement waitForElementPresentedOnPage(By locator) {
            return new WebDriverWait(getDriver(), DEFAULT_WAIT_IN_SECONDS)
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public static WebElement waitForNestedElementPresented(WebElement element, By locator){
            return new WebDriverWait(getDriver(),DEFAULT_WAIT_IN_SECONDS).
                    until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, locator));
    }
    public static WebElement waitForElementVisible(By locator){
            return new WebDriverWait(getDriver(), DEFAULT_WAIT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static WebElement waitForElementClickable(By locator) {
            return new WebDriverWait(getDriver(), DEFAULT_WAIT_IN_SECONDS).until(ExpectedConditions.presenceOfElementLocated(locator));

    }

    public static List<WebElement> waitForElements(By locator) {

            return new WebDriverWait(getDriver(), DEFAULT_WAIT_IN_SECONDS).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static WebElement scrollPageUntilElementNeeded(WebElement element) {
        getJs().executeScript("arguments[0].scrollIntoView();", element);
        return element;
    }

    public static void hoverOnElement(WebElement element){
        Actions action = new Actions(getDriver());
        action
                .moveToElement(element)
                .perform();
    }

    public static void sendKeysThroughJS(String value, WebElement element){
        getJs().executeScript(String.format("arguments[0].value='%s';",value),element);
    }

    public static void switchToFrame(String nameOfFrame){
        Browser.getDriver().switchTo().frame(nameOfFrame);
    }

    public static void switchToDefaultContent(){
        Browser.getDriver().switchTo().defaultContent();
    }

    public static void acceptAlert(){
        Browser.getDriver().switchTo().alert().accept();
    }
}
