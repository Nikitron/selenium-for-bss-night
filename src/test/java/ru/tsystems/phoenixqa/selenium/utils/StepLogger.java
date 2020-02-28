package ru.tsystems.phoenixqa.selenium.utils;

import io.qameta.allure.Attachment;
import io.qameta.allure.listener.StepLifecycleListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.tsystems.phoenixqa.selenium.AbstractBaseSeleniumTests;

public class StepLogger implements StepLifecycleListener {
    @Override
    public void beforeStepStop(io.qameta.allure.model.StepResult result) {
        byte[] bytes = ((TakesScreenshot) AbstractBaseSeleniumTests.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        var name = result.getName();
        saveScreenshot(name, bytes);
    }

    @Attachment(value = "{name}", type = "image/png")
    public byte[] saveScreenshot(String name, byte[] screenShot) {
        return screenShot;
    }
}
