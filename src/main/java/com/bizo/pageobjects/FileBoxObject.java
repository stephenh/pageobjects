package com.bizo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class FileBoxObject extends AbstractElementObject {

  private final String id;

  /** Only take an id for our firefox change hack. */
  public FileBoxObject(final PageObject p, final String id) {
    super(p, id);
    this.id = id;
  }

  public void type(final String value) {
    getElement().sendKeys(value);
    // Firefox does not fire onchange even if we send tab (it gets mangled) or
    // focus on another element, so do this the very explicit way
    if (getWebDriver() instanceof JavascriptExecutor) {
      final String script = "var element = document.getElementById('" + id + "');" //
        + "if (element.dispatchEvent) {"
        + "var e = document.createEvent('HTMLEvents');"
        + "e.initEvent('change', true, true);"
        + "element.dispatchEvent(e);"
        + "}"
        + "return true;";
      ((JavascriptExecutor) getWebDriver()).executeScript(script);
    }
    // go ahead and click dummy-click-div in case its not FF
    getWebDriver().findElement(By.id("dummy-click-div")).click();
  }

}
