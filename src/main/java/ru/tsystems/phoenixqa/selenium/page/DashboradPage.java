package ru.tsystems.phoenixqa.selenium.page;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Selector;
import ru.tsystems.phoenixqa.selenium.block.WithNavigation;

public interface DashboradPage extends WebPage, WithNavigation {
    @FindBy(value = "div[data-name='pryaniky/virtcurrency'] span[data-bind*='mythanksCount']", selector = Selector.CSS)
    AtlasWebElement megabyteCountTextBlock();
}
