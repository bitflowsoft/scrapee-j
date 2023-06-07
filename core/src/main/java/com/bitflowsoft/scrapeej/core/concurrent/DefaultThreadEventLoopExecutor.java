package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DefaultThreadEventLoopExecutor implements Executor {

  private final ThreadFactory threadFactory = Executors.defaultThreadFactory();
  private static Executor executor;

  private DefaultThreadEventLoopExecutor() {}

  public static synchronized Executor getExecutor() {
    if (executor == null) {
      executor = new DefaultThreadEventLoopExecutor();
    }
    return executor;
  }

  @Override
  public void execute(final Runnable task) {
    threadFactory.newThread(task).start();
  }
}
