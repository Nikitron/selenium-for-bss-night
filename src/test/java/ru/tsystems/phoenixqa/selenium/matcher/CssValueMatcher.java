package ru.tsystems.phoenixqa.selenium.matcher;

import lombok.val;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

public class CssValueMatcher extends TypeSafeMatcher<WebElement> {
    private Matcher<String> matcher;
    private String cssValueName;

    public CssValueMatcher(String cssValueName, Matcher<String> matcher) {
        this.cssValueName = cssValueName;
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(WebElement element) {
        val cssValue = element.getCssValue(this.cssValueName);
        return matcher.matches(cssValue);
    }

    @Override
    public void describeTo(Description description) {

    }
}
