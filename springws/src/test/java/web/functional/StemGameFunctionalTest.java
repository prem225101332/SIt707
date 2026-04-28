package web.functional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StemGameFunctionalTest {

    private static final String BASE_URL = "http://localhost:8080";
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void loginValid() throws InterruptedException {
        driver.get(BASE_URL + "/login");
        Thread.sleep(1000);
        driver.findElement(By.name("username")).sendKeys("ahsan");
        driver.findElement(By.name("passwd")).sendKeys("ahsan_pass");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Thread.sleep(1500);
    }

    private void submitForm(String n1, String n2, String result) throws InterruptedException {
        if (n1 != null) driver.findElement(By.name("number1")).sendKeys(n1);
        if (n2 != null) driver.findElement(By.name("number2")).sendKeys(n2);
        if (result != null) driver.findElement(By.name("result")).sendKeys(result);
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Thread.sleep(1500);
    }

    @Test
    public void testLoginPage_Loads() throws InterruptedException {
        driver.get(BASE_URL + "/login");
        Thread.sleep(1000);
        Assert.assertTrue(driver.getPageSource().contains("User name"));
    }

    @Test
    public void testLogin_InvalidCredentials_StaysOnLogin() throws InterruptedException {
        driver.get(BASE_URL + "/login");
        Thread.sleep(1000);
        driver.findElement(By.name("username")).sendKeys("wrong");
        driver.findElement(By.name("passwd")).sendKeys("wrong");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Thread.sleep(1500);
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
        Assert.assertTrue(driver.getPageSource().contains("Incorrect credentials"));
    }

    @Test
    public void testLogin_ValidCredentials_GoesToQ1() throws InterruptedException {
        loginValid();
        Assert.assertTrue(driver.getCurrentUrl().contains("/q1"));
    }

    @Test
    public void testQ1_CorrectAnswer_GoesToQ2() throws InterruptedException {
        loginValid();
        submitForm("3", "4", "7");
        Assert.assertTrue(driver.getCurrentUrl().contains("/q2"));
    }

    @Test
    public void testQ1_WrongAnswer_StaysOnQ1WithMessage() throws InterruptedException {
        loginValid();
        submitForm("3", "4", "99");
        Assert.assertTrue(driver.getCurrentUrl().contains("/q1"));
        Assert.assertTrue(driver.getPageSource().contains("Wrong answer"));
    }

    @Test
    public void testQ1_EmptyFields_ShowsMessageNotErrorPage() throws InterruptedException {
        loginValid();
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Thread.sleep(1500);
        Assert.assertFalse(driver.getPageSource().toLowerCase().contains("whitelabel error"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/q1"));
    }

    @Test
    public void testQ2_CorrectAnswer_GoesToQ3() throws InterruptedException {
        loginValid();
        submitForm("3", "4", "7");   // pass Q1
        submitForm("9", "4", "5");   // pass Q2
        Assert.assertTrue(driver.getCurrentUrl().contains("/q3"));
    }

    @Test
    public void testQ2_WrongAnswer_StaysOnQ2WithMessage() throws InterruptedException {
        loginValid();
        submitForm("3", "4", "7");   // pass Q1
        submitForm("9", "4", "99");  // wrong Q2
        Assert.assertTrue(driver.getCurrentUrl().contains("/q2"));
        Assert.assertTrue(driver.getPageSource().contains("Wrong answer"));
    }

    @Test
    public void testQ2_EmptyFields_ShowsMessageNotErrorPage() throws InterruptedException {
        loginValid();
        submitForm("3", "4", "7");   // pass Q1
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Thread.sleep(1500);
        Assert.assertFalse(driver.getPageSource().toLowerCase().contains("whitelabel error"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/q2"));
    }

    @Test
    public void testQ3_PageLoads() throws InterruptedException {
        loginValid();
        submitForm("3", "4", "7");   // pass Q1
        submitForm("9", "4", "5");   // pass Q2
        Assert.assertTrue(driver.getCurrentUrl().contains("/q3"));
        Assert.assertTrue(driver.getPageSource().contains("Q3"));
    }

    @Test
    public void testQ3_CorrectAnswer_LeavesQ3() throws InterruptedException {
        loginValid();
        submitForm("3", "4", "7");   // pass Q1
        submitForm("9", "4", "5");   // pass Q2
        submitForm("2", "3", "6");   // pass Q3
        Assert.assertFalse(driver.getCurrentUrl().contains("/q3"));
    }

    @Test
    public void testQ3_WrongAnswer_StaysOnQ3WithMessage() throws InterruptedException {
        loginValid();
        submitForm("3", "4", "7");   // pass Q1
        submitForm("9", "4", "5");   // pass Q2
        submitForm("2", "3", "99");  // wrong Q3
        Assert.assertTrue(driver.getCurrentUrl().contains("/q3"));
        Assert.assertTrue(driver.getPageSource().contains("Wrong answer"));
    }

    @Test
    public void testQ3_EmptyFields_ShowsMessageNotErrorPage() throws InterruptedException {
        loginValid();
        submitForm("3", "4", "7");   // pass Q1
        submitForm("9", "4", "5");   // pass Q2
        Thread.sleep(1500);
        Assert.assertFalse(driver.getPageSource().toLowerCase().contains("whitelabel error"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/q3"));
    }
}