package utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

// Common for Android & IOS
public class AppiumUtils {
	public AppiumDriver driver;
	public double priceTotal = 0;
	public double productFinal = 0;
	WebDriverWait wait;

	public AppiumUtils(AppiumDriver driver) {
		this.driver = driver;

	}

	public void addition(List<WebElement> list) {
		for (int i = 0; i < list.size(); i++) {
			priceTotal = priceTotal + Double.parseDouble(list.get(i).getText().replace("$", ""));
		}
	}

	public void waitAttributeContainsById(String id, String attribute, String value) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id(id)), attribute, value));
	}

	public void waitAttributeContainsByAccessibilityId(String id, String attribute, String value) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(AppiumBy.accessibilityId(id)), attribute,
				value));
	}

	public static List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "/src/test/resources/testData/eCommerce.json"),
				StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
}
