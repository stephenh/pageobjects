package org.bizo.pageobjects;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/** Helper class to reuse a local Firefox instance instead of having FirefoxDriver start one each and every time. */
public class ReusedFirefox {

  public static WebDriver reconnectOrLaunch(boolean useNativeEvents, boolean useNoFocus, final String... extensions) {
    if (isAlreadyRunning()) {
      return new RemoteWebDriver(getLocalURL(), DesiredCapabilities.firefox());
    }
    // when this JVM terminates, leave the Firefox profile for the next test/JVM to use
    System.setProperty("webdriver.reap_profile", "false");
    final FirefoxProfile p = new FirefoxProfile();
    p.setAlwaysLoadNoFocusLib(useNoFocus);
    p.setEnableNativeEvents(useNativeEvents);
    for (final String extension : extensions) {
      addExtension(p, extension);
    }
    return new FirefoxDriver(p);
  }

  private static void addExtension(final FirefoxProfile p, final String extension) {
    try {
      File file = new File(extension);
      if (!file.exists()) {
        throw new IllegalArgumentException(extension + " location does not exist");
      }
      p.addExtension(file);
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static URL getLocalURL() {
    try {
      return new URL("http://127.0.0.1:7055/hub");
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static boolean isAlreadyRunning() {
    try {
      final Socket s = new Socket();
      s.connect(new InetSocketAddress("127.0.0.1", 7055));
      s.close();
      return true;
    } catch (final IOException io) {
      return false;
    }
  }

}
