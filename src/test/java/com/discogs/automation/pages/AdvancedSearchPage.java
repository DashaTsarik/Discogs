package com.discogs.automation.pages;

import com.discogs.automation.utils.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.Map;

public class AdvancedSearchPage extends BasePage {

    public AdvancedSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

        private static final String INPUT_SELECTOR = "input#%s";
        //private static final String STYLE_INPUT_SELECTOR = "input#style";
        //private static final String YEAR_INPUT_SELECTOR = "input#year";
        private static final String SUBMIT_SEARCH_BUTTON = "button#advanced_search_form_submit";

        /*@FindBy(css = STYLE_INPUT_SELECTOR)
        private WebElement styleInput;
        @FindBy(css = YEAR_INPUT_SELECTOR)
        private WebElement yearInput;*/
        @FindBy(css = SUBMIT_SEARCH_BUTTON)
        private WebElement submitSearchButton;

        public void sendKeysToInputSelector(InputType inputType, String keys) {
            Browser.getDriver()
                    .findElement(By.cssSelector(String.format(INPUT_SELECTOR, inputType.getInputId())))
                    .sendKeys(keys);
        }

        private void submitSearch() {
            submitSearchButton.submit();
        }

        //Map<String,String> inputValues это idInput и его value
        public SearchResultPage fillInSearchForm(Map<InputType, String> inputValues) {
            inputValues.keySet().stream().forEach(x -> sendKeysToInputSelector(x, inputValues.get(x)));
            submitSearch();
            return new SearchResultPage(driver);
        }

}
