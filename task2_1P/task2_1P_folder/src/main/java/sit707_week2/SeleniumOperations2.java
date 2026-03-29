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

public class SeleniumOperations2 {

    public static void sleep(int sec) {
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void stripe_registeration_page(String url) {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        System.out.println("Fire up chrome browser.");
        WebDriver driver = new ChromeDriver();

        System.out.println("Driver info: " + driver);
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-email-input")));
        System.out.println("Page loaded and form is visible.");

        WebElement element3 = driver.findElement(By.id("register-email-input"));
        System.out.println("Found element: " + element3);
        element3.sendKeys("premkumardev909@gmail.com");

        WebElement element = driver.findElement(By.id("register-name-input"));
        System.out.println("Found element: " + element);
        element.sendKeys("Prem Kumar");

        WebElement element4 = driver.findElement(By.id("register-password-input-with-description"));
        System.out.println("Found element: " + element4);
        element4.sendKeys("Premkumar@123");

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='register-submit-button']")));
        WebElement createBtn = driver.findElement(By.cssSelector("[data-testid='register-submit-button']"));
        createBtn.click();

        sleep(2);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(
                    screenshot.toPath(),
                    new File("stripe_screenshot.png").toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );
            System.out.println("Screenshot saved: stripe_screenshot.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sleep(2);
        driver.close();
    }


}
