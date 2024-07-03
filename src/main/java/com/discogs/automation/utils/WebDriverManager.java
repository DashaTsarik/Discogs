package com.discogs.automation.utils;

import com.discogs.automation.config.SuiteConfiguration;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromiumDriverManager;
import io.github.bonigarcia.wdm.managers.EdgeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import io.github.bonigarcia.wdm.managers.InternetExplorerDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import ru.stqa.selenium.factory.WebDriverPool;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

public class WebDriverManager {
    private static Optional<String> gridURL;

    public static void setupWebDriver(SuiteConfiguration suiteConfiguration) {
        WebDriverManager.gridURL = Optional.of(suiteConfiguration.getProperty("grid.url"));

        switch (suiteConfiguration.getCapabilities().getBrowserName()) {
            case "firefox":
                FirefoxDriverManager.getInstance(DriverManagerType.FIREFOX)
                        .browserVersion(suiteConfiguration.getProperty("firefox-driver.version")).setup();
                break;
            case "MicrosoftEdge":
                EdgeDriverManager.getInstance(DriverManagerType.EDGE)
                        .browserVersion(suiteConfiguration.getProperty("edge-driver.version")).setup();
                break;
            case "internet explorer":
                InternetExplorerDriverManager.getInstance(DriverManagerType.IEXPLORER)
                        .browserVersion(suiteConfiguration.getProperty("ie-driver.version")).setup();
                break;
            case "chrome":
            default:
                ChromiumDriverManager.getInstance(DriverManagerType.CHROME)
                        .browserVersion(suiteConfiguration.getProperty("chrome-driver.version")).setup();
        }
    }

    public static WebDriver getDriver(Capabilities capabilities) {
        try {
            return WebDriverPool.DEFAULT.getDriver(new URL(gridURL.orElse("")), capabilities);
        } catch (SessionNotCreatedException| MalformedURLException | UnreachableBrowserException e) {
            return WebDriverPool.DEFAULT.getDriver(capabilities);
        }
    }

    public static void dismissDriver(WebDriver driver) {
        WebDriverPool.DEFAULT.dismissDriver(driver);
    }

    public static void dismissAll() {
        WebDriverPool.DEFAULT.dismissAll();
    }

}
