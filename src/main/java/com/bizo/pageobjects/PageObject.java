package com.bizo.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public interface PageObject {

  /** @return the {@link WebDriver} instance for this page object */
  WebDriver getWebDriver();

  /** Wait for {@code conditions} to be true before continuing */
  void waitFor(ExpectedCondition<?>... conditions);

  /** Wait for {@code conditions} to be true before continuing */
  void waitFor(List<ExpectedCondition<?>> conditions);

}
