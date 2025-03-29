package hellocucumber;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Optional;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void before() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Before All scenarios run once");
        WebDriverManager.firefoxdriver().setup();
    }

    @After
    public void after() {
        Optional.ofNullable(driver).ifPresent(WebDriver::quit);
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("After all scenarios run once");
    }
}
