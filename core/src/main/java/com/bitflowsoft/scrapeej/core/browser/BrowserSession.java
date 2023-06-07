package com.bitflowsoft.scrapeej.core.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;

public class BrowserSession {

    private Browser browser;
    private Page page;

    public BrowserSession(Browser browser, Page page) {
        this.browser = browser;
        this.page = page;
    }

    public Browser getBrowser() {
        return browser;
    }

    public Page getPage() {
        return page;
    }

    public void close() {
        browser.close();
        page.close();
    }
}
