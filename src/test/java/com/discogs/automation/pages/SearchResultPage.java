package com.discogs.automation.pages;

import com.discogs.automation.components.VinylContainer;
import com.discogs.automation.utils.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultPage extends BasePage{
    private static final String APPLIED_FILTERS_HOLDER = "//div[@class='hide_mobile']";
    private static final String APPLIED_FILTERS_LIST_SELECTOR = "//div[@class='hide_mobile']//a";
    //[normalize-space(text())='%s']
    @FindBy(xpath = APPLIED_FILTERS_HOLDER)
    private WebElement appliedFiltersHolder;
    @FindBy(css = VinylContainer.VINYL_CONTAINER_SELECTOR)
    private WebElement vinylContainerHolder;

    /**
     * Constructor injecting the WebDriver interface
     *
     * @param driver
     */
    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    private List<WebElement> getAppliedFilters(){
        return Awaitility.waitForElements(By.xpath(APPLIED_FILTERS_LIST_SELECTOR));
    }

    public List<String> getFiltersNames(){
        List<String> filtersNames = getAppliedFilters().stream().map(x->x.getText().trim()).collect(Collectors.toList());
        return filtersNames;
    }

    public VinylContainer getVinylContainer(){
        closeBanner();
        return new VinylContainer(Awaitility.waitForElement(vinylContainerHolder),driver);
    }
}
