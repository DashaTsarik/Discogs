package com.discogs.automation;

import com.discogs.automation.config.SuiteConfiguration;
import com.discogs.automation.utils.Browser;
import com.discogs.automation.utils.PropertiesLoader;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import java.util.Properties;

public class BaseTest {

    protected static String baseUrl;
    protected static Capabilities capabilities;
    protected static PropertiesLoader propertiesLoader;
    protected static String testCaseId;
    protected static int testRunId;
    protected static String msg;
    protected Properties env;
    protected WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void initTestSuite() {

        SuiteConfiguration config = new SuiteConfiguration();

        driver = Browser.getDriver();
        baseUrl = config.getProperty("site.url");
        capabilities = config.getCapabilities();
        propertiesLoader = new PropertiesLoader();

        env = new Properties();
        env.setProperty("Base URL", baseUrl);
    }

    @BeforeTest
    public void openBaseUrl() {
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
       Browser.close();
    }

}
