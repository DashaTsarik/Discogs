package com.discogs.automation;

import com.discogs.automation.utils.Browser;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        String url = "http://localhost:4444/wd/hub/";
        ChromeOptions options = new ChromeOptions();
        List<String> arguments = new ArrayList<>();
        arguments.add("disable-extensions");
        arguments.add("start-maximized");
        options.addArguments(arguments);
        RemoteWebDriver webDriver;
        try{
            webDriver = new RemoteWebDriver(new URL(url), options);
            webDriver.get("https://www.discogs.com/search/advanced");
        }catch(MalformedURLException e){
            System.out.println(e.getMessage());
        }
    }
}
