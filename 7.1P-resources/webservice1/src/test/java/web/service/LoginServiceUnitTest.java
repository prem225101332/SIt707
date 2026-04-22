package web.service;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LoginServiceUnitTest {

    private String dobForAge(int age) {
        return LocalDate.now().minusYears(age).format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
    private String dobForAgeAndDays(int age, int extraDays) {
        return LocalDate.now().minusYears(age).minusDays(extraDays).format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Test
    public void testLogin_ValidCredentials_Age18_ShouldSucceed() {
        String result = LoginService.login("admin", "admin123", dobForAge(18)) ? "success" : "fail";
        Assert.assertEquals("Expected success for exactly 18 y/o", "success", result);
    }

    @Test
    public void testLogin_ValidCredentials_Age30_ShouldSucceed() {
        boolean result = LoginService.login("admin", "admin123", dobForAge(30));
        Assert.assertTrue("Expected true for 30 y/o valid credentials", result);
    }

    @Test
    public void testLogin_ValidCredentials_Age100_ShouldSucceed() {
        boolean result = LoginService.login("admin", "admin123", dobForAge(100));
        Assert.assertTrue("Expected true for 100 y/o valid credentials", result);
    }

    @Test
    public void testLogin_ValidCredentials_Age17_ShouldFail() {
        boolean result = LoginService.login("admin", "admin123", dobForAge(17));
        Assert.assertFalse("Expected false for 17 y/o (underage)", result);
    }

    @Test
    public void testLogin_ValidCredentials_Age0_ShouldFail() {
        boolean result = LoginService.login("admin", "admin123", dobForAge(0));
        Assert.assertFalse("Expected false for 0 y/o (underage)", result);
    }

    @Test
    public void testLogin_ValidCredentials_OneDayBelow18_ShouldFail() {
        String dob = LocalDate.now().minusYears(18).plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
        boolean result = LoginService.login("admin", "admin123", dob);
        Assert.assertFalse("Expected false for someone 1 day short of 18", result);
    }

    @Test
    public void testLogin_ValidCredentials_OneDayAfter18_ShouldSucceed() {
        String dob = dobForAgeAndDays(18, 1);
        boolean result = LoginService.login("admin", "admin123", dob);
        Assert.assertTrue("Expected true for someone 1 day past 18", result);
    }
    @Test
    public void testLogin_WrongUsername_ShouldFail() {
        boolean result = LoginService.login("wrongUser", "admin123", dobForAge(25));
        Assert.assertFalse("Expected false for wrong username", result);
    }

    @Test
    public void testLogin_EmptyUsername_ShouldFail() {
        boolean result = LoginService.login("", "admin123", dobForAge(25));
        Assert.assertFalse("Expected false for empty username", result);
    }

    @Test
    public void testLogin_NullUsername_ShouldFail() {
        boolean result = LoginService.login(null, "admin123", dobForAge(25));
        Assert.assertFalse("Expected false for null username", result);
    }
    @Test
    public void testLogin_WrongPassword_ShouldFail() {
        boolean result = LoginService.login("admin", "wrongPass", dobForAge(25));
        Assert.assertFalse("Expected false for wrong password", result);
    }

    @Test
    public void testLogin_EmptyPassword_ShouldFail() {
        boolean result = LoginService.login("admin", "", dobForAge(25));
        Assert.assertFalse("Expected false for empty password", result);
    }

    @Test
    public void testLogin_NullPassword_ShouldFail() {
        boolean result = LoginService.login("admin", null, dobForAge(25));
        Assert.assertFalse("Expected false for null password", result);
    }
    @Test
    public void testLogin_InvalidDobFormat_ShouldFail() {
        boolean result = LoginService.login("admin", "admin123", "25/12/1990");
        Assert.assertFalse("Expected false for invalid DOB format", result);
    }

    @Test
    public void testLogin_DobAsRandomText_ShouldFail() {
        boolean result = LoginService.login("admin", "admin123", "not-a-date");
        Assert.assertFalse("Expected false for non-date DOB string", result);
    }

    @Test
    public void testLogin_NullDob_ShouldFail() {
        boolean result = LoginService.login("admin", "admin123", null);
        Assert.assertFalse("Expected false for null DOB", result);
    }

    @Test
    public void testLogin_EmptyDob_ShouldFail() {
        boolean result = LoginService.login("admin", "admin123", "");
        Assert.assertFalse("Expected false for empty DOB string", result);
    }

    @Test
    public void testLogin_WhitespaceDob_ShouldFail() {
        boolean result = LoginService.login("admin", "admin123", "   ");
        Assert.assertFalse("Expected false for whitespace-only DOB", result);
    }
    @Test
    public void testLogin_DT_Rule3_ValidUserWrongPass_ValidAge_ShouldFail() {
        boolean result = LoginService.login("admin", "badpass", dobForAge(25));
        Assert.assertFalse("DT Rule 3: valid user, wrong pass, valid age → false", result);
    }

    @Test
    public void testLogin_DT_Rule4_WrongUserValidPass_ValidAge_ShouldFail() {
        boolean result = LoginService.login("baduser", "admin123", dobForAge(25));
        Assert.assertFalse("DT Rule 4: wrong user, valid pass, valid age → false", result);
    }

    @Test
    public void testLogin_DT_Rule5_BothInvalidCredentials_ValidAge_ShouldFail() {
        boolean result = LoginService.login("baduser", "badpass", dobForAge(25));
        Assert.assertFalse("DT Rule 5: wrong user, wrong pass, valid age → false", result);
    }

    @Test
    public void testLogin_DT_Rule7_NullUserNullPass_ValidAge_ShouldFail() {
        boolean result = LoginService.login(null, null, dobForAge(25));
        Assert.assertFalse("DT Rule 7: null user, null pass, valid age → false", result);
    }

    @Test
    public void testLogin_FutureDob_ShouldFail() {
        String futureDob = LocalDate.now().plusYears(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
        boolean result = LoginService.login("admin", "admin123", futureDob);
        Assert.assertFalse("Expected false for a future DOB", result);
    }
}