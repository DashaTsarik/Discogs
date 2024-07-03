package com.discogs.automation.pages;

import com.discogs.automation.components.DropdownListBox;
import com.discogs.automation.components.SearchBar;
import com.discogs.automation.utils.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.jayway.awaitility.Awaitility.await;

public abstract class BasePage {
    private static final String HOME_LINK_SELECTOR = "div._wrap_xf68f_34>a";
    private static final String ACCEPT_COOKIE_BUTTON_SELECTOR = "button#onetrust-accept-btn-handler";
    private static final String BANNER_CLOSE_BUTTON_SELECTOR = "button#move";
    @FindBy(css = BANNER_CLOSE_BUTTON_SELECTOR)
    private WebElement bannerCloseButton;
    @FindBy(css = "button#onetrust-accept-btn-handler")
    private WebElement acceptCookieButton;
    @FindBy(css = SearchBar.SEARCH_FORM_SELECTOR)
    private WebElement searchBarHolder;
    @FindBy(css = DropdownListBox.DEFAULT_SELECTOR)
    private WebElement dropdownListBoxHolder;
    @FindBy(css = HOME_LINK_SELECTOR)
    private WebElement homePageButton;
    protected WebDriver driver;

    /**
     * Constructor injecting the WebDriver interface
     * @param webDriver
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
    public HomePage navigateToHomePage(){
        Awaitility.waitForElement(homePageButton).click();
        return new HomePage(driver);
    }

    private SearchBar getSearchBar(){
        return new SearchBar(searchBarHolder,driver);
    }

    private DropdownListBox getDropdownListBox(){
        return new DropdownListBox(dropdownListBoxHolder,driver);
    }

    public SearchResultPage searchByText(String text){
        getSearchBar().sendKeys(text).submitSearch();
        return new SearchResultPage(driver);
    }

    public AdvancedSearchPage advancedSearchByText(){
        getSearchBar().submitAdvancedSearch();
        return new AdvancedSearchPage(driver);
    }

    public void acceptCookies(){
        Awaitility.waitForElement(acceptCookieButton).click();
    }

    public void closeBanner(){
        bannerCloseButton.click();
    }
}
