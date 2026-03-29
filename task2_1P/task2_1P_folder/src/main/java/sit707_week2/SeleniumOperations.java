package sit707_week2;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class SeleniumOperations {

	public static void sleep(int sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void officeworks_registration_page(String url) {
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		System.out.println("Fire up chrome browser.");
		WebDriver driver = new ChromeDriver();
		
		System.out.println("Driver info: " + driver);
		driver.get(url);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname")));
		System.out.println("Page loaded and form is visible.");

		WebElement element = driver.findElement(By.id("firstname"));
		System.out.println("Found element: " + element);
		element.sendKeys("Prem Kumar");

		WebElement element2 = driver.findElement(By.id("lastname"));
		System.out.println("Found element: " + element2);
		element2.sendKeys("Lingutla");

		WebElement element3 = driver.findElement(By.id("phoneNumber"));
		System.out.println("Found element: " + element3);
		element3.sendKeys("0438159115");

		WebElement element4 = driver.findElement(By.id("email"));
		System.out.println("Found element: " + element4);
		element4.sendKeys("premkumardev909@gmail.com");

		WebElement element5 = driver.findElement(By.id("password"));
		System.out.println("Found element: " + element5);
		element5.sendKeys("Premkumar@123");

		WebElement element6 = driver.findElement(By.id("confirmPassword"));
		System.out.println("Found element: " + element6);
		element6.sendKeys("Premkumar@1234");

		WebElement createBtn = driver.findElement(By.cssSelector("[data-testid='account-action-btn']"));
		createBtn.click();

		sleep(2);

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(
					screenshot.toPath(),
					new File("officeworks_screenshot.png").toPath(),
					StandardCopyOption.REPLACE_EXISTING
			);
			System.out.println("Screenshot saved: officeworks_screenshot.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sleep(2);
		driver.close();	
	}
	
	
}
