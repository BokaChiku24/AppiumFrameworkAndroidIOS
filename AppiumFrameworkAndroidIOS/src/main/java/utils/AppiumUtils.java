package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

// Common for Android & IOS
public class AppiumUtils {
	public double priceTotal = 0;
	public double productFinal = 0;
	WebDriverWait wait;
	AppiumDriverLocalService service;
	public Properties prop;

	public Properties properties(String path) {
		prop = new Properties();
		try {
			FileInputStream is = new FileInputStream(new File(System.getProperty("user.dir") + path));
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
		prop = properties("/src/main/resources/globalData.properties");
		service = new AppiumServiceBuilder().withAppiumJS(new File(prop.getProperty("mainJS"))).withIPAddress(ipAddress)
				.usingPort(port).build();
		service.start();
		return service;
	}

	public void addition(List<WebElement> list) {
		for (int i = 0; i < list.size(); i++) {
			priceTotal = priceTotal + Double.parseDouble(list.get(i).getText().replace("$", ""));
		}
	}

	public void waitAttributeContainsById(AppiumDriver driver, String id, String attribute, String value) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id(id)), attribute, value));
	}

	public void waitAttributeContainsByAccessibilityId(AppiumDriver driver, String id, String attribute, String value) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.accessibilityId(id)), attribute,
				value));
	}

	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "/src/test/resources/testData/eCommerce.json"),
				StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public String getScreenshotPath(String testCaseName, AppiumDriver driver) {
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+ "/TestCaseScreenshots/" + testCaseName + ".png";
		try {
			FileUtils.copyFile(source, new File(destinationFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationFile;
		
		// 1. Capture and place it in the folder
		// 2. Extent report pick the file and attach the report
	}
}
