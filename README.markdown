
A small set of objects to test webapps via selenium.

E.g.:

    public class HomePageObject extends AbstractPageObject {
      public final TextBoxObject username = textBox("username-id");
      public final TextBoxObject password = textBox("password-id");
      public final LinkObject login = link("login-id");

      public HomePageObject(WebDriver d) {
        super(d);
      }
    }

Then in your tests:

    public class HomePageText {
      // setup a WebDriver instance, e.g. in a @BeforeClass
      final HomePageObject home = new HomePageObject(webDriver);

      @Test
      public void testLogin() {
        home.username.type("me");
        home.password.type("password");
        home.login.click();
      }
    }

