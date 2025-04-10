package cattleyanadora.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cattleyanadora.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver intializeDriver() throws IOException {
		if (driver == null) {
	
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\cattleyanadora\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		prop.getProperty("browser");
		
		if (browserName.contains("chrome")) {		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		WebDriverManager.chromedriver().setup();

		if(browserName.contains("headless")) {
		options.addArguments("headless");
		}
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			//System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
        else if (browserName.equalsIgnoreCase("edge")) {
        	//System.setProperty("webdriver.edge.driver", "edge.exe");
        	WebDriverManager.edgedriver().setup();
        	driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		return driver;
	}
	
@BeforeMethod(alwaysRun = true)
public LandingPage launchApplication() throws IOException {
	//If browser isn't open or session is invalid, it calls intializeDriver()
    if (driver == null || ((RemoteWebDriver) driver).getSessionId() == null) {
        driver = intializeDriver();  
    }
    landingPage = new LandingPage(driver);
    landingPage.goTo();
    return landingPage;
}

@AfterMethod(alwaysRun = true)
public void tearDown() {
    if (driver != null) {
        try {
            driver.quit();
        } catch (Exception ignored) {
        }
//Sets driver to null to clean up. cleans the memory

        driver = null;  
    }
}

}
