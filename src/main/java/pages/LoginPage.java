package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LoginPage extends BasePage {
    // Locators
    private final String emailField = "input[type='email']";
    private final String passwordField = "input[type='password']";
    private final String submitButton = "button[type='submit']";

    public LoginPage(Page page) {
        super(page);
    }

    public void login(String email, String password) {
        // Wait for email field to be visible and enabled
        page.waitForSelector(emailField, new Page.WaitForSelectorOptions()
            .setState(WaitForSelectorState.VISIBLE)
            .setTimeout(3000));
        page.fill(emailField, email);

        // Wait for password field to be visible and enabled
        page.waitForSelector(passwordField, new Page.WaitForSelectorOptions()
            .setState(WaitForSelectorState.VISIBLE)
            .setTimeout(3000));
        page.fill(passwordField, password);

        // Wait for submit button to be visible and enabled
        page.waitForSelector(submitButton, new Page.WaitForSelectorOptions()
            .setState(WaitForSelectorState.VISIBLE)
            .setTimeout(3000));
        page.click(submitButton);

        // Wait for navigation after login
        page.waitForURL("http://localhost:4200/#/organisations", new Page.WaitForURLOptions()
            .setTimeout(5000));
    }
} 