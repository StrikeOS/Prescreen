package declarations;

import java.util.List;
import org.gradle.Browser;
import org.gradle.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchPage {
	By searchTextfieldLocator			= By.name("q");
	By searchButtonLocator				= By.xpath("//*[@id='sblsbb']/button");
	By firstSearchResultLinkLocator		= By.xpath("//*[@id='rso']/div[1]/div[1]/div/h3/a");
	By allSearchResultLinksLocator		= By.xpath("//*[@id='rso']//cite");
	By nextButtonLocator				= By.id("pnnext");
	By pageNumeratorLocator				= By.xpath("//td[@class='cur']");

    public void invoke() {
    	String sURL = "https://www.google.com";
    	Log.info("[ACTION] Trying to load page: " + sURL);
    	Browser.getDriver().get(sURL);
        if (!"Google".equals(Browser.getDriver().getTitle())) {
            throw new IllegalStateException("This is not the Google page");
        }
        Log.info("[STATUS] OK");
    }

    public void searchFor(String patternToSearch) {
    	Log.info("[ACTION] Trying to search: " + patternToSearch + " and wait for results loaded");
    	WebElement searhcBox = Browser.getDriver().findElement(searchTextfieldLocator);
    	searhcBox.sendKeys(patternToSearch);
    	Browser.getDriver().findElement(searchButtonLocator).click();//searhcBox.submit();
    	WebDriverWait waitSearchRes = new WebDriverWait(Browser.getDriver(),5);
    	waitSearchRes.until(ExpectedConditions.elementToBeClickable(firstSearchResultLinkLocator));
    	Log.info("[STATUS] OK");
    }
    
    public void clickFirstFoundLink () {
    	Log.info("[ACTION] Trying to click first found link and wait until page loaded");
    	WebElement firstFoundLink = (new WebDriverWait(Browser.getDriver(), 5))
	              .until(ExpectedConditions.elementToBeClickable(firstSearchResultLinkLocator));
		String sURLtowait = Browser.getDriver().findElements(allSearchResultLinksLocator).get(0).getText();
		firstFoundLink.click();
		WebDriverWait waitDomainName = new WebDriverWait(Browser.getDriver(),5);
		waitDomainName.until(ExpectedConditions.urlContains(sURLtowait));
		Log.info("[STATUS] OK");
    }
    
    public boolean findLinkViaSearchResults (String linkToFind) {
    	Log.info("[ACTION] Verify that search show expected domain");
    	List<WebElement> ElementCollection = (List<WebElement>) Browser.getDriver().findElements(allSearchResultLinksLocator);
    	boolean bFound = false;
    	for (WebElement link : ElementCollection){
    		//Log.info("link -> " + link.getText());
    		if (link.getText().equals(linkToFind)||link.getText().equals(linkToFind+"\\")){
    			bFound = true;
    			break;
    		}
    	}
    	Log.info("[STATUS] OK");
    	return bFound;
    }
    public void goToNextSearchResultPage (int pageNumber){
    	Log.info("[ACTION] Trying to go to the next page: " + pageNumber);
    	WebElement nextPage = (new WebDriverWait(Browser.getDriver(), 5))
    			.until(ExpectedConditions.elementToBeClickable(nextButtonLocator));
		nextPage.click();
		(new WebDriverWait(Browser.getDriver(), 5))
				.until(ExpectedConditions.textToBePresentInElementLocated(pageNumeratorLocator, Integer.toString(pageNumber)));
		Log.info("[STATUS] OK");
    }
}