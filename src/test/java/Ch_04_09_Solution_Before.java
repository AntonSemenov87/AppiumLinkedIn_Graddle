import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ch_04_09_Solution_Before {
    private static final String APP = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.apk";
    private static final String APPIUM = "http://localhost:4723/wd/hub";

    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "10");
        caps.setCapability("deviceName", "Pixel XL");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("app", APP);
        driver = new AndroidDriver(new URL(APPIUM), caps);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        String textToEnter = "Anton Semenov";

        wait.until(ExpectedConditions.presenceOfElementLocated(
                MobileBy.AccessibilityId("Echo Box"))).click();

        WebElement textInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                MobileBy.AccessibilityId("messageInput")));
        textInput.sendKeys(textToEnter);

        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(
                "messageSaveBtn"))).click();

        WebElement textToCheck = wait.until(ExpectedConditions.presenceOfElementLocated(
                MobileBy.AccessibilityId("Anton Semenov")));

    }
}
