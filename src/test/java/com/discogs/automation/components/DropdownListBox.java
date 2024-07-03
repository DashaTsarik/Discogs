package com.discogs.automation.components;

import com.discogs.automation.utils.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class DropdownListBox extends BaseComponent {

    public static final String DEFAULT_SELECTOR = "div._desktop_xf68f_53";
    private static final String DROPDOWN_SELECTOR = "button";
    private static final String DROPDOWN_SELECTOR_BY_NAME = "//button[contains(text(),'%s')]";
    private static final String DROPDOWN_ITEM_SELECTOR = "/following-sibling::div//a";
    private static final String DROPDOWN_ITEM_SELECTOR_BY_NAME = "/following-sibling::div//a[contains(text(),'%s')]";


    public DropdownListBox(WebElement holder, WebDriver driver) {
        super(holder,driver);
    }

    public List<String> getDropdownNames(){
        return findElements(By.cssSelector(DROPDOWN_SELECTOR))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }

    private WebElement getDropdownByName(String dropdownName){
        return findElement(By.xpath(String.format(DROPDOWN_SELECTOR_BY_NAME, dropdownName)));
    }

    public void selectDropdown(String dropdownName){
        getDropdownByName(dropdownName).click();
    }

    public List<String> getDropdownItemsNames(String dropdownName, String dropdownItemName){
        selectDropdown(dropdownName);
        return getDropdownByName(dropdownName).
                findElements(By.xpath(DROPDOWN_ITEM_SELECTOR))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void selectDropDownItem(String dropdownName, String dropdownItemName){
        selectDropdown(dropdownName);
        getDropdownByName(dropdownName).
                findElement(By.xpath(String.format(DROPDOWN_ITEM_SELECTOR_BY_NAME,dropdownItemName)))
                .click();
    }
}
