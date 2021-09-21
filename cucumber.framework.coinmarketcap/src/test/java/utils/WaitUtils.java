package utils;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaitUtils {
	public static final Logger LOGGER = LoggerFactory.getLogger(WaitUtils.class);
	private static WebDriver driver;
	private static WebDriverWait driverWait;

	public WaitUtils(WebDriver driver){
		this.setDriver(driver);
	}
	
	public void invokeImplicityWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	
	public void invokeExplicitWait(WebDriver driver, By locator) {
		driverWait = new WebDriverWait(driver, 10);
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void invokeFluentWait(WebDriver driver) {
		@SuppressWarnings("unused")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
				.withTimeout(30, TimeUnit.SECONDS) 			
				.pollingEvery(5, TimeUnit.SECONDS) 			
				.ignoring(NoSuchElementException.class);
	}
	
	public void staticWait(int timeInMS) {
		try {
			LOGGER.info("Waiting for " + timeInMS + "Milli Seconds");
			Thread.sleep(timeInMS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		WaitUtils.driver = driver;
	}
}
