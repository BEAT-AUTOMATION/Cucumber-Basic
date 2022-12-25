package resources;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class base {
    public static WebDriver driver;
    public Properties prop;
    public static int implicitwait = 30;
    public static int pageloadtimeout = 30;

    public WebDriver initializeDriver() throws IOException {

        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
        prop.load(fis);

        String browserName = prop.getProperty("browser");

        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\java\\resources\\chromedriver.exe");

            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(base.pageloadtimeout, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(base.implicitwait, TimeUnit.SECONDS);
            driver.get(prop.getProperty("url"));

        } else if (browserName.equals("ie")) {
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\main\\java\\resources\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();

        } else if (browserName.equals("firefox")) {

            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\main\\java\\resources\\IEDriverServer.exe");
            //driver = new GeckoDriverService();
        }

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        return driver;
    }


    public WebDriverWait ewait() {
        WebDriverWait wait = new WebDriverWait(driver, 45);
        return wait;

    }

    public void closeDriver() {
        driver.close();

    }


    public static void ReCaptcha_click() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
        driver.findElement(By.xpath("//span[@id='recaptcha-anchor']")).click();

        driver.switchTo().defaultContent();

    }

}

	
