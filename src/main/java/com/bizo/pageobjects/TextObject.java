package com.bizo.pageobjects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class TextObject extends AbstractElementObject {

  public TextObject(final PageObject p, final By by) {
    super(p, by);
  }

  public TextObject(final PageObject p, final String id) {
    super(p, id);
  }

  public void click() {
    getElement().click();
  }

  public String get() {
    return getElement().getText();
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

  private static String getHiddenText(final WebDriver d, final String elementId) {
    return (String) ((JavascriptExecutor) d).executeScript(//
      "var d = document.getElementById('" + elementId + "'); return (d == null) ? '' : d.innerHTML;");
  }
  

}
