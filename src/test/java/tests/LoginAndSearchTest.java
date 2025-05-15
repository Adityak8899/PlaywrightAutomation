package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import pages.LoginPage;
import pages.OrganizationPage;
import pages.ProjectPage;

public class LoginAndSearchTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;
    private LoginPage loginPage;
    private OrganizationPage orgPage;
    private ProjectPage projectPage;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
            .setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        loginPage = new LoginPage(page);
        orgPage = new OrganizationPage(page);
        projectPage = new ProjectPage(page);
    }

    @Test(priority = 1)
    public void testLogin() {
        System.out.println("Starting login test...");
        // Navigate to the application
        page.navigate("http://localhost:4200/");
        // Login
        loginPage.login("aditya.kannojia@dhwaniris.com", "Aditya@123");
        System.out.println("Login test completed");
    }

    @Test(priority = 2)
    public void testSearchAndShowProjects() {
        System.out.println("Starting organization search test...");
        // Search for organization
        orgPage.searchOrganization("not-Disclosed");
        // Click on the organization and show its projects
        orgPage.clickShowProjects();
        System.out.println("Organization search test completed");
    }

    @Test(priority = 3)
    public void testCreateProject() throws Exception {
        System.out.println("Starting project creation test...");
        // Create new project
        projectPage.clickCreateProject();
        projectPage.selectEntity("MPLADS");
       
        projectPage.enterProjectName("Playright automation");
   
        projectPage.selectImplementation("Ngo");
        projectPage.selectStartDate("01/01/2024");
        System.out.println("Project creation test completed");
    }

    @AfterClass
    public void tearDown() {
        context.close();
        browser.close();
        playwright.close();
    }
} 