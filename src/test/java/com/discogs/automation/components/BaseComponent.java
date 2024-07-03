package com.discogs.automation.components;

import org.openqa.selenium.*;

import java.util.List;

public class BaseComponent implements WebElement {
    public WebElement holder;
    protected WebDriver driver;

    public BaseComponent(WebElement holder, WebDriver driver){
        this.driver = driver;
        this.holder = holder;
    }
    @Override
    public void click() {
        holder.click();
    }

    @Override
    public void submit() {
        holder.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        holder.sendKeys();
    }

    @Override
    public void clear() {
        holder.clear();
    }

    @Override
    public String getTagName() {
        return holder.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return holder.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return holder.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return holder.isEnabled();
    }

    @Override
    public String getText() {
        return holder.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return holder.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return holder.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return holder.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return holder.getLocation();
    }

    @Override
    public Dimension getSize() {
        return holder.getSize();
    }

    @Override
    public Rectangle getRect() {
        return holder.getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return holder.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return holder.getScreenshotAs(target);
    }
}
