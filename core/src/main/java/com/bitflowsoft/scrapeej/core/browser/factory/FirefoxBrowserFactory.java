package com.bitflowsoft.scrapeej.core.browser.factory;

import com.bitflowsoft.scrapeej.core.browser.BrowserOptions;
import com.bitflowsoft.scrapeej.core.browser.BrowserSessionContext;
import com.bitflowsoft.scrapeej.core.browser.FirefoxBrowserSessionContext;

public class FirefoxBrowserFactory implements BrowserSessionFactory {

    @Override
    public BrowserSessionContext createBrowser() {
        return createBrowser(null);
    }

    @Override
    public BrowserSessionContext createBrowser(BrowserOptions browserOptions) {
        return new FirefoxBrowserSessionContext(browserOptions);
    }
}
