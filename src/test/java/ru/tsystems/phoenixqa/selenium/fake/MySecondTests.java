package ru.tsystems.phoenixqa.selenium.fake;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MySecondTests {
    @Test
    public void testDiv() {
        int x = 10;
        int result = x / 3;
        assertThat("Division is not working", result, is(3));
    }
}
