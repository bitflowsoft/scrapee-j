package com.bitflowsoft.scrapeej.core.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;

public interface BrowserSessionContext extends AutoCloseable {

  BrowserSession launch(BrowserOptions browserOptions);

  BrowserSession session();

  Page getPage();

  Browser getBrowser();
}
