package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class OrganizationPage extends BasePage {
    // Locators
    private final String searchField = "//input[@placeholder='Search by organisation name']";
    private final String showProjectsButton = "//button[normalize-space()='Show Projects']";

    public OrganizationPage(Page page) {
        super(page);
    }

    public void searchOrganization(String orgName) {
        System.out.println("Starting organization search for: " + orgName);
        
        System.out.println("Waiting for search field to be visible...");
        page.waitForSelector(searchField, new Page.WaitForSelectorOptions()
            .setState(WaitForSelectorState.VISIBLE)
            .setTimeout(30000));
        
        System.out.println("Entering organization name in search field...");
        page.fill(searchField, orgName);
        page.press(searchField, "Enter");
        System.out.println("Search initiated with Enter key");
    }

    public void clickShowProjects() {
        System.out.println("Attempting to click Show Projects button...");
        
        System.out.println("Waiting for Show Projects button to be visible...");
        page.waitForSelector(showProjectsButton, new Page.WaitForSelectorOptions()
            .setState(WaitForSelectorState.VISIBLE)
            .setTimeout(3000));
        
        System.out.println("Clicking Show Projects button...");
        page.click(showProjectsButton);
        System.out.println("Show Projects button clicked successfully");
    }
} 