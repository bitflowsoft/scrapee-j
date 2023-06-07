package com.bitflowsoft.scrapeej.core.pool;

import java.util.concurrent.TimeUnit;

public interface Pool<T> {

  T select();

  T select(final Long timeout);

  void release(T t);

  int getPoolSize();
}
