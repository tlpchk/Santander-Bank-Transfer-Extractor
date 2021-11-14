package com.example.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class SeleniumConfiguration {
    private static final String CHROME_DRIVER_NAME = "chrome";

    @Value("${selenium.driver:chrome}")
    private String driverName;

    @Bean
    @Lazy
    WebDriver seleniumDriver() {
        if (driverName.equals(CHROME_DRIVER_NAME)) {
            return new ChromeDriver();
        }
        throw new RuntimeException(String.format("WebDriver '%s' is invalid", driverName));
    }
}
