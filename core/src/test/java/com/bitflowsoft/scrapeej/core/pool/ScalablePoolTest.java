package com.bitflowsoft.scrapeej.core.pool;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.bitflowsoft.scrapeej.core.browser.BrowserSessionContext;
import com.bitflowsoft.scrapeej.core.browser.factory.BrowserFactories;
import com.bitflowsoft.scrapeej.core.browser.factory.ChromiumBrowserFactory;

class ScalablePoolTest {

    private final int poolSize = 1, maxPool = 1;
    private final Pool<BrowserSessionContext> scalableBrowserPool = new ScalablePool(
            poolSize, maxPool, BrowserFactories.getBrowserFactory(ChromiumBrowserFactory.class));

    @Test
    void poolTestSelect() {
        for (int i = 0; i < scalableBrowserPool.getPoolSize(); i++) {
            BrowserSessionContext select = scalableBrowserPool.select();
            assertNotNull(select);
        }
        assertNull(scalableBrowserPool.select());
    }

    @Test
    void autoScaleTest() {
        for (int i = 0; i < maxPool; i++) {
            BrowserSessionContext select = scalableBrowserPool.select(2000L);
            assertNotNull(select);
        }
        assertNull(scalableBrowserPool.select(1000L));
    }

    @Test
    void autoScaleReleaseWaitingTest() throws Exception {
        BrowserSessionContext select = scalableBrowserPool.select();
        assertNotNull(select);
        new Thread(() -> {
            try {
                Thread.sleep(1000L);
                scalableBrowserPool.release(select);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        assertNotNull(scalableBrowserPool.select(3000L));
    }

    @Test
    void autoScaleWaitingFailedTest() throws Exception {
        BrowserSessionContext select = scalableBrowserPool.select();
        assertNotNull(select);
        new Thread(() -> {
            try {
                Thread.sleep(5000L);
                scalableBrowserPool.release(select);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        assertNull(scalableBrowserPool.select(3000L));
    }
}