package com.bitflowsoft.scrapeej.core.pool;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.bitflowsoft.scrapeej.core.browser.BrowserSessionContext;
import com.bitflowsoft.scrapeej.core.browser.factory.BrowserSessionFactory;
import com.bitflowsoft.scrapeej.core.util.Time;

public class ScalablePool implements Pool<BrowserSessionContext>  {

    private final int poolSize, maxPoolSize;
    private final Queue<BrowserSessionContext> browserSessionContextQueue;
    private final BrowserSessionFactory browserSessionFactory;
    private final AtomicInteger currentBrowserSize;

    public ScalablePool(final int poolSize, final int maxPoolSize, BrowserSessionFactory browserSessionFactory) {
        this.poolSize = poolSize;
        this.maxPoolSize = maxPoolSize;
        this.browserSessionContextQueue = new ConcurrentLinkedQueue<>();
        this.browserSessionFactory = browserSessionFactory;
        this.currentBrowserSize = new AtomicInteger(0);

        initialize(poolSize);
    }

    private void initialize(int poolSize) {
        if (poolSize > maxPoolSize) {
            throw new IllegalArgumentException("poolSize's cannot be over then maxPoolSize");
        }
        for (int i = 0; i < poolSize; i++) {
            browserSessionContextQueue.add(browserSessionFactory.createBrowser());
        }
    }

    @Override
    public void release(BrowserSessionContext browserSessionContext) {
        currentBrowserSize.decrementAndGet();
        browserSessionContextQueue.add(browserSessionContext);
    }

    @Override
    public BrowserSessionContext select() {
        if (browserSessionContextQueue.isEmpty()) {
            return null;
        }
        currentBrowserSize.incrementAndGet();
        return browserSessionContextQueue.poll();
    }

    @Override
    public BrowserSessionContext select(Long timeout) {
        BrowserSessionContext sessionContext = null;
        if (browserSessionContextQueue.isEmpty()) {
            if (currentBrowserSize.get() >= maxPoolSize) {
                long lastTimeMills;
                long startTimeMills = Time.milliTime();
                do {
                    if (currentBrowserSize.get() < maxPoolSize) {
                        sessionContext = select();
                        break;
                    }
                    lastTimeMills = Time.milliTime();
                } while (lastTimeMills - startTimeMills < timeout);
                return sessionContext;
            }
            return browserSessionFactory.createBrowser();
        }
        else {
            currentBrowserSize.incrementAndGet();
            sessionContext = browserSessionContextQueue.poll();
        }
        return sessionContext;
    }

    @Override
    public int getPoolSize() {
        return poolSize;
    }
}
