package ru.tsystems.phoenixqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.tsystems.phoenixqa.selenium.page.OldStyleLoginPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SmokeTests extends AbstractBaseSeleniumTests {
    public static final String DASHBOARD_PAGE_URL = "https://intra.t-systems.ru/dash";
    public static final String LOGIN_PAGE_URL = "https://intra.t-systems.ru/Account/LogOn";


    @Test
    public void testLoginInIntra() {
        webDriver.get(DASHBOARD_PAGE_URL);

        var currentUrl = webDriver.getCurrentUrl();
        assertThat("Login page is not opened", currentUrl, containsString(LOGIN_PAGE_URL));

        onLoginPage().usernameTextInput().sendKeys("nsontsev");
        onLoginPage().passwordTextInput().sendKeys(PASSWORD);
        onLoginPage().loginButton().click();

        currentUrl = webDriver.getCurrentUrl();
        assertThat("Login page still open", currentUrl, containsString(DASHBOARD_PAGE_URL));

        var megabyteCount = Integer.parseInt(onDashPage().megabyteCountTextBlock().getText());
        assertThat("Megabyte count is not enough", megabyteCount, is(greaterThan(100)));
    }

    @Test
    @Deprecated
    public void testLoginInIntraInOldStylePageObject() {
        webDriver.get(DASHBOARD_PAGE_URL);

        var currentUrl = webDriver.getCurrentUrl();
        assertThat("Login page is not opened", currentUrl, containsString(LOGIN_PAGE_URL));

        var loginPage = PageFactory.initElements(webDriver, OldStyleLoginPage.class);

        loginPage.login("nsontsev", PASSWORD);

        currentUrl = webDriver.getCurrentUrl();
        assertThat("Login page still open", currentUrl, containsString(DASHBOARD_PAGE_URL));

    }
}
