package ru.tsystems.phoenixqa.selenium;

import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.tsystems.phoenixqa.selenium.page.DashboradPage;
import ru.tsystems.phoenixqa.selenium.page.LoginPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public abstract class AbstractBaseSeleniumTests {
    public static final String SELENIUM_HUB_URL = "http://127.0.0.1:4444/wd/hub";
    public static final String PASSWORD = System.getenv("PASSWORD_FOR_SELENIUM");

    protected WebDriver webDriver;
    protected Atlas atlas;

    @BeforeMethod
    public void initWebDriver() {
        var options = new ChromeOptions();
        options.addArguments("start-maximized");
        try {
            webDriver = new RemoteWebDriver(new URL(SELENIUM_HUB_URL), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Incorrect url for selenium hub. Please fix!", e);
        }
        webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        atlas = new Atlas(new WebDriverConfiguration(webDriver));
    }

    @AfterMethod
    public void closeWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    protected LoginPage onLoginPage(){
        return atlas.create(webDriver, LoginPage.class);
    }

    protected DashboradPage onDashPage(){
        return atlas.create(webDriver, DashboradPage.class);
    }
}
