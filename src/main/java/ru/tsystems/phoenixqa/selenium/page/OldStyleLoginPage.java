package ru.tsystems.phoenixqa.selenium.page;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
@Deprecated
public class OldStyleLoginPage {
    @FindBy(css = "form#loginform input#UserName")
    private WebElement usernameTextInput;

    @FindBy(css = "form#loginform input[type=password]")
    private WebElement passwordTextInput;

    @FindBy(css = "form#loginform input[type=submit]")
    private WebElement loginButton;

    public OldStyleLoginPage login(String username, String password) {
        usernameTextInput.sendKeys(username);
        passwordTextInput.sendKeys(password);
        loginButton.click();
        return this;
    }
}
