package by.a1qa.vkapi.test;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import by.a1qa.vkapi.data.ConfigDataManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    private Browser browser;

    @BeforeMethod
    public void initializeTestScenario() {
        browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(ConfigDataManager.getURL());
    }

    @AfterMethod
    public void finalizeTestScenario(){
        browser.quit();
    }
}
