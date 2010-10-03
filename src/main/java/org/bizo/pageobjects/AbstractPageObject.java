package org.bizo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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
        try {
          return until.get();
        } catch (final StaleElementReferenceException sere) {
          return false;
        }
      }
    });
  }

  /** @return xpath interpolated with our offset id. */
  protected By xpath(String xpathExpression) {
    return new ByXPathWithInterpolation(this, xpathExpression);
  }

  /** @return a TextObject for id. */
  protected TextObject text(String id) {
    return new TextObject(this, id);
  }

  /** @return a TextObject for by. */
  protected TextObject text(By by) {
    return new TextObject(this, by);
  }
  
  /** @return a TextBoxObject for id. */
  protected TextBoxObject textBox(String id) {
    return new TextBoxObject(this, id);
  }
  
  /** @return a TextBoxObject for by. */
  protected TextBoxObject textBox(By by) {
    return new TextBoxObject(this,by);
  }

  /** @return a LinkObject for id. */
  protected LinkObject link(String id) {
    return new LinkObject(this, id);
  }

  /** @return a LinkObject for by. */
  protected LinkObject link(By by) {
    return new LinkObject(this, by);
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
