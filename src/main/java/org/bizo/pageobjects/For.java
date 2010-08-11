package org.bizo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

/** Provides an API for waiting for AJAX stuff. */
public abstract class For {
  private int seconds = 30;

  public static ForElement element(final By by) {
    return new ForElement(by);
  }

  public static For pageLoad(final String href) {
    return new For() {
      public boolean success(final WebDriver d) {
        if (d.getCurrentUrl().indexOf(href) == -1) {
          return false;
        }
        return true;
      }
    };
  }

  /** @return true if the thing we are waiting for has happened */
  public abstract boolean success(final WebDriver d);

  /** @return the current {@link For} updated to timeout after seconds */
  public For seconds(final int seconds) {
    this.seconds = seconds;
    return this;
  }

  public int getSeconds() {
    return seconds;
  }

  public static class ForElement {
    private final By by;

    public ForElement(final By by) {
      this.by = by;
    }

    public For hidden() {
      return new For() {
        public boolean success(final WebDriver d) {
          return !((RenderedWebElement) d.findElement(by)).isDisplayed();
        }
      };
    }

    public For exists() {
      return new For() {
        public boolean success(final WebDriver d) {
          try {
            d.findElement(by);
            return true;
          } catch (final NoSuchElementException nsee) {
            return false;
          }
        }
      };
    }

    public For notExists() {
      return new For() {
        public boolean success(final WebDriver d) {
          try {
            d.findElement(by);
            return false;
          } catch (final NoSuchElementException nsee) {
            return true;
          }
        }
      };
    }

    public For enabled() {
      return new For() {
        public boolean success(final WebDriver d) {
          try {
            return d.findElement(by).isEnabled();
          } catch (final StaleElementReferenceException sere) {
            return false;
          } catch (final NoSuchElementException nsee) {
            return false;
          }
        }
      };
    }

    public For hasText(final String text) {
      return new For() {
        public boolean success(final WebDriver d) {
          return text.equals(d.findElement(by).getText());
        }
      };
    }

    public For hasAttribute(final String name, final String value) {
      return new For() {
        public boolean success(final WebDriver d) {
          return value.equals(d.findElement(by).getAttribute(name));
        }
      };
    }
  }

}