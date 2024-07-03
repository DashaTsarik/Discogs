package com.discogs.automation.pages;

import com.discogs.automation.utils.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VinylCardPage extends BasePage{

    public VinylCardPage(WebDriver driver) {
        super(driver);
    }
    private static final String INFO_TABLE_HEADER_SELECTOR = "//th[contains(text(),'%s')]";
    //div[@class='info_23nnx']//th[contains(text(),'Genre')]/following-sibling::td/a
    private static final String INFO_TABLE_DATA_SELECTOR = "./following-sibling::td/a";

    private WebElement getVinylTableHeader(InputType inputType){
       return Awaitility.waitForElement(By.xpath(String.format(INFO_TABLE_HEADER_SELECTOR,inputType.getInfoHeaderName())));
    }

    public String getTextOfInfoTable(InputType inputType){
        return getVinylTableHeader(inputType).findElement(By.xpath(INFO_TABLE_DATA_SELECTOR)).getText();
    }



}
