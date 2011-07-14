package com.bizo.pageobjects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.base.Supplier;

/** A base class for element objects like {@link TextBoxObject}, {@link TextObject}, etc. */
public abstract class AbstractElementObject {

  /** Our parent {@link PageObject}. */
  protected final PageObject p;
  /** How to find our element on the page. */
  protected final By by;

  public AbstractElementObject(final PageObject p, final String id) {
    this.p = p;
    by = new ByWithIdInterpolation(p, id);
  }

  public AbstractElementObject(final PageObject p, final By by) {
    this.p = p;
    this.by = by;
  }

  /** @return the element's css value of {@code name}. */
  public String getCssValue(final String name) {
    return element().getCssValue(name);
  }

  /** @return the element's attribute value of {@code name}. */
  public String getAttribute(final String name) {
    return element().getAttribute(name);
  }

  /** @return true if the element is present but not displayed. */
  public boolean isDisplayed() {
    return element().isDisplayed();
  }

  /** @return true if the element is in the DOM, regardless of being displayed or not. */
  public boolean isPresent() {
    try {
      element();
      return true;
    } catch (final NoSuchElementException nsee) {
      return false;
    }
  }

  public void assertDisplayed() {
    assertThat(this + " was not displayed", isDisplayed(), is(true));
  }

  public void assertNotDisplayed() {
    assertThat(this + " was displayed", isDisplayed(), is(false));
  }

  public void assertNotPresent() {
    assertThat(this + " was presenter", isPresent(), is(false));
  }

  /** @return the page object's web driver instance. */
  protected WebDriver getWebDriver() {
    return p.getWebDriver();
  }

  /** @return the element we're wrapping. */
  protected WebElement element() {
    return getWebDriver().findElement(by);
  }

  @Override
  public String toString() {
    return by.toString();
  }

  /** @return a supplier for waiting until this is enabled. */
  public Supplier<Boolean> nowEnabled() {
    return new Supplier<Boolean>() {
      public Boolean get() {
        return element().isEnabled();
      }
    };
  }

}
