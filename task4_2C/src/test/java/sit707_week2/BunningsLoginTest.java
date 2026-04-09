package sit707_week4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BunningsLoginTest {

    private static final String VALID_EMAIL    = "your.email@example.com";
    private static final String VALID_PASSWORD = "YourPassword123";

    private static final String INVALID_EMAIL    = "notauser_xyz123@fake.com";
    private static final String INVALID_PASSWORD = "WrongPass999!";

    private static final String LOGIN_URL   = "https://www.bunnings.com.au/login";
    private static final String ACCOUNT_URL = "https://www.bunnings.com.au/my-account";

    // Selenium locators identified via browser code inspection
    private static final String EMAIL_FIELD_ID    = "loginForm_emailAddress";
    private static final String PASSWORD_FIELD_ID = "loginForm_password";
    private static final String SUBMIT_BUTTON_CSS = "button[data-locator='login-submit']";

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        // Set path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.home") + "/java_lib/chromedriver");

        ChromeOptions options = new ChromeOptions();
        // Uncomment the next line to run headless (no visible browser window)
        // options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--window-size=1280,800");

        driver = new ChromeDriver(options);
        wait   = new WebDriverWait(driver, 10);
        System.out.println("Browser started: " + driver);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ---------------------------------------------------------------
    // Helper: navigate to login page and fill/submit the form
    // ---------------------------------------------------------------
    private void attemptLogin(String email, String password) {
        driver.get(LOGIN_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(EMAIL_FIELD_ID)));

        WebElement emailField    = driver.findElement(By.id(EMAIL_FIELD_ID));
        WebElement passwordField = driver.findElement(By.id(PASSWORD_FIELD_ID));
        WebElement submitButton  = driver.findElement(By.cssSelector(SUBMIT_BUTTON_CSS));

        emailField.clear();
        emailField.sendKeys(email);

        passwordField.clear();
        passwordField.sendKeys(password);

        submitButton.click();

        // Allow page to react
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    // ---------------------------------------------------------------
    // Keep original 2 tests from task2_1P and MAKE THEM PASS
    // ---------------------------------------------------------------

    @Test
    public void testStudentIdentity() {
        String studentId = "220000000"; // Replace with YOUR student ID
        Assert.assertNotNull("Student ID must not be null", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "John Smith"; // Replace with YOUR name
        Assert.assertNotNull("Student name must not be null", studentName);
    }

    // ---------------------------------------------------------------
    // TC1: Valid email + Valid password → should redirect to /my-account
    // ---------------------------------------------------------------
    @Test
    public void testTC1_ValidEmail_ValidPassword_SuccessfulLogin() {
        System.out.println("TC1: Valid email + Valid password");
        attemptLogin(VALID_EMAIL, VALID_PASSWORD);

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after login: " + currentUrl);

        Assert.assertTrue(
                "TC1 FAILED: Expected redirect to /my-account but got: " + currentUrl,
                currentUrl.contains("my-account") || currentUrl.contains("account")
        );
    }

    // ---------------------------------------------------------------
    // TC2: Valid email + Invalid password → should stay on /login
    // ---------------------------------------------------------------
    @Test
    public void testTC2_ValidEmail_InvalidPassword_FailedLogin() {
        System.out.println("TC2: Valid email + Invalid password");
        attemptLogin(VALID_EMAIL, INVALID_PASSWORD);

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after login attempt: " + currentUrl);

        Assert.assertTrue(
                "TC2 FAILED: Expected to remain on login page but got: " + currentUrl,
                currentUrl.contains("login")
        );
    }

    // ---------------------------------------------------------------
    // TC3: Invalid email + Valid password → should stay on /login
    // ---------------------------------------------------------------
    @Test
    public void testTC3_InvalidEmail_ValidPassword_FailedLogin() {
        System.out.println("TC3: Invalid email + Valid password");
        attemptLogin(INVALID_EMAIL, VALID_PASSWORD);

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after login attempt: " + currentUrl);

        Assert.assertTrue(
                "TC3 FAILED: Expected to remain on login page but got: " + currentUrl,
                currentUrl.contains("login")
        );
    }

    // ---------------------------------------------------------------
    // TC4: Invalid email + Invalid password → should stay on /login
    // ---------------------------------------------------------------
    @Test
    public void testTC4_InvalidEmail_InvalidPassword_FailedLogin() {
        System.out.println("TC4: Invalid email + Invalid password");
        attemptLogin(INVALID_EMAIL, INVALID_PASSWORD);

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after login attempt: " + currentUrl);

        Assert.assertTrue(
                "TC4 FAILED: Expected to remain on login page but got: " + currentUrl,
                currentUrl.contains("login")
        );
    }

    // ---------------------------------------------------------------
    // TC5: Empty email + Empty password → should stay on /login
    // ---------------------------------------------------------------
    @Test
    public void testTC5_EmptyEmail_EmptyPassword_FailedLogin() {
        System.out.println("TC5: Empty email + Empty password");
        attemptLogin("", "");

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after login attempt: " + currentUrl);

        Assert.assertTrue(
                "TC5 FAILED: Expected to remain on login page but got: " + currentUrl,
                currentUrl.contains("login")
        );
    }

    // ---------------------------------------------------------------
    // TC6: Empty email + Valid password → should stay on /login
    // ---------------------------------------------------------------
    @Test
    public void testTC6_EmptyEmail_ValidPassword_FailedLogin() {
        System.out.println("TC6: Empty email + Valid password");
        attemptLogin("", VALID_PASSWORD);

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after login attempt: " + currentUrl);

        Assert.assertTrue(
                "TC6 FAILED: Expected to remain on login page but got: " + currentUrl,
                currentUrl.contains("login")
        );
    }

    // ---------------------------------------------------------------
    // TC7: Valid email + Empty password → should stay on /login
    // ---------------------------------------------------------------
    @Test
    public void testTC7_ValidEmail_EmptyPassword_FailedLogin() {
        System.out.println("TC7: Valid email + Empty password");
        attemptLogin(VALID_EMAIL, "");

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after login attempt: " + currentUrl);

        Assert.assertTrue(
                "TC7 FAILED: Expected to remain on login page but got: " + currentUrl,
                currentUrl.contains("login")
        );
    }
}