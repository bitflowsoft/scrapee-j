package com.bitflowsoft.scrapeej.core.util;

import static com.bitflowsoft.scrapeej.core.util.RuntimeAssertions.checkNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionUtils {

  private final static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

  public static <T> T createNoConstructorInstance(final Class<T> clazz) {
    checkNull(clazz, "instantiate target class is null");
    try {
      final Constructor<T> constructor = clazz.getConstructor();
      return constructor.newInstance();
    } catch (NoSuchMethodException e) {
      logger.error("{} constructor cannot found.", clazz.getName());
    } catch (InstantiationException e) {
      logger.error("{} instantiate failed.", clazz.getName());
    } catch (IllegalAccessException e) {
      logger.error("{} constructor is can't access", clazz.getName());
    } catch (InvocationTargetException e) {
      logger.error("{} invoke exception", clazz.getName());
    }
    return null;
  }
}
