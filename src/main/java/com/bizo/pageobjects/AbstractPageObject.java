package com.bizo.pageobjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/** A base class for user page objects. */
public class AbstractPageObject implements PageObject {

  protected final WebDriver d;
  private final String prefix;
  private final String offset;

  protected AbstractPageObject(final WebDriver d) {
    this(d, null);
  }

  protected AbstractPageObject(final WebDriver d, final String prefix) {
    this(d, prefix, null);
  }

  protected AbstractPageObject(final WebDriver d, final String prefix, final String offset) {
    this.d = d;
    this.prefix = prefix;
    this.offset = offset;
  }

  @Override
  public void waitFor(final ExpectedCondition<?>... conditions) {
    waitFor(Arrays.asList(conditions));
  }

  @Override
  public void waitFor(final List<ExpectedCondition<?>> conditions) {
    for (final ExpectedCondition<?> condition : conditions) {
      long timeout = PageObjectSettings.getTimeout();
      new WebDriverWait(d, timeout) //
        .ignoring(StaleElementReferenceException.class)
        .until(condition);
    }
  }

  /** @return a CheckBoxObject for id. */
  protected CheckBoxObject checkBox(final String id) {
    return new CheckBoxObject(this, mungeId(id));
  }

  /** @return a CheckBoxObject for by. */
  protected CheckBoxObject checkBox(final By by) {
    return new CheckBoxObject(this, by);
  }

  /** @return an ElementObject for id. */
  protected ElementObject element(final String id) {
    return new ElementObject(this, mungeId(id));
  }

  /** @return an ElementObject for by. */
  protected ElementObject element(final By by) {
    return new ElementObject(this, by);
  }

  /** @return a FileBoxObject for id. */
  protected FileBoxObject file(final String id) {
    return new FileBoxObject(this, mungeId(id));
  }

  /** @return an ImageObject for id. */
  protected ImageObject image(final String id) {
    return new ImageObject(this, mungeId(id));
  }

  /** @return an ImageObject for by. */
  protected ImageObject image(final By by) {
    return new ImageObject(this, by);
  }

  /** @return a LinkObject for id. */
  protected LinkObject link(final String id) {
    return new LinkObject(this, mungeId(id));
  }

  /** @return a LinkObject for by. */
  protected LinkObject link(final By by) {
    return new LinkObject(this, by);
  }

  /** @return a ListObject for id. */
  protected ListObject list(final String id) {
    return new ListObject(this, mungeId(id));
  }

  /** @return a ListObject for by. */
  protected ListObject list(final By by) {
    return new ListObject(this, by);
  }

  /** @return a SelectObject for id. */
  protected SelectObject select(final String id) {
    return new SelectObject(this, mungeId(id));
  }

  /** @return a SelectObject for by. */
  protected SelectObject select(final By by) {
    return new SelectObject(this, by);
  }

  /** @return a TextBoxObject for id. */
  protected TextBoxObject textBox(final String id) {
    return new TextBoxObject(this, mungeId(id));
  }

  /** @return a TextBoxObject for by. */
  protected TextBoxObject textBox(final By by) {
    return new TextBoxObject(this, by);
  }
  /** @return a TextObject for id. */
  protected TextObject text(final String id) {
    return new TextObject(this, mungeId(id));
  }

  /** @return a TextObject for by. */
  protected TextObject text(final By by) {
    return new TextObject(this, by);
  }


  protected String mungeId(String id) {
    return //
    (prefix == null ? "" : prefix) + (offset == null ? id : id.replace("$id", offset));
  }

  @Override
  public WebDriver getWebDriver() {
    return d;
  }

}
