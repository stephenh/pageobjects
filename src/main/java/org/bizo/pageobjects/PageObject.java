package org.bizo.pageobjects;

import org.openqa.selenium.WebDriver;

public interface PageObject {

  WebDriver getWebDriver();

  /** @return the string to interpolate into string-based elements ids */
  String getOffsetId();

}
