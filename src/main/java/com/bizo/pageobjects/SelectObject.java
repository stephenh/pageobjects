package com.bizo.pageobjects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SelectObject extends AbstractElementObject {

  public SelectObject(final PageObject p, final String id) {
    super(p, id);
  }

  public SelectObject(final PageObject p, final By id) {
    super(p, id);
  }

  public void click() {
    element().click();
  }

  public void assertSelected(final String text) {
    assertThat(this + " had wrong selection " + getSelected(), getSelected(), is(text));
  }

  public List<String> getItems() {
    final List<String> items = new ArrayList<String>();
    for (final WebElement element : element().findElements(By.tagName("option"))) {
      items.add(element.getText());
    }
    return items;
  }

  public String getSelected() {
    for (final WebElement element : element().findElements(By.tagName("option"))) {
      if (element.isSelected()) {
        return element.getText();
      }
    }
    return null;
  }

  public void select(final String text) {
    boolean found = false;
    for (final WebElement element : element().findElements(By.tagName("option"))) {
      if (element.getText().equals(text)) {
        element.click();
        found = true;
        break;
      }
    }
    if (!found) {
      fail("No option found for " + text);
    }
    // This magically fires a blur on the box
    element().click();
    element().click();
  }
}
