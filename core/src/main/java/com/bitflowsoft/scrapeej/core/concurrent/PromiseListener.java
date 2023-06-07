package com.bitflowsoft.scrapeej.core.concurrent;

/**
 * An event listener to receive the result after the asynchronous operation completes.
 *
 * @author gamzaman
 * */
public interface PromiseListener<T> {

  void onComplete(T value);
}
