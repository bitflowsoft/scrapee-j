package com.bitflowsoft.scrapeej.core.util;

public final class Check {

    public static <T> void checkNull(final T t, final String message) {
        if (t == null) {
            throw new NullPointerException(message);
        }
    }

    public static void checkZero(final int integer, final String message) {
        if (integer == 0) {
            throw new IllegalArgumentException(message);
        }
    }
}
