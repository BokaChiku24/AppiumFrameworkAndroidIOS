package utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.ios.IOSDriver;

public class IOSActions extends AppiumUtils{

	IOSDriver driver;

	public IOSActions(IOSDriver driver) {
		this.driver = driver;

	}

	public void longPressIOS(WebElement element, int duration) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("element", ((RemoteWebElement) element).getId());
		map.put("duration", duration);
		((JavascriptExecutor) driver).executeScript("mobile: touchAndHold", map);
	}

	public void scollIOS(WebElement element, String direction) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("element", ((RemoteWebElement) element).getId());
		map.put("direction", direction);
		((JavascriptExecutor) driver).executeScript("mobile: scroll", map);
	}

}
