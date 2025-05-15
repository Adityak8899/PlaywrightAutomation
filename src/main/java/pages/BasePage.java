package pages;

import com.microsoft.playwright.Page;

public class BasePage {
    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public void waitForPageLoad() {
        page.waitForLoadState();
    }

    public void navigate(String url) {
        page.navigate(url);
        waitForPageLoad();
        System.out.println("abcd");
    }
} 