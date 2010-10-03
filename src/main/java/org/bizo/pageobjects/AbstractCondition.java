package org.bizo.pageobjects;

import org.openqa.selenium.WebDriver;

import com.google.common.base.Function;

/** Basic getters for a {@code Condition}. */
public class AbstractCondition implements Condition {

  private final int timeoutSeconds;
  private final Function<WebDriver, Boolean> check;

  public AbstractCondition(int timeoutSeconds, Function<WebDriver, Boolean> check) {
    this.timeoutSeconds = timeoutSeconds;
    this.check = check;
  }

  @Override
  public int getTimeoutSeconds() {
    return timeoutSeconds;
  }

  @Override
  public Function<WebDriver, Boolean> getCheck() {
    return check;
  }

}
