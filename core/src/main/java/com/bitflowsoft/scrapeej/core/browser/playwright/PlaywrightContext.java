package com.bitflowsoft.scrapeej.core.browser.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Playwright;

public class PlaywrightContext {

    private static PlaywrightContext playwrightContext;
    private final Playwright playwright;

    private PlaywrightContext() {
        playwright = Playwright.create();
    }

    public static synchronized PlaywrightContext instance() {
        if (playwrightContext == null) {
            playwrightContext = new PlaywrightContext();
        }
        return playwrightContext;
    }

    public Playwright getPlaywright() {
        return playwright;
    }

    public synchronized Browser chrome(final LaunchOptions launchOptions) {
        return playwright.chromium().launch(launchOptions);
    }

    public synchronized Browser firefox(final LaunchOptions launchOptions) {
        return playwright.firefox().launch(launchOptions);
    }

    public void close() {
        playwright.close();
    }
}
