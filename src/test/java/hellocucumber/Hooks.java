package hellocucumber;

import io.cucumber.java.*;
import java.util.Optional;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Hooks {
  public static WebDriver driver;

  @Before(order = 1)
  public void before() {
    System.out.println("Running First");
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
  }

  @Before(order = 2, value = "@CreateResources")
  public void createResources() {
    System.out.println("Creating resources");
  }

  @BeforeStep
  public static void beforeStep() {
    System.out.println("Before All steps run once");
  }

  @BeforeAll
  public static void beforeAll() {
    System.out.println("Before All scenarios run once");
  }

  @After
  public void after() {
    Optional.ofNullable(driver).ifPresent(WebDriver::quit);
  }

  @AfterStep
  public static void afterStep() {
    System.out.println("After all steps run once");
  }

  @AfterAll
  public static void afterAll() {
    System.out.println("After all scenarios run once");
  }
}
