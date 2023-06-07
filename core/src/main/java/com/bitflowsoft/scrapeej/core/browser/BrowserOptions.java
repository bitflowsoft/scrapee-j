package com.bitflowsoft.scrapeej.core.browser;

import com.bitflowsoft.scrapeej.core.util.annotations.Nullable;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.options.Proxy;

public class BrowserOptions extends LaunchOptions {
    public BrowserOptions(final boolean headless, @Nullable final BrowserProxy browserProxy) {
        super.setHeadless(headless);
        if (browserProxy != null) {
            super.setProxy(new Proxy(browserProxy.getProxyServer()));
        }
    }
}
