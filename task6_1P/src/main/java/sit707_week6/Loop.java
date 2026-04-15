package sit707_week6;

public class Loop {

    /**
     * (a) Conditional loop with simple statements.
     * Multiplies all integers from 1 to n together (factorial).
     * Returns 1 for n <= 0.
     */
    public static int factorial(int n) {
        int result = 1;
        int i = 1;
        while (i <= n) {        // conditional loop
            result = result * i; // simple statement
            i++;
        }
        return result;
    }

    /**
     * (b) Conditional loop containing a conditional statement.
     * Returns the count of negative numbers in the array.
     */
    public static int countNegatives(int[] numbers) {
        int count = 0;
        int i = 0;
        while (i < numbers.length) {        // conditional loop
            if (numbers[i] < 0) {           // conditional statement inside loop
                count++;
            }
            i++;
        }
        return count;
    }
}