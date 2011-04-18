package com.bizo.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsById;
import org.openqa.selenium.internal.FindsByXPath;

/** Replaces {@code $id} in otherwise static element ids for use in table/list scenarios. */
public final class ByWithIdInterpolation extends By {
  private final PageObject page;
  private final String id;

  public ByWithIdInterpolation(final PageObject page, final String id) {
    this.page = page;
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
    return page.getOffsetId() == null ? id : id.replace("$id", page.getOffsetId());
  }
}
