package org.bizo.pageobjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ListObject extends AbstractElementObject {
  public ListObject(final PageObject p, final String id) {
    super(p, id);
  }

  public List<String> getValues() {
    final List<String> values = new ArrayList<String>();
    for (final WebElement li : element().findElements(By.tagName("li"))) {
      values.add(li.getText());
    }
    return values;
  }

  public String getValuesAsString() {
    String s = "";
    for (final Iterator<String> i = getValues().iterator(); i.hasNext();) {
      s += i.next();
      if (i.hasNext()) {
        s += ", ";
      }
    }
    return s;
  }

}
