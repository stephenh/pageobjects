package com.bizo.pageobjects;

import java.util.concurrent.atomic.AtomicLong;

public class PageObjectSettings {

  private static AtomicLong timeout = new AtomicLong(15);

  public static long getTimeout() {
    return timeout.get();
  }

  public static void setTimeout(long timeoutInSeconds) {
    timeout.set(timeoutInSeconds);
  }
    
}
