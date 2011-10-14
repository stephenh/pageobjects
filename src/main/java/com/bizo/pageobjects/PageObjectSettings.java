package com.bizo.pageobjects;

import java.util.concurrent.atomic.AtomicLong;

public class PageObjectSettings {

  private static AtomicLong timeout = new AtomicLong(15000);

  public static long getTimeout() {
    return timeout.get();
  }

  public static void setTimeout(Long timeoutInMilliseconds) {
    timeout.set(timeoutInMilliseconds);
  }
    
}
