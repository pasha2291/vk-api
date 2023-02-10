package by.a1qa.vkapi.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LogInPage extends Form {

    private static final By LOGIN_FORM_LOCATOR = By.id("index_login");
    private static final By PHONE_INPUT_FIELD_LOCATOR = By.xpath("//input[@id='index_email']");
    private static final By PASSWORD_INPUT_FIELD_LOCATOR = By.xpath("//input[@type='password']");
    private static final By SIGN_IN_BUTTON_LOCATOR = By.xpath("//button[@type='submit']/span");
    private static final By MY_PAGE_BUTTON_LOCATOR = By.id("l_pr");
    private final IButton signInButton = getElementFactory().getButton(SIGN_IN_BUTTON_LOCATOR, "Sign in button");

    public LogInPage() {
        super(LOGIN_FORM_LOCATOR, "Log in form");
    }

    public void logIn(String phone, String password) {
        getElementFactory().getTextBox(PHONE_INPUT_FIELD_LOCATOR, "Phone input field").clearAndType(phone);
        signInButton.click();
        getElementFactory().getTextBox(PASSWORD_INPUT_FIELD_LOCATOR, "Password input field").clearAndType(password);
        signInButton.click();
    }

    public MyPage myPageButtonClick() {
        getElementFactory().getButton(MY_PAGE_BUTTON_LOCATOR, "My page button").click();
        MyPage myPage = new MyPage();
        AqualityServices.getConditionalWait().waitFor(() -> new MyPage().state().isDisplayed());
        return myPage;
    }
}
