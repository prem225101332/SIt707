package sit707_week5;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class WeatherControllerTest {

	private static WeatherController wController;
	private static double[] hourlyTemperatures;
	private static int nHours;

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("+++ @BeforeClass: Initialising WeatherController +++");

		wController = WeatherController.getInstance();
		nHours = wController.getTotalHours();
		hourlyTemperatures = new double[nHours];
		for (int i = 0; i < nHours; i++) {
			hourlyTemperatures[i] = wController.getTemperatureForHour(i + 1);
		}

		System.out.println("Cached temperatures for " + nHours + " hours.");
	}

	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("+++ @AfterClass: Closing WeatherController +++");
		if (wController != null) {
			wController.close();
		}
	}

	@Test
	public void testStudentIdentity() {
		String studentId = "s225101332";

		Assert.assertNotNull("Student ID is null", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "prem Kumar Lingutla";

		Assert.assertNotNull("Student name is null", studentName);
	}

	@Test
	public void testTemperatureMin() {
		System.out.println("+++ testTemperatureMin +++");

		double expectedMin = Double.MAX_VALUE;
		for (double temp : hourlyTemperatures) {
			if (temp < expectedMin) {
				expectedMin = temp;
			}
		}
		double actualMin = wController.getTemperatureMinFromCache();
		Assert.assertEquals(
				"Cached min temperature does not match computed min",
				expectedMin,
				actualMin,
				0.0
		);
	}

	@Test
	public void testTemperatureMax() {
		System.out.println("+++ testTemperatureMax +++");
		double expectedMax = Double.MIN_VALUE;
		for (double temp : hourlyTemperatures) {
			if (temp > expectedMax) {
				expectedMax = temp;
			}
		}
		double actualMax = wController.getTemperatureMaxFromCache();
		Assert.assertEquals(
				"Cached max temperature does not match computed max",
				expectedMax,
				actualMax,
				0.0
		);
	}

	@Test
	public void testTemperatureAverage() {
		System.out.println("+++ testTemperatureAverage +++");
		double sum = 0;
		for (double temp : hourlyTemperatures) {
			sum += temp;
		}
		double expectedAverage = sum / nHours;
		double actualAverage = wController.getTemperatureAverageFromCache();
		Assert.assertEquals(
				"Cached average temperature does not match computed average",
				expectedAverage,
				actualAverage,
				0.0001
		);
	}
	@Test
	public void testTemperaturePersist() {
	}
}