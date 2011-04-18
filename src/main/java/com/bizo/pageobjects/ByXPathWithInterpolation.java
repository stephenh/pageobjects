package com.bizo.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.FindsByXPath;

/** Replaces {@code $id} in otherwise static element xpathExpression for use in table/list scenarios. */
public final class ByXPathWithInterpolation extends By {
  private final PageObject page;
  private final String xpathExpression;

  public ByXPathWithInterpolation(final PageObject page, final String xpathExpression) {
    this.page = page;
    this.xpathExpression = xpathExpression;
  }

  @Override
  public List<WebElement> findElements(final SearchContext context) {
    return ((FindsByXPath) context).findElementsByXPath(getInterpolatedXPath());
  }

  @Override
  public WebElement findElement(final SearchContext context) {
    return ((FindsByXPath) context).findElementByXPath(getInterpolatedXPath());
  }

  private String getInterpolatedXPath() {
    return page.getOffsetId() == null ? xpathExpression : xpathExpression.replace("$id", page.getOffsetId());
  }

  @Override
  public String toString() {
    return "By.xpath: " + xpathExpression;
  }
}
