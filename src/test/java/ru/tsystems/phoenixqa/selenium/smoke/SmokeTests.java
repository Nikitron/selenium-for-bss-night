package ru.tsystems.phoenixqa.selenium.smoke;

import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ru.tsystems.phoenixqa.selenium.AbstractBaseSeleniumTests;
import ru.tsystems.phoenixqa.selenium.page.OldStyleLoginPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static ru.tsystems.phoenixqa.selenium.matcher.PhoenixMatchers.hasCssValue;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class SmokeTests extends AbstractBaseSeleniumTests {
    public static final String DASHBOARD_PAGE_URL = "https://intra.t-systems.ru/dash";
    public static final String LOGIN_PAGE_URL = "https://intra.t-systems.ru/Account/LogOn";

    @Test
    public void testLoginInIntra() {
        openDashboardPage();
        checkLoginPageIsOpen();
        fillLoginForm("nsontsev");
        clockOnLoginButton();
        checkDashboardPageIsOpen();
        checkThanMegabyteCountIsGreatThen(100);
    }

    @Step("Check than megabyte count is great then {count}")
    private void checkThanMegabyteCountIsGreatThen(int count) {
        var megabyteCount = Integer.parseInt(onDashPage().megabyteCountTextBlock().getText());
        assertThat("Megabyte count is not enough", megabyteCount, is(greaterThan(count)));
    }

    @Step("Check dashboard page is open")
    private void checkDashboardPageIsOpen() {
        var currentUrl = webDriver.getCurrentUrl();
        assertThat("Login page still open", currentUrl, containsString(DASHBOARD_PAGE_URL));
    }

    @Step("Click on login button")
    private void clockOnLoginButton() {
        onLoginPage().loginButton().click();
    }

    @Step("Fill login form with username '{username}'")
    private void fillLoginForm(String username) {
        onLoginPage().usernameTextInput()
                .check(hasCssValue("font-weight", is("401")))
                .sendKeys(username);
        onLoginPage().passwordTextInput().sendKeys(PASSWORD);
    }

    @Step("Check login is open")
    private void checkLoginPageIsOpen() {
        var currentUrl = webDriver.getCurrentUrl();
        assertThat("Login page is not opened", currentUrl, containsString(LOGIN_PAGE_URL));
        assert currentUrl.toLowerCase().equals(LOGIN_PAGE_URL.toLowerCase());
    }

    @Step("Open dashboard page")
    private void openDashboardPage() {
        webDriver.get(DASHBOARD_PAGE_URL);
    }

    @Test
    @Deprecated
    public void testLoginInIntraInOldStylePageObject() {
        openDashboardPage();

        checkLoginPageIsOpen();

        var loginPage = PageFactory.initElements(webDriver, OldStyleLoginPage.class);

        loginPage.login("nsontsev", PASSWORD);

        checkDashboardPageIsOpen();

    }
}
