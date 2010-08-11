package org.bizo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.google.common.base.Supplier;

/** A base class for user page objects. */
public class AbstractPageObject implements PageObject {

  protected final WebDriver d;
  protected String offsetId = null;

  protected AbstractPageObject(final WebDriver d) {
    this.d = d;
  }

  protected void wait(final Supplier<Boolean> until) {
    wait(DefaultWait.SECONDS, until);
  }

  protected void wait(final int seconds, final Supplier<Boolean> until) {
    new WebDriverWait(d, seconds).until(new Function<WebDriver, Boolean>() {
      @Override
      public Boolean apply(final WebDriver from) {
        return until.get();
      }
    });
  }

  @Override
  public WebDriver getWebDriver() {
    return d;
  }

  @Override
  public String getOffsetId() {
    return offsetId;
  }

}
