package com.bitflowsoft.scrapeej.core.browser.factory;

import com.bitflowsoft.scrapeej.core.browser.BrowserOptions;
import com.bitflowsoft.scrapeej.core.browser.BrowserSessionContext;
import com.bitflowsoft.scrapeej.core.browser.ChromiumBrowserSessionContext;

public class ChromiumBrowserFactory implements BrowserSessionFactory {

    private BrowserOptions browserOptions;

    public ChromiumBrowserFactory() {
        this(null);
    }

    public ChromiumBrowserFactory(BrowserOptions browserOptions) {
        this.browserOptions = browserOptions;
    }

    @Override
    public BrowserSessionContext createBrowser() {
        return createBrowser(browserOptions);
    }

    @Override
    public BrowserSessionContext createBrowser(BrowserOptions browserOptions) {
        return new ChromiumBrowserSessionContext(browserOptions);
    }
}
