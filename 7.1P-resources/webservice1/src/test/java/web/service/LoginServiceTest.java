package web.service;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LoginServiceTest {

    private static final String CHROME_DRIVER_PATH = "/usr/local/bin/chromedriver";

    private static final String LOGIN_HTML_PATH = "file:///Users/premkumar/Desktop/MSofIT/T3 2026/SIT707/7.1P-resources/pages/login.html";

    private void sleep(long sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private String dobForAge(int age) {
        return LocalDate.now().minusYears(age).format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Test
    public void testLoginSuccess() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        System.out.println("Driver info: " + driver);

        driver.navigate().to(LOGIN_HTML_PATH);
        sleep(5);

        WebElement ele = driver.findElement(By.id("username"));
        ele.clear();
        ele.sendKeys("admin");

        ele = driver.findElement(By.id("passwd"));
        ele.clear();
        ele.sendKeys("admin123");

        ele = driver.findElement(By.id("dob"));
        ele.clear();
        ele.sendKeys(dobForAge(25));

        ele = driver.findElement(By.cssSelector("[type=submit]"));
        ele.submit();
        sleep(5);

        String title = driver.getTitle();
        System.out.println("FT-01 Title: " + title);
        Assert.assertEquals("success", title);

        driver.close();
    }

    @Test
    public void testLoginSuccess_ExactAge18() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.navigate().to(LOGIN_HTML_PATH);
        sleep(3);

        WebElement ele = driver.findElement(By.id("username"));
        ele.clear();
        ele.sendKeys("admin");

        ele = driver.findElement(By.id("passwd"));
        ele.clear();
        ele.sendKeys("admin123");

        ele = driver.findElement(By.id("dob"));
        ele.clear();
        ele.sendKeys(dobForAge(18));

        ele = driver.findElement(By.cssSelector("[type=submit]"));
        ele.submit();
        sleep(3);

        String title = driver.getTitle();
        System.out.println("FT-02 Title: " + title);
        Assert.assertEquals("FT-02 expected success for exactly 18 y/o", "success", title);

        driver.close();
    }

    @Test
    public void testLoginFail_Underage17() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.navigate().to(LOGIN_HTML_PATH);
        sleep(3);

        WebElement ele = driver.findElement(By.id("username"));
        ele.clear();
        ele.sendKeys("admin");

        ele = driver.findElement(By.id("passwd"));
        ele.clear();
        ele.sendKeys("admin123");

        ele = driver.findElement(By.id("dob"));
        ele.clear();
        ele.sendKeys(dobForAge(17));   // BVA: one year below minimum

        ele = driver.findElement(By.cssSelector("[type=submit]"));
        ele.submit();
        sleep(3);

        String title = driver.getTitle();
        System.out.println("FT-03 Title: " + title);
        Assert.assertEquals("FT-03 expected fail for underage (17)", "fail", title);

        driver.close();
    }

    @Test
    public void testLoginFail_WrongUsername() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.navigate().to(LOGIN_HTML_PATH);
        sleep(3);

        WebElement ele = driver.findElement(By.id("username"));
        ele.clear();
        ele.sendKeys("wrongUser");

        ele = driver.findElement(By.id("passwd"));
        ele.clear();
        ele.sendKeys("admin123");

        ele = driver.findElement(By.id("dob"));
        ele.clear();
        ele.sendKeys(dobForAge(25));

        ele = driver.findElement(By.cssSelector("[type=submit]"));
        ele.submit();
        sleep(3);

        String title = driver.getTitle();
        System.out.println("FT-04 Title: " + title);
        Assert.assertEquals("FT-04 expected fail for wrong username", "fail", title);

        driver.close();
    }

    @Test
    public void testLoginFail_WrongPassword() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.navigate().to(LOGIN_HTML_PATH);
        sleep(3);

        WebElement ele = driver.findElement(By.id("username"));
        ele.clear();
        ele.sendKeys("admin");

        ele = driver.findElement(By.id("passwd"));
        ele.clear();
        ele.sendKeys("wrongPass");

        ele = driver.findElement(By.id("dob"));
        ele.clear();
        ele.sendKeys(dobForAge(25));

        ele = driver.findElement(By.cssSelector("[type=submit]"));
        ele.submit();
        sleep(3);

        String title = driver.getTitle();
        System.out.println("FT-05 Title: " + title);
        Assert.assertEquals("FT-05 expected fail for wrong password", "fail", title);

        driver.close();
    }

    @Test
    public void testLoginFail_BothCredentialsWrong() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.navigate().to(LOGIN_HTML_PATH);
        sleep(3);

        WebElement ele = driver.findElement(By.id("username"));
        ele.clear();
        ele.sendKeys("notAdmin");

        ele = driver.findElement(By.id("passwd"));
        ele.clear();
        ele.sendKeys("notPass");

        ele = driver.findElement(By.id("dob"));
        ele.clear();
        ele.sendKeys(dobForAge(25));

        ele = driver.findElement(By.cssSelector("[type=submit]"));
        ele.submit();
        sleep(3);

        String title = driver.getTitle();
        System.out.println("FT-06 Title: " + title);
        Assert.assertEquals("FT-06 expected fail for both credentials wrong", "fail", title);

        driver.close();
    }

    @Test
    public void testLoginFail_EmptyUsername() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.navigate().to(LOGIN_HTML_PATH);
        sleep(3);

        WebElement ele = driver.findElement(By.id("passwd"));
        ele.clear();
        ele.sendKeys("admin123");

        ele = driver.findElement(By.id("dob"));
        ele.clear();
        ele.sendKeys(dobForAge(25));

        ele = driver.findElement(By.cssSelector("[type=submit]"));
        ele.submit();
        sleep(3);

        String title = driver.getTitle();
        System.out.println("FT-07 Title: " + title);
        Assert.assertEquals("FT-07 expected fail for empty username", "fail", title);

        driver.close();
    }
    @Test
    public void testLoginFail_EmptyPassword() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.navigate().to(LOGIN_HTML_PATH);
        sleep(3);

        WebElement ele = driver.findElement(By.id("username"));
        ele.clear();
        ele.sendKeys("admin");

        ele = driver.findElement(By.id("dob"));
        ele.clear();
        ele.sendKeys(dobForAge(25));

        ele = driver.findElement(By.cssSelector("[type=submit]"));
        ele.submit();
        sleep(3);

        String title = driver.getTitle();
        System.out.println("FT-08 Title: " + title);
        Assert.assertEquals("FT-08 expected fail for empty password", "fail", title);

        driver.close();
    }
    @Test
    public void testLoginFail_EmptyDob() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.navigate().to(LOGIN_HTML_PATH);
        sleep(3);

        WebElement ele = driver.findElement(By.id("username"));
        ele.clear();
        ele.sendKeys("admin");

        ele = driver.findElement(By.id("passwd"));
        ele.clear();
        ele.sendKeys("admin123");


        ele = driver.findElement(By.cssSelector("[type=submit]"));
        ele.submit();
        sleep(3);

        String title = driver.getTitle();
        System.out.println("FT-09 Title: " + title);
        Assert.assertEquals("FT-09 expected fail for empty DOB", "fail", title);

        driver.close();
    }
    @Test
    public void testLoginFail_AllFieldsEmpty() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.navigate().to(LOGIN_HTML_PATH);
        sleep(3);

        WebElement ele = driver.findElement(By.cssSelector("[type=submit]"));
        ele.submit();
        sleep(3);

        String title = driver.getTitle();
        System.out.println("FT-10 Title: " + title);
        Assert.assertEquals("FT-10 expected fail when all fields are empty", "fail", title);

        driver.close();
    }
    @Test
    public void testLoginFail_FutureDob() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.navigate().to(LOGIN_HTML_PATH);
        sleep(3);

        WebElement ele = driver.findElement(By.id("username"));
        ele.clear();
        ele.sendKeys("admin");

        ele = driver.findElement(By.id("passwd"));
        ele.clear();
        ele.sendKeys("admin123");

        String futureDob = LocalDate.now().plusYears(5)
                .format(DateTimeFormatter.ISO_LOCAL_DATE);
        ele = driver.findElement(By.id("dob"));
        ele.clear();
        ele.sendKeys(futureDob);

        ele = driver.findElement(By.cssSelector("[type=submit]"));
        ele.submit();
        sleep(3);

        String title = driver.getTitle();
        System.out.println("FT-11 Title: " + title);
        Assert.assertEquals("FT-11 expected fail for future DOB", "fail", title);

        driver.close();
    }
    @Test
    public void testLoginFail_CaseSensitiveUsername() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.navigate().to(LOGIN_HTML_PATH);
        sleep(3);

        WebElement ele = driver.findElement(By.id("username"));
        ele.clear();
        ele.sendKeys("Admin");   // capital A — should not match "admin"

        ele = driver.findElement(By.id("passwd"));
        ele.clear();
        ele.sendKeys("admin123");

        ele = driver.findElement(By.id("dob"));
        ele.clear();
        ele.sendKeys(dobForAge(25));

        ele = driver.findElement(By.cssSelector("[type=submit]"));
        ele.submit();
        sleep(3);

        String title = driver.getTitle();
        System.out.println("FT-12 Title: " + title);
        Assert.assertEquals("FT-12 expected fail: username is case-sensitive", "fail", title);

        driver.close();
    }
}