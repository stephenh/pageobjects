package com.bizo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DummyClickDiv {

  private static String id = System.getProperty("pageobjects.dummyClickDiv", "dummy-click-div");

  public synchronized static boolean isEnabled() {
    return id != null && id.length() > 0;
  }

  public synchronized static String getId() {
    return id;
  }

  public synchronized static void setId(String id) {
    DummyClickDiv.id = id;
  }

  public synchronized static void click(WebDriver d) {
    d.findElement(By.id(id)).click();
  }

}
