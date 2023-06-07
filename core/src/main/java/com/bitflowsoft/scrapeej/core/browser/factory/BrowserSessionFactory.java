package com.bitflowsoft.scrapeej.core.browser.factory;

import com.bitflowsoft.scrapeej.core.browser.BrowserOptions;
import com.bitflowsoft.scrapeej.core.browser.BrowserSessionContext;

public interface BrowserSessionFactory {

    BrowserSessionContext createBrowser(BrowserOptions browserOptions);
    BrowserSessionContext createBrowser();
}
