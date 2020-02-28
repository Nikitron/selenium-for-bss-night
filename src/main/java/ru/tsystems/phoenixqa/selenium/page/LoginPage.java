package ru.tsystems.phoenixqa.selenium.page;

import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Selector;
import ru.tsystems.phoenixqa.selenium.element.PhoenixElement;

public interface LoginPage extends WebPage {
    @FindBy(value = "form#loginform input#UserName", selector = Selector.CSS)
    PhoenixElement usernameTextInput();

    @FindBy(value = "form#loginform input[type=password]", selector = Selector.CSS)
    PhoenixElement passwordTextInput();

    @FindBy(value = "form#loginform input[type=submit]", selector = Selector.CSS)
    PhoenixElement loginButton();
}
