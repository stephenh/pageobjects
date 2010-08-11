package org.bizo.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class By2 {

  public static By then(final By one, final By then) {
    return new By() {
      @Override
      public List<WebElement> findElements(final SearchContext context) {
        final List<WebElement> result = new ArrayList<WebElement>();
        for (final WebElement o : one.findElements(context)) {
          result.addAll(o.findElements(then));
        }
        return result;
      }

      @Override
      public String toString() {
        return one + " then " + then;
      }
    };
  }

}
