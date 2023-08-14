package com.bitflowsoft.scrapeej.core.browser.pool.factory;

import com.bitflowsoft.scrapeej.core.browser.factory.BrowserSessionFactory;
import com.bitflowsoft.scrapeej.core.browser.pool.BrowserPool;

public interface BrowserPoolFactory<U> {

    BrowserPool<U> createPool(int poolSize, int maxPoolSize, BrowserSessionFactory browserSessionFactory);
}
