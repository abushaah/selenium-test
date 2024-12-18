package com.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CrossBrowserTestingScript {

    @Test(dataProvider = "browsers")
    public void crossBrowserTest(String browser) {
        WebDriver driver;

        if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "C:\\University\\UniversityCourseDocuments\\F24\\4150\\researchProject\\selenium-test\\browser-drivers\\edgedriver_win64\\msedgedriver.exe");
            
            // Browser specific configurations, EdgeOptions
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--remote-allow-origins=*");
            
            driver = new EdgeDriver(options);
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\University\\UniversityCourseDocuments\\F24\\4150\\researchProject\\selenium-test\\browser-drivers\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

            // Browser specific configurations, ChromeOptions
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\University\\UniversityCourseDocuments\\F24\\4150\\researchProject\\selenium-test\\browser-drivers\\geckodriver-v0.35.0-win32\\geckodriver.exe");
            // No browser specific configurations required
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        driver.get("https://avianfludashboard.ca/");
        System.out.println("Title is: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "UOG Avian Influenza");
        driver.quit();
    }

    @DataProvider(name = "browsers")
    public Object[][] getBrowsers() {
        return new Object[][] {
            { "Firefox" },
            { "Chrome" },
            { "Edge" }
        };
    }
}