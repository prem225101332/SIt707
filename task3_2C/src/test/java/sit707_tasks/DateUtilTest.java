package sit707_tasks;

import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class DateUtilTest {

	@Test
	public void testStudentIdentity() {
		String studentId = "s225101332";
		Assert.assertNotNull("Student ID is null", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "Prem Kumar";
		Assert.assertNotNull("Student name is null", studentName);
	}

	@Test
	public void testMaxJanuary31ShouldIncrementToFebruary1() {
		DateUtil date = new DateUtil(31, 1, 2024);
		System.out.println("january31ShouldIncrementToFebruary1 > " + date);
		date.increment();
		System.out.println(date);
		Assert.assertEquals(2, date.getMonth());
		Assert.assertEquals(1, date.getDay());
	}

	@Test
	public void testMaxJanuary31ShouldDecrementToJanuary30() {
		DateUtil date = new DateUtil(31, 1, 2024);
		System.out.println("january31ShouldDecrementToJanuary30 > " + date);
		date.decrement();
		System.out.println(date);
		Assert.assertEquals(30, date.getDay());
		Assert.assertEquals(1, date.getMonth());
	}

	@Test
	public void testNominalJanuary() {
		DateUtil date = new DateUtil(15, 1, 2024);
		System.out.println("testNominalJanuary > " + date);
		date.increment();
		System.out.println(date);
		Assert.assertEquals(16, date.getDay());
		Assert.assertEquals(1, date.getMonth());
		Assert.assertEquals(2024, date.getYear());
	}

	@Test
	public void testMinJanuary1ShouldIncrementToJanuary2() {
		DateUtil date = new DateUtil(1, 1, 2024);
		System.out.println("Before increment: " + date);
		date.increment();
		System.out.println("After increment: " + date);
		Assert.assertEquals(2, date.getDay());
		Assert.assertEquals(1, date.getMonth());
		Assert.assertEquals(2024, date.getYear());
	}

	@Test
	public void testMinJanuary1ShouldDecrementToDecember31() {
		DateUtil date = new DateUtil(1, 1, 2024);
		System.out.println("Before decrement: " + date);
		date.decrement();
		System.out.println("After decrement: " + date);
		Assert.assertEquals(31, date.getDay());
		Assert.assertEquals(12, date.getMonth());
		Assert.assertEquals(2023, date.getYear());
	}

	@Test
	public void testIncrement_D1_M31_NLY() {
		DateUtil date = new DateUtil(15, 1, 2023);
		date.increment();
		Assert.assertEquals(16, date.getDay());
		Assert.assertEquals(1, date.getMonth());
		Assert.assertEquals(2023, date.getYear());
	}

	@Test
	public void testIncrement_D1_M30_NLY() {
		DateUtil date = new DateUtil(15, 4, 2023);
		date.increment();
		Assert.assertEquals(16, date.getDay());
		Assert.assertEquals(4, date.getMonth());
		Assert.assertEquals(2023, date.getYear());
	}

	@Test
	public void testIncrement_D1_MF_NLY() {
		DateUtil date = new DateUtil(15, 2, 2023);
		date.increment();
		Assert.assertEquals(16, date.getDay());
		Assert.assertEquals(2, date.getMonth());
		Assert.assertEquals(2023, date.getYear());
	}

	@Test
	public void testIncrement_D2_M31_NLY() {
		DateUtil date = new DateUtil(29, 1, 2023);
		date.increment();
		Assert.assertEquals(30, date.getDay());
		Assert.assertEquals(1, date.getMonth());
		Assert.assertEquals(2023, date.getYear());
	}

	@Test
	public void testIncrement_D2_MF_LY() {
		DateUtil date = new DateUtil(29, 2, 2024);
		date.increment();
		Assert.assertEquals(1, date.getDay());
		Assert.assertEquals(3, date.getMonth());
		Assert.assertEquals(2024, date.getYear());
	}

	@Test
	public void testIncrement_D3_M30() {
		DateUtil date = new DateUtil(30, 4, 2024);
		date.increment();
		Assert.assertEquals(1, date.getDay());
		Assert.assertEquals(5, date.getMonth());
		Assert.assertEquals(2024, date.getYear());
	}

	@Test
	public void testIncrement_D3_M31() {
		DateUtil date = new DateUtil(30, 1, 2024);
		date.increment();
		Assert.assertEquals(31, date.getDay());
		Assert.assertEquals(1, date.getMonth());
		Assert.assertEquals(2024, date.getYear());
	}

	@Test
	public void testIncrement_D4_M31() {
		DateUtil date = new DateUtil(31, 1, 2024);
		date.increment();
		Assert.assertEquals(1, date.getDay());
		Assert.assertEquals(2, date.getMonth());
		Assert.assertEquals(2024, date.getYear());
	}

	@Test
	public void testIncrement_EndOfYear() {
		DateUtil date = new DateUtil(31, 12, 2023);
		date.increment();
		Assert.assertEquals(1, date.getDay());
		Assert.assertEquals(1, date.getMonth());
		Assert.assertEquals(2024, date.getYear());
	}

	@Test
	public void testDecrement_D2_M31_NLY() {
		DateUtil date = new DateUtil(29, 1, 2023);
		date.decrement();
		Assert.assertEquals(28, date.getDay());
		Assert.assertEquals(1, date.getMonth());
		Assert.assertEquals(2023, date.getYear());
	}

	@Test
	public void testDecrement_StartOfYear() {
		DateUtil date = new DateUtil(1, 1, 2024);
		date.decrement();
		Assert.assertEquals(31, date.getDay());
		Assert.assertEquals(12, date.getMonth());
		Assert.assertEquals(2023, date.getYear());
	}

	@Test
	public void testDecrement_D2_MF_LY() {
		DateUtil date = new DateUtil(29, 2, 2024);
		date.decrement();
		Assert.assertEquals(28, date.getDay());
		Assert.assertEquals(2, date.getMonth());
		Assert.assertEquals(2024, date.getYear());
	}

	@Test
	public void testDecrement_FirstDayOfMarch_LeapYear() {
		DateUtil date = new DateUtil(1, 3, 2024);
		date.decrement();
		Assert.assertEquals(29, date.getDay());
		Assert.assertEquals(2, date.getMonth());
		Assert.assertEquals(2024, date.getYear());
	}

	@Test
	public void testDecrement_FirstDayOfMarch_NonLeapYear() {
		DateUtil date = new DateUtil(1, 3, 2023);
		date.decrement();
		Assert.assertEquals(28, date.getDay());
		Assert.assertEquals(2, date.getMonth());
		Assert.assertEquals(2023, date.getYear());
	}

	@Test
	public void testDecrement_FirstDayOfMay() {
		DateUtil date = new DateUtil(1, 5, 2024);
		date.decrement();
		Assert.assertEquals(30, date.getDay());
		Assert.assertEquals(4, date.getMonth());
		Assert.assertEquals(2024, date.getYear());
	}
}