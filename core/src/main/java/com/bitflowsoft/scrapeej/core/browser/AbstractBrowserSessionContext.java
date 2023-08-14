package com.bitflowsoft.scrapeej.core.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;

public abstract class AbstractBrowserSessionContext implements BrowserSessionContext {

    private final BrowserSession browserSession;

    public AbstractBrowserSessionContext(BrowserOptions browserOptions) {
        this.browserSession = launch(browserOptions);
    }

    @Override
    public BrowserSession session() {
        return browserSession;
    }

    @Override
    public Browser getBrowser() {
        return browserSession.getBrowser();
    }

    @Override
    public Page getPage() {
        return browserSession.getPage();
    }

    @Override
    public void close() {
        browserSession.close();
    }
}
