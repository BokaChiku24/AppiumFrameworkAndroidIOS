package org.kunalchavan.testutils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

	static ExtentReports extent;

	public static ExtentReports getExtentReportObject() {
		String path = System.getProperty("user.dir") + "/ExtentReport/index.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("Mobile Test Automation");
		report.config().setDocumentTitle("Appium Test Result");

		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Tester", "Kunal Chavan");
		return extent;

	}

}
