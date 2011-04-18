package com.bizo.pageobjects;

import org.openqa.selenium.WebDriver;

import com.google.common.base.Function;

/** A condition that we should for before continuing a test. */
public interface Condition {

  int getTimeoutSeconds();

  Function<WebDriver, Boolean> getCheck();

}
