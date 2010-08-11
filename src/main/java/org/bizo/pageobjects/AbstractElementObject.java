package org.bizo.pageobjects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsById;
import org.openqa.selenium.internal.FindsByXPath;

import com.google.common.base.Supplier;

/** A base class for element objects like {@link TextBoxObject}, {@link TextObject}, etc. */
public abstract class AbstractElementObject {

  /** Our parent {@link PageObject}. */
  protected final PageObject p;
  /** How to find our element on the page. */
  protected final By by;

  public AbstractElementObject(final PageObject p, final String id) {
    this.p = p;
    by = new ByWithIdInterpolation(id);
  }

  public AbstractElementObject(final PageObject p, final By by) {
    this.p = p;
    this.by = by;
  }

  /** @return the element's css value of {@code name}. */
  public String getCssValue(final String name) {
    return ((RenderedWebElement) element()).getValueOfCssProperty(name);
  }

  /** @return the element's attribute value of {@code name}. */
  public String getAttribute(final String name) {
    return ((RenderedWebElement) element()).getAttribute(name);
  }

  /** @return true if the element is present but not displayed. */
  public boolean isDisplayed() {
    return ((RenderedWebElement) element()).isDisplayed();
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

  /** Replaces {@code $id} in otherwise static element ids for use in table/list scenarios. */
  private final class ByWithIdInterpolation extends By {
    private final String id;

    private ByWithIdInterpolation(final String id) {
      this.id = id;
    }

    @Override
    public List<WebElement> findElements(final SearchContext context) {
      // Copy/paste of By.id with $id/page offset id interpolation added
      final String id = getInterpolatedId();
      if (context instanceof FindsById) {
        return ((FindsById) context).findElementsById(id);
      }
      return ((FindsByXPath) context).findElementsByXPath("*[@id = '" + id + "']");
    }

    @Override
    public WebElement findElement(final SearchContext context) {
      // Copy/paste of By.id with $id/page offset id interpolation added
      final String id = getInterpolatedId();
      if (context instanceof FindsById) {
        return ((FindsById) context).findElementById(id);
      }
      return ((FindsByXPath) context).findElementByXPath("*[@id = '" + id + "']");
    }

    @Override
    public String toString() {
      return "By.id: " + getInterpolatedId();
    }

    private String getInterpolatedId() {
      return p.getOffsetId() == null ? id : id.replace("$id", p.getOffsetId());
    }
  }
}