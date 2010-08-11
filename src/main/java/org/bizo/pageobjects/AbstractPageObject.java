package org.bizo.pageobjects;

import org.openqa.selenium.WebDriver;

/** A base class for user page objects. */
public class AbstractPageObject implements PageObject {

  protected final WebDriver d;
  protected String offsetId = null;

  protected AbstractPageObject(final WebDriver d) {
    this.d = d;
  }

  public WebDriver getWebDriver() {
    return d;
  }

  /** Waits for the ajaxy stuff in <code>wait</code> to happen or times out. */
  public void wait(final For wait) {
    final int timeoutInSeconds = wait.getSeconds();
    final Thread timeoutThread = new Thread(new TimeoutThread(Thread.currentThread(), timeoutInSeconds));
    timeoutThread.start();
    try {
      // Based on Wait in selenium java client
      final long start = System.currentTimeMillis();
      final long end = start + (timeoutInSeconds * 1000);
      while (System.currentTimeMillis() < end) {
        if (wait.success(getWebDriver())) {
          break;
        }
        try {
          Thread.sleep(100);
        } catch (final InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    } finally {
      timeoutThread.interrupt();
    }
  }

  /**
   * Interrupts the parent thread if it blocks too long.
   * 
   * Based on WebDriverBackedSelenium's TimeoutThread.
   */
  protected static class TimeoutThread implements Runnable {
    private final long timeoutInSeconds;
    private final Thread callback;

    public TimeoutThread(final Thread callback, final long timeoutInSeconds) {
      this.callback = callback;
      this.timeoutInSeconds = timeoutInSeconds;
    }

    public void run() {
      try {
        Thread.sleep(timeoutInSeconds * 1000);
      } catch (final InterruptedException e) {
        // The timeout has been interrupted.
        return;
      }
      // The timeout has been reach, interrupting the original thread.
      callback.interrupt();
    }
  }

  @Override
  public String getOffsetId() {
    return offsetId;
  }

}
