package com.candreotti;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class SeleniumTests {

	private WebDriver webDriver;

	@BeforeClass
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}

	@Before
	public void setupTest() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		webDriver = new ChromeDriver(chromeOptions);
	}

	@After
	public void teardown() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}

	@Test
	public void testCanLoadTargetUrl() {
		webDriver.get("https://develop.amp-sbx.prh.com");

		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		WebElement signedInHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.okta-sign-in-header.auth-header")));

		assertTrue(signedInHeader.isDisplayed());
	}

}
