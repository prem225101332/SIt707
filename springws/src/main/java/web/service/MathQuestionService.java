package web.service;

public class MathQuestionService {

	public static Double q1Addition(String number1, String number2) {
		if (isInvalid(number1) || isInvalid(number2)) {
			return null;
		}
		try {
			return Double.valueOf(number1) + Double.valueOf(number2);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Double q2Subtraction(String number1, String number2) {
		if (isInvalid(number1) || isInvalid(number2)) {
			return null;
		}
		try {
			return Double.valueOf(number1) - Double.valueOf(number2);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Double q3Multiplication(String number1, String number2) {
		if (isInvalid(number1) || isInvalid(number2)) {
			return null;
		}
		try {
			return Double.valueOf(number1) * Double.valueOf(number2);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private static boolean isInvalid(String value) {
		return value == null || value.trim().isEmpty();
	}
}