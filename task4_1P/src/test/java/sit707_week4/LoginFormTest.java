package sit707_week4;

import org.junit.Assert;
import org.junit.Test;

public class LoginFormTest 
{

	@Test
	public void testStudentIdentity() {
		String studentId = "s225101332";
		Assert.assertNotNull("Student ID is null", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "Prem Kumar Lingutla";
		Assert.assertNotNull("Student name is null", studentName);
	}

	@Test
	public void testFailEmptyUsernameAndEmptyPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login(null, null);
		Assert.assertTrue(status.isLoginSuccess() == false);
	}

	@Test
	public void testFailEmptyUsernameAndWrongPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login(null, "wrong_pass");
		Assert.assertTrue(status.isLoginSuccess() == false);
	}

	@Test
	public void testFailEmptyUsernameAndCorrectPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login(null, "ahsan_pass");
		Assert.assertTrue(status.isLoginSuccess() == false);
	}

	@Test
	public void testFailWrongUsernameAndEmptyPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login("wrong_user", null);
		Assert.assertTrue(status.isLoginSuccess() == false);
	}

	@Test
	public void testFailWrongUsernameAndWrongPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login("wrong_user", "wrong_pass");
		Assert.assertTrue(status.isLoginSuccess() == false);
	}

	@Test
	public void testFailWrongUsernameAndCorrectPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login("wrong_user", "premkumar123");
		Assert.assertTrue(status.isLoginSuccess() == false);
	}

	@Test
	public void testFailCorrectUsernameAndEmptyPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login("Prem Kumar", null);
		Assert.assertTrue(status.isLoginSuccess() == false);
	}

	@Test
	public void testFailCorrectUsernameAndWrongPasswordAndDontCareValCode() {
		LoginStatus status = LoginForm.login("Prem Kumar", "wrong_pass");
		Assert.assertTrue(status.isLoginSuccess() == false);
	}

	@Test
	public void testSuccessCorrectUsernameAndCorrectPasswordAndEmptyValCode() {
		LoginStatus status = LoginForm.login("Prem Kumar", "premkumar123");
		Assert.assertTrue(status.isLoginSuccess() == true);
		Assert.assertTrue(LoginForm.validateCode(null) == false);
	}

	@Test
	public void testSuccessCorrectUsernameAndCorrectPasswordAndWrongValCode() {
		LoginStatus status = LoginForm.login("Prem Kumar", "premkumar123");
		Assert.assertTrue(status.isLoginSuccess() == true);
		Assert.assertTrue(LoginForm.validateCode("wrong_code") == false);
	}

	@Test
	public void testSuccessCorrectUsernameAndCorrectPasswordAndCorrectValCode() {
		LoginStatus status = LoginForm.login("Prem Kumar", "premkumar123");
		Assert.assertTrue(status.isLoginSuccess() == true);
		String validationCode = status.getErrorMsg();
		Assert.assertTrue(LoginForm.validateCode(validationCode) == true);
	}

}
