package sit707_week6;

import org.junit.Assert;
import org.junit.Test;

public class WeatherAndMathUtilsTest {

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
	public void testCancelWindSpeedAboveDangerous() {
		Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(70.1, 0.0));
	}

	@Test
	public void testCancelRainfallAboveDangerous() {
		Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(0.0, 6.1));
	}

	@Test
	public void testCancelCombinedConcerning() {
		Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(45.1, 4.1));
	}

	@Test
	public void testWarnWindSpeedAboveConcerning() {
		Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(45.1, 0.0));
	}

	@Test
	public void testWarnRainfallAboveConcerning() {
		Assert.assertEquals("WARN", WeatherAndMathUtils.weatherAdvice(0.0, 4.1));
	}

	@Test
	public void testAllClearNormalConditions() {
		Assert.assertEquals("ALL CLEAR", WeatherAndMathUtils.weatherAdvice(0.0, 0.0));
	}

	@Test
	public void testAllClearExactlyAtThresholds() {
		Assert.assertEquals("ALL CLEAR", WeatherAndMathUtils.weatherAdvice(45.0, 4.0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeWindSpeedThrowsException() {
		WeatherAndMathUtils.weatherAdvice(-1.0, 0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativePrecipitationThrowsException() {
		WeatherAndMathUtils.weatherAdvice(0.0, -1.0);
	}

	@Test
	public void testIsEvenWithEvenNumber() {
		Assert.assertTrue(WeatherAndMathUtils.isEven(4));
	}

	@Test
	public void testIsEvenWithOddNumber() {
		Assert.assertFalse(WeatherAndMathUtils.isEven(3));
	}

	@Test
	public void testIsEvenWithZero() {
		Assert.assertTrue(WeatherAndMathUtils.isEven(0));
	}

	@Test
	public void testIsEvenWithNegativeEven() {
		Assert.assertTrue(WeatherAndMathUtils.isEven(-2));
	}

	@Test
	public void testIsPrimeOne() {
		Assert.assertFalse(WeatherAndMathUtils.isPrime(1));
	}

	@Test
	public void testIsPrimeEvenNumber() {
		Assert.assertFalse(WeatherAndMathUtils.isPrime(4));
	}

	@Test
	public void testIsPrimeOddPrime() {
		Assert.assertTrue(WeatherAndMathUtils.isPrime(7));
	}

	@Test
	public void testIsPrimeOddNonPrime() {
		Assert.assertFalse(WeatherAndMathUtils.isPrime(9));
	}

	@Test
	public void testIsPrimeTwo() {
		Assert.assertTrue(WeatherAndMathUtils.isPrime(2));
	}

}
