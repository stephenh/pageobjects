package com.bizo.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public interface PageObject {

  /** @return the {@link WebDriver} instance for this page object */
  WebDriver getWebDriver();

  /** @return the string to interpolate into string-based elements ids */
  String getOffsetId();

  /** Wait for {@code conditions} to be true before continuing */
  void waitFor(ExpectedCondition<?>... conditions);

  /** Wait for {@code conditions} to be true before continuing */
  void waitFor(List<ExpectedCondition<?>> conditions);

}
