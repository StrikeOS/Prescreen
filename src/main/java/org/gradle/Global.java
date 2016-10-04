package org.gradle;

public class Global {
	public static final String HOME				= "HOME";
	public static final String GRID				= "GRID";
	public static final String BROWSESTACK		= "BROWSESTACK";
	// =============
	public static String TESTENV			= HOME;
	public static void setTESTENV(String testEnv) { 
		Log.info("SET: Global.TESTENV = " + testEnv); 
		Global.TESTENV = testEnv;
    }
    public String getTESTENV() {return TESTENV;}

	// Supported Browsers
	public static final String FIREFOX				= "Firefox";//[Child 544] ###!!! ABORT: Aborting on channel error --> //http://stackoverflow.com/questions/39196419/abort-aborting-on-channel-error-line-2076-in-selenium-3-0-0-beta-2
	public static final String CHROME				= "Chrome";
	public static final String OPERA				= "Opera";
	public static final String SAFARI				= "Safari";
	public static final String INTERNET_EXPLORER	= "IntenetExplorer";
	public static final String EDGE					= "Edge";
	
	// Drivers
	public static final String IE_DRIVER_PATH		= "src/main/resources/IEDriverServer_x64.exe";
	public static final String CHROME_DRIVER_PATH	= "src/main/resources/chromedriver.exe";
	public static final String OPERA_DRIVER_PATH	= "src/main/resources/operadriver.exe";
	public static final String FIREFOX_DRIVER_PATH	= "src/main/resources/Mozilla.exe";

	// Configurations
	public static String BROWSER = FIREFOX;
	public static void setBROWSER(String browserType) { 
		Log.info("SET: Global.BROWSER = " + browserType); 
		Global.BROWSER = browserType;
    }
    public String getBROWSER() {return BROWSER;}

	// Timers
	public static final int DEFAULT_EXPLICIT_WAIT			= 2;
	public static final int DEFAULT_IMPLICIT_WAIT			= 10;
	public static final int DEFAULT_TIME_TO_WAIT_ACTIONS	= 1000;
	public static final int DEFAULT_TIME_TO_WAIT_PAGE		= 3;
	public static final int DEFAULT_TIME_TO_WAIT_CONTROL	= 3;

	// Logs and reports
	public static String logFolderPath;
	public static final String REPORT_LOCATION = "build/reports";
	public static final String REPORT_HTML_CLASSES_LOCATION = "build/reports/classes";
}