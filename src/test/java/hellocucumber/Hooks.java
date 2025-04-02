package hellocucumber;

import io.cucumber.java.*;
import java.util.Optional;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Hooks {
  public static WebDriver driver;

  @Before(order = 1)
  public void before() {
    System.out.println("Run before each scenario First");
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
  }

  @Before(order = 2, value = "@CreateResources")
  public void createResources() {
    System.out.println("Run for each scenario Second only for annotated scenarios");
  }

  @BeforeStep
  public static void beforeStep() {
    System.out.println("Run before all steps");
  }

  @BeforeAll
  public static void beforeAll() {
    System.out.println("Run once before all scenarios");
  }

  @After
  public void after() {
    System.out.println("Run after all scenarios");
    Optional.ofNullable(driver).ifPresent(WebDriver::quit);
  }

  @AfterStep
  public static void afterStep() {
    System.out.println("Run after all steps");
  }

  @AfterAll
  public static void afterAll() {
    System.out.println("Run once after all scenarios");
  }
}
