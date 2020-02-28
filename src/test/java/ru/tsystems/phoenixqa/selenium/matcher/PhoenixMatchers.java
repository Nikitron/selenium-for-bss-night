package ru.tsystems.phoenixqa.selenium.matcher;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;

import static org.hamcrest.Matchers.is;


public class PhoenixMatchers {
    public static Matcher<WebElement> hasCssValue(String cssProperty, String matcher){
        return new CssValueMatcher(cssProperty, is(matcher));
    }

    public static Matcher<WebElement> hasCssValue(String cssProperty, Matcher<String> matcher){
        return new CssValueMatcher(cssProperty, matcher);
    }
}
