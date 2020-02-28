package ru.tsystems.phoenixqa.selenium;

import io.qameta.allure.Step;
import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import lombok.val;
import org.hamcrest.Matcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import ru.tsystems.phoenixqa.selenium.atlas.CheckMethodExtension;
import ru.tsystems.phoenixqa.selenium.page.DashboradPage;
import ru.tsystems.phoenixqa.selenium.page.LoginPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public abstract class AbstractBaseSeleniumTests {
    public static final String SELENIUM_HUB_URL = "http://127.0.0.1:4444/wd/hub";
    public static final String PASSWORD = System.getenv("TEST_PASSWORD");

    protected WebDriver webDriver;
    private static ThreadLocal<WebDriver> WEB_DRIVER = ThreadLocal.withInitial(() -> null);
    private static ThreadLocal<SoftAssert> SOFT_ASSERT =ThreadLocal.withInitial(() -> null);
    protected Atlas atlas;

    @BeforeMethod
    @Step("Create new WebDriver and create Atlas")
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
        atlas.extension(new CheckMethodExtension());

        WEB_DRIVER.set(webDriver);
        SOFT_ASSERT.set(new SoftAssert());
    }

    @AfterMethod
    @Step("Close WebDriver")
    public void closeWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
        for (val errorMessage: CheckMethodExtension.errorList.get()) {
            SOFT_ASSERT.get().assertTrue(false, errorMessage);
        }
        SOFT_ASSERT.get().assertAll();
    }

    public static WebDriver getWebDriver() {
        return WEB_DRIVER.get();
    }

    protected LoginPage onLoginPage(){
        return atlas.create(webDriver, LoginPage.class);
    }

    protected DashboradPage onDashPage(){
        return atlas.create(webDriver, DashboradPage.class);
    }
}
