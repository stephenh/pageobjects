package org.bizo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class FileBoxObject extends AbstractElementObject {

  public FileBoxObject(final PageObject p, final String id) {
    super(p, id);
  }

  public FileBoxObject(final PageObject p, final By by) {
    super(p, by);
  }

  public void type(final String value) {
    element().sendKeys(value);
    // Firefox does not fire onchange even if we send tab (it gets mangled) or
    // focus on another element, so do this the very explicit way
    final String script = "var e = document.createEvent('HTMLEvents');"//
      + "e.initEvent('change', true, true);"//
      + "document.getElementsByClassName('gwt-FileUpload')[0].dispatchEvent(e);"//
      + "return true;";
    ((JavascriptExecutor) getWebDriver()).executeScript(script);
  }

}
