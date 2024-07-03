package com.discogs.automation.components;

import com.discogs.automation.pages.VinylCardPage;
import com.discogs.automation.utils.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VinylContainer extends BaseComponent {

    public static final String VINYL_CONTAINER_SELECTOR = "div#pjax_container>ul";

    private static final String VINYL_CARD_SELECTOR = "li:nth-of-type(%d)";
    private static final String VINYL_CARD_TITLE_SELECTOR = "div.card-release-title a";

    public VinylContainer(WebElement holder, WebDriver driver) {
        super(holder,driver);
    }

    public VinylCardPage selectVinylCardByNumber(int numberOfCard){
        getVinylCardTitle(numberOfCard).click();
        return new VinylCardPage(driver);
    }

    private WebElement getVinylCardByNumber(int numberOfCard){
        return Awaitility.waitForElement(By.cssSelector(String.format(VINYL_CARD_SELECTOR,numberOfCard)));
    }

    public WebElement getVinylCardTitle(int numberOfCard){
       return getVinylCardByNumber(numberOfCard).findElement(By.cssSelector(VINYL_CARD_TITLE_SELECTOR));
    }
}
