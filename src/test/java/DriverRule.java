import org.junit.rules.ExternalResource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverRule extends ExternalResource {
    private WebDriver driver;

    @Override
    protected void before() {
        initDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Override
    protected void after() {
        driver.quit();
    }

    public void initDriver() {
        if ("firefox".equals(System.getProperty("browser"))) {
            initFireFox();
        } else {
            initChrome();
        }
    }

    private void initFireFox() {
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
    }

    private void initChrome() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
