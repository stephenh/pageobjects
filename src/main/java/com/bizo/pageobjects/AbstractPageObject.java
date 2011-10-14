package com.bizo.pageobjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/** A base class for user page objects. */
public class AbstractPageObject implements PageObject {

  protected final WebDriver d;
  protected String offsetId = null;

  protected AbstractPageObject(final WebDriver d) {
    this.d = d;
  }

  @Override
  public void waitFor(final ExpectedCondition<?>... conditions) {
    waitFor(Arrays.asList(conditions));
  }

  @Override
  @SuppressWarnings("unchecked")
  public void waitFor(final List<ExpectedCondition<?>> conditions) {
    for (final ExpectedCondition<?> condition : conditions) {
      long timeout = PageObjectSettings.getTimeout();
      new WebDriverWait(d, timeout) //
        .ignoring(StaleElementReferenceException.class)
        .until(condition);
    }
  }

  /** @return xpath interpolated with our offset id. */
  protected By xpath(final String xpathExpression) {
    return new ByXPathWithInterpolation(this, xpathExpression);
  }

  /** @return a TextObject for id. */
  protected TextObject text(final String id) {
    return new TextObject(this, id);
  }

  /** @return a TextObject for by. */
  protected TextObject text(final By by) {
    return new TextObject(this, by);
  }

  /** @return a TextBoxObject for id. */
  protected TextBoxObject textBox(final String id) {
    return new TextBoxObject(this, id);
  }

  /** @return a TextBoxObject for by. */
  protected TextBoxObject textBox(final By by) {
    return new TextBoxObject(this, by);
  }

  /** @return a LinkObject for id. */
  protected LinkObject link(final String id) {
    return new LinkObject(this, id);
  }

  /** @return a LinkObject for by. */
  protected LinkObject link(final By by) {
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
