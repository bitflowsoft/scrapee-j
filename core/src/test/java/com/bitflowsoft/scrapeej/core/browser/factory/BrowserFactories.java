package com.bitflowsoft.scrapeej.core.browser.factory;

import java.util.HashMap;

import java.util.Map;

public class BrowserFactories {

    private static Map<Class<? extends BrowserSessionFactory>, BrowserSessionFactory> factory = new HashMap<>();

    static {
        factory.put(ChromiumBrowserFactory.class, new ChromiumBrowserFactory());
        factory.put(FirefoxBrowserFactory.class, new FirefoxBrowserFactory());
    }

    public static BrowserSessionFactory getBrowserFactory(Class<? extends BrowserSessionFactory> clazz) {
        return factory.get(clazz);
    }
}
