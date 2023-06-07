package com.bitflowsoft.scrapeej.core.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Manage the event loops and delegate task to child event loops
 *
 * @author gamzaman
 * */
public interface EventLoopGroup extends Iterable<EventLoop> {

  int FIT_SYSTEM_EVENT_LOOP_COUNT = Runtime.getRuntime().availableProcessors() * 2;

  /**
   * Kills all currently running tasks and stops all EventLoops.
   * */
  void shutdownGracefully();

  EventDispatcher getEventDispatcher();
}
