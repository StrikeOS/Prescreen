package org.gradle;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class WebTestListener implements ITestListener {

	private static String resultFileName;
	
	@Override
	public void onTestStart(ITestResult result) {
		resultFileName = Log.getFinalFileName(Log.logFolderLocation(), result.getName());
		Log.info("==============================================");
		Log.info("Started test: " + resultFileName);
		Log.info("==============================================");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Log.info("==============================================");
		Log.info("Finished test: " + resultFileName + " with status: SUCCESS");
		Log.info("==============================================");
		Log.info("");
		Log.writeLog(resultFileName);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Log.info("==============================================");
		Log.info("Finished test: "+resultFileName+" with status: FAILED");
		Log.info("==============================================");
		Log.info("");
		Browser.sleep(1500);
		Log.writeLog(resultFileName);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Log.info("==============================================");
		Log.info("Finished test: "+resultFileName+" with status: SKIPPED");
		Log.info("==============================================");
		Log.info("");
		Log.sessionLog.clear();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
		Log.info("Started tests from the class - " + context.getClass().getName());
	}

	@Override
	public void onFinish(ITestContext context) {
	}
}
