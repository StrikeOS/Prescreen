package org.gradle;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import declarations.GoogleSearchPage;

/** 
 *	Testing task for Automation Engineer 
 *	(Selenium / WebDriver)
 *	TESTS TO DEVELOP: 
 *	1.	Test #1. Open Google. Search for “automation”. Open the first link on search results page. Verify that title contains searched word
 *	2.	Test #2. Open Google. Search for “automation”. Verify that there is expected domain (“testautomationday.com”) on search results  pages (page: 1-5).
 *	REQUIREMENTS AND RECOMMENDATIONS:
 *	-	Any xUnit or related framework could be used
 *	-	Page object pattern should be used
 *	-	Dependency resolution could be done inside project (NuGet, maven, etc)
 *	-	Plus if browser (Chrome or Firefox) is configured in external configuration file
 *	-	Plus if logger is added
*/

@Listeners({WebTestListener.class})
public class TestcasesPrescreen {
	@BeforeMethod()
	public static void beforeMethod() throws MalformedURLException{	
		Global.setTESTENV(Global.HOME);
		Global.setBROWSER(Global.CHROME);
		Log.info("beforeMethod for TESTENV = " + Global.TESTENV);
		Browser.setBrowser(new Browser(Global.BROWSER));
		Browser.getDriver().manage().timeouts().pageLoadTimeout(Global.DEFAULT_TIME_TO_WAIT_PAGE, TimeUnit.SECONDS);
		Browser.getDriver().manage().timeouts().implicitlyWait(Global.DEFAULT_IMPLICIT_WAIT, TimeUnit.SECONDS);
		Browser.getDriver().manage().window().maximize();		
	}
	@AfterMethod()
	public static void afterMethod() {
		Browser.getDriver().quit();
	}
	@Test(groups = {"Testcases_prescreen"}, enabled = true)
	public static void prescreen_test1(){
		/** TEST ANNOTATION
		 *  Purpose:	Open Google. Search for “automation”. Open the first link on search results page. Verify that title contains searched word
		 *  Steps:
		 *  	
		 *	Expected:	Title contains searched word
		*/
		String patternToCheck = "automation";
		GoogleSearchPage gPage = new GoogleSearchPage();
		gPage.invoke();
		gPage.searchFor(patternToCheck);
		gPage.clickFirstFoundLink();
		Assert.assertTrue(org.apache.commons.lang3.StringUtils.containsIgnoreCase(Browser.getDriver().getTitle(),patternToCheck), "[FAIL] First link on search results page doesnt contain searched word: " + patternToCheck + " Actual title: " + Browser.getDriver().getTitle());
	}
	@Test(groups = {"Testcases_prescreen"}, enabled = true)
	public static void prescreen_test2(){
		/** TEST ANNOTATION
		 *  Purpose:	Test #2. Open Google. Search for “automation”. Verify that there is expected domain (“testautomationday.com”) on search results  pages (page: 1-5).
		 *  Steps:
		 *  	
		 *	Expected:
		*/
		String patternToCheck	= "automation";
		String linkToFind		= "testautomationday.com";
		int pagesToSearch		= 5;
		GoogleSearchPage gPage = new GoogleSearchPage();
		gPage.invoke();
		gPage.searchFor(patternToCheck);
		boolean bFound = false;
		int pageNumber = 1;
		do{
			bFound = gPage.findLinkViaSearchResults(linkToFind);
			pageNumber++;
			if (pageNumber != pagesToSearch + 1){gPage.goToNextSearchResultPage(pageNumber);}	
		}while(!bFound && pageNumber <= pagesToSearch);
		Assert.assertTrue(bFound,"[FAIL] Verify that there is expected domain (“testautomationday.com”) on search results  pages (page: 1-5)");
	}
}
