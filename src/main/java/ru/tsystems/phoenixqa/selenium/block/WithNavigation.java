package ru.tsystems.phoenixqa.selenium.block;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Selector;

public interface WithNavigation {
    @FindBy(value = "form[action='/New/SearchPage'] input#searchtxt", selector = Selector.CSS)
    AtlasWebElement globalSearchInput();
}
