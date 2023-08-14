package com.bitflowsoft.scrapeej.core.browser.pool.factory;

import com.bitflowsoft.scrapeej.core.browser.BrowserSessionContext;
import com.bitflowsoft.scrapeej.core.browser.factory.BrowserSessionFactory;
import com.bitflowsoft.scrapeej.core.browser.pool.BrowserPool;
import com.bitflowsoft.scrapeej.core.browser.pool.ScalableBrowserPool;

public class ScalableBrowserPoolFactory implements BrowserPoolFactory<BrowserSessionContext> {

    @Override
    public BrowserPool<BrowserSessionContext> createPool(int poolSize, int maxPoolSize,
                                                         BrowserSessionFactory browserSessionFactory) {
        return new ScalableBrowserPool(poolSize, maxPoolSize, browserSessionFactory);
    }
}
