package com.discogs.automation;

import com.discogs.automation.pages.InputType;
import com.discogs.automation.utils.Browser;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println(InputType.YEAR.name());
    }
}
