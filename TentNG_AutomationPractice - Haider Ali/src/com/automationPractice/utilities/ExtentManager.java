package com.automationPractice.utilities;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	private static ExtentReports extent;
	private static ExtentSparkReporter reporter;
	
	public static ExtentReports createInstance() {
		String fileName = getReportName();
		String directory = System.getProperty("user.dir")+"/reports/";
		new File(directory).mkdirs();
		String path = directory+fileName;
		reporter = new ExtentSparkReporter(path);
		extent = new ExtentReports();
		extent.setSystemInfo("Organization", "Kualitatem");
		extent.setSystemInfo("Browser", "Chrome");
		extent.attachReporter(reporter);
		return extent;
	}
	
	public static String getReportName() {
		Date date = new Date();
		String filename = "AutomationReport_"+date.toString().replace(":", "_").replace(" ", "_")+".html";
		return filename;
	}
}
