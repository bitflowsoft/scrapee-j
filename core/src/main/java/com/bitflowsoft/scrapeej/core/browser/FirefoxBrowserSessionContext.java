package com.bitflowsoft.scrapeej.core.browser;

import com.bitflowsoft.scrapeej.core.browser.playwright.PlaywrightContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;

public class FirefoxBrowserSessionContext extends AbstractBrowserSessionContext {

  public FirefoxBrowserSessionContext(BrowserOptions browserOptions) {
    super(browserOptions);
  }

  @Override
  public BrowserSession launch(BrowserOptions browserOptions) {
    Browser browser = PlaywrightContext.instance().firefox(browserOptions);
    Page page = browser.newPage();
    return new BrowserSession(browser, page);
  }
}
