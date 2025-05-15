package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class ProjectPage extends BasePage {
    // Locators
    private final String createProjectButton = "//span[@class='material-icons-outlined text-white']";
    private final String createNewProjectOption = "//li[normalize-space(text())='Create New Project']";
    private final String entityDropdown = ".mat-select-arrow-wrapper.ng-tns-c108-29";
    private final String projectNameInput = "input[id='2']";
    private final String implementationDropdown = ".mat-select-arrow.ng-tns-c108-31";
    private final String startDateInput = "(//input[@id='6'])[1]";
    private final String projectPageLoader = "//div[contains(@class, 'loading-spinner')]";
    private final String projectListing = ".w-100.ng-star-inserted";

    public ProjectPage(Page page) {
        super(page);
    }

    public void waitForProjectPageLoad() {
        System.out.println("Waiting for project page to load completely...");
        try {
            // Wait for loading spinner to disappear
            page.waitForSelector(projectPageLoader, new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.HIDDEN)
                .setTimeout(30000));
            System.out.println("Project page loaded successfully");
        } catch (Exception e) {
            System.out.println("Error waiting for project page load: " + e.getMessage());
            throw e;
        }
    }

    public void clickCreateProject() throws Exception {
        System.out.println("Starting project creation process...");
        try {
        	Thread.sleep(8000);
            // Wait for project listing to be visible before proceeding
            page.waitForSelector(projectListing, new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(20000));
            // Click the + icon
            System.out.println("Clicking + icon...");
            page.click(createProjectButton);
            
            // Wait for and click the Create New Project option
            System.out.println("Selecting Create New Project option...");
            page.waitForSelector(createNewProjectOption, new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(3000));
            page.click(createNewProjectOption);
            
            System.out.println("Project creation process initiated successfully");
        } catch (Exception e) {
            System.out.println("Error in project creation process: " +
        e.getMessage());
            throw e;
        }
    }

    public void selectEntity(String entity) throws InterruptedException {
        System.out.println("Selecting project type: " + entity);
       // waitForProjectPageLoad();
        Thread.sleep(4000);
        page.waitForSelector(entityDropdown, new Page.WaitForSelectorOptions()
            .setState(WaitForSelectorState.VISIBLE)
            .setTimeout(5000));
        page.click(entityDropdown);
        System.out.println("Enitity Dropdown clicked"+ entity);
        page.fill("//input[@placeholder=\"Search Select Entity\"]", entity);
        Thread.sleep(4000);
        page.click("//div[@class='d-flex flex-column mt-2']//span[contains(text(),'MPLADS')]");
        System.out.println("entity selected");
        // Press ESC to ensure the dropdown is closed
        page.keyboard().press("Escape");
      
       
        
    }

    public void enterProjectName(String name) {
    	
        System.out.println("Entering project name: " + name);
        page.waitForSelector(projectNameInput, new Page.WaitForSelectorOptions()
            .setState(WaitForSelectorState.VISIBLE)
            .setTimeout(3000));
        page.fill(projectNameInput, name);
        System.out.println("Project name entered");
    }

    public void selectImplementation(String implementation) {
        System.out.println("Selecting implementation: " + implementation);
        page.waitForSelector(implementationDropdown, new Page.WaitForSelectorOptions()
            .setState(WaitForSelectorState.VISIBLE)
            .setTimeout(3000));
        page.click(implementationDropdown);
        page.fill("//input[@placeholder='Search Implementation Type ']", implementation);
        page.click("//span[contains(text(),'NGO (Partner NGO)')]");
        System.out.println("Implementation selected");
    }

    public void selectStartDate(String date) {
        System.out.println("Selecting start date: " + date);
        page.waitForSelector(startDateInput, new Page.WaitForSelectorOptions()
            .setState(WaitForSelectorState.VISIBLE)
            .setTimeout(3000));
        page.fill(startDateInput, date);
        System.out.println("Start date entered");
    }
} 