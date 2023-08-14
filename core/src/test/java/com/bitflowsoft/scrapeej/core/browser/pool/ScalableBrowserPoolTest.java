package com.bitflowsoft.scrapeej.core.browser.pool;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bitflowsoft.scrapeej.core.browser.BrowserSessionContext;
import com.bitflowsoft.scrapeej.core.browser.factory.BrowserFactories;
import com.bitflowsoft.scrapeej.core.browser.factory.ChromiumBrowserFactory;

class ScalableBrowserPoolTest {

	final int maxPool = 1;
    private BrowserPool<BrowserSessionContext> scalableBrowserBrowserPool;

	@BeforeEach
	void before() {
		final int poolSize = 1;
		scalableBrowserBrowserPool = new ScalableBrowserPool(
			poolSize, maxPool, BrowserFactories.getBrowserFactory(ChromiumBrowserFactory.class));
	}

    @Test
    void poolTestSelect() {
        for (int i = 0; i < scalableBrowserBrowserPool.getPoolSize(); i++) {
            BrowserSessionContext select = scalableBrowserBrowserPool.select();
            assertNotNull(select);
        }
        assertNull(scalableBrowserBrowserPool.select());
    }

    @Test
    void autoScaleTest() {
        for (int i = 0; i < maxPool; i++) {
            BrowserSessionContext select = scalableBrowserBrowserPool.select(2000L);
            assertNotNull(select);
        }
        assertNull(scalableBrowserBrowserPool.select(1000L));
    }

    @Test
    void autoScaleReleaseWaitingTest() throws Exception {
        BrowserSessionContext select = scalableBrowserBrowserPool.select();
        assertNotNull(select);
        new Thread(() -> {
            try {
                Thread.sleep(100L);
                scalableBrowserBrowserPool.release(select);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        assertNotNull(scalableBrowserBrowserPool.select(3000L));
    }

    @Test
    void autoScaleWaitingFailedTest() throws Exception {
        BrowserSessionContext select = scalableBrowserBrowserPool.select();
        assertNotNull(select);
        new Thread(() -> {
            try {
                Thread.sleep(5000L);
                scalableBrowserBrowserPool.release(select);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        assertNull(scalableBrowserBrowserPool.select(3000L));
    }
}
