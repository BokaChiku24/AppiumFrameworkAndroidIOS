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
		service = startAppiumServer();
		options = new XCUITestOptions();
		options.setDeviceName("iPhone 16 Pro Max");
		//options.setApp("/Users/kunalchavan/Library/Developer/Xcode/DerivedData/UIKitCatalog-gquhtmfnxjxpifgdmjdtvjcqmaxn/Build/Products/Debug-iphonesimulator/UIKitCatalog.app");
		options.setApp("/Users/kunalchavan/Desktop/UIKitCatalog.app");
		options.setPlatformName("iOS");
		options.setPlatformVersion("18.5");
		
		// Appium installs WebDriver Agent in the iOS Apps (Simulator)
		
		options.setWdaLaunchTimeout(Duration.ofSeconds(30));
		driver = new IOSDriver(service.getUrl(),options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		page = new HomePage(driver);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}
