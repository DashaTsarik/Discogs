package com.discogs.automation.config;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SuiteConfiguration {
    private static final String APPLICATION_PROPERTIES = "/application.properties";

    private Properties properties = new Properties();

    //получаем проперти app.properties, если их нет устанавливаем их по дефолту,
    //System.getProperties возвращает список системных свойств (параметров окружения), доступных для данной
    //виртуальной машины.Свойство представляет из себя пару ключ-значение.
    public SuiteConfiguration() {
        this(System.getProperty("application.properties", APPLICATION_PROPERTIES));
    }

    public SuiteConfiguration(String fromResource) {
        try {
            //reads a property list(key and element pairs) from the input byte stream
            properties.load(SuiteConfiguration.class.getResourceAsStream(APPLICATION_PROPERTIES));
            properties.load(SuiteConfiguration.class.getResourceAsStream(fromResource));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Capabilities getCapabilities() {
        //считывает свойство из файла Application.properties значение capabilities
        //в данном случае chrome.capabilities
        String capabilitiesFile = getProperty("capabilities");

        //отдельно создаем свойства для capabilities
        Properties capsProps = new Properties();
        //создание самого объекта Capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();

        try {

            capsProps.load(SuiteConfiguration.class.getResourceAsStream(capabilitiesFile));

            for (String name : capsProps.stringPropertyNames()) {
                String value = capsProps.getProperty(name);
                if (value.toLowerCase().equals("true") || value.toLowerCase().equals("false")) {
                    capabilities.setCapability(name, Boolean.valueOf(value));
                } else if (value.startsWith("file:")) {
                    //????????
                    capabilities.setCapability(name, new File(".", value.substring(5)).getCanonicalFile().getAbsolutePath());
                } else {
                    capabilities.setCapability(name, value);
                }

            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

            //берет свойства для мобильной эмуляции, если указано
            String deviceName = getProperty("device.name");
            if (!deviceName.isEmpty()) {
                Map<String, Object> deviceMetrics = new HashMap<>();
                deviceMetrics.put("width", Integer.valueOf(getProperty("device.width")));
                deviceMetrics.put("height", Integer.valueOf(getProperty("device.height")));
                deviceMetrics.put("pixelRatio", Integer.valueOf(getProperty("pixel.ratio")));

                Map<String, Object> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceMetrics", deviceMetrics);
                mobileEmulation.put("userAgent", getProperty("user.agent"));
                Map<String, Object> chromeOptions = new HashMap<>();
                chromeOptions.put("mobileEmulation", mobileEmulation);
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            }

        return capabilities;
    }

    public boolean hasProperty(String name) {
        return properties.contains(name);
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }
}
