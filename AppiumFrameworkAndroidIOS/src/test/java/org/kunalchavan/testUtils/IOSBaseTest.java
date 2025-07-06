package org.kunalchavan.testUtils;

import java.time.Duration;

import org.kunalchavan.pageObjects.ios.HomePage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import utils.AppiumUtils;

public class IOSBaseTest extends AppiumUtils {

	AppiumDriverLocalService service;
	XCUITestOptions options;
	IOSDriver driver;
	public HomePage page;
	WebDriverWait wait;

	@BeforeClass
	public void setup() {
		prop = properties("/src/main/resources/globalData.properties");
		service = startAppiumServer(prop.getProperty("ipAddress"), Integer.parseInt(prop.getProperty("port")));
		options = new XCUITestOptions();
		options.setDeviceName(prop.getProperty("IOSDevice"));
		options.setApp(prop.getProperty("iosIPA"));
		options.setPlatformName(prop.getProperty("iossetPlatformName"));
		options.setPlatformVersion(prop.getProperty("iossetPlatformVersion"));

		// Appium installs WebDriver Agent in the iOS Apps (Simulator)

		options.setWdaLaunchTimeout(Duration.ofSeconds(Long.parseLong(prop.getProperty("iosimplicitlyWait"))));
		driver = new IOSDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(prop.getProperty("implicitlyWait"))));
		page = new HomePage(driver);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}
