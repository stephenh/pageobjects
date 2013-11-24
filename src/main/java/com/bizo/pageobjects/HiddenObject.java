package com.bizo.pageobjects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

/** A class to get the text of off-screen/debug elements that are otherwise hidden. */
public class HiddenObject extends AbstractElementObject {

  private final String id;

  public HiddenObject(final PageObject p, final String id) {
    super(p, id);
    this.id = id;
  }

  public String get() {
    return (String) ((JavascriptExecutor) getWebDriver()).executeScript(//
      "var d = document.getElementById('" + id + "'); return (d == null) ? '' : d.innerHTML;");
  }

  public void assertText(final String text) {
    assertThat(get(), is(text));
  }

  public ExpectedCondition<Boolean> nowHasText(final String text) {
    return new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver input) {
        return text.equals(get());
      }
    };
  }

}
