package com.bitflowsoft.scrapeej.core.browser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.bitflowsoft.scrapeej.core.browser.factory.BrowserFactories;
import com.bitflowsoft.scrapeej.core.browser.factory.BrowserSessionFactory;
import com.bitflowsoft.scrapeej.core.browser.factory.ChromiumBrowserFactory;
import com.microsoft.playwright.Page;

class ChromiumBrowserSessionContextTest {

    @Test
    void chromiumBrowserTest() throws Exception {
        final BrowserOptions options = new BrowserOptions(true, null);
        final BrowserSessionFactory factory = BrowserFactories.getBrowserFactory(ChromiumBrowserFactory.class);
        try (BrowserSessionContext browserSessionContext = factory.createBrowser(options)) {
            Page page = browserSessionContext.getPage();
            page.navigate("https://playwright.dev/");
            String title = page.title();

            assertEquals(title, "Fast and reliable end-to-end testing for modern web apps | Playwright");
        }
    }
}