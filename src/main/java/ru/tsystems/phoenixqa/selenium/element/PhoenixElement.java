package ru.tsystems.phoenixqa.selenium.element;

import io.qameta.atlas.webdriver.AtlasWebElement;
import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;

public interface PhoenixElement extends AtlasWebElement<PhoenixElement> {
    PhoenixElement check(Matcher<? extends WebElement> matcher);

    PhoenixElement check(String reason, Matcher<? extends WebElement> matcher);
}
