package ru.tsystems.phoenixqa.selenium.page;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Selector;

public interface LoginPage extends WebPage {
    @FindBy(value = "form#loginform input#UserName", selector = Selector.CSS)
    AtlasWebElement usernameTextInput();

    @FindBy(value = "form#loginform input[type=password]", selector = Selector.CSS)
    AtlasWebElement passwordTextInput();

    @FindBy(value = "form#loginform input[type=submit]", selector = Selector.CSS)
    AtlasWebElement loginButton();
}
