package sit707_week6;

import org.junit.Assert;
import org.junit.Test;

public class LoopTest {
    @Test
    public void testFactorialZero() {
        Assert.assertEquals(1, Loop.factorial(0));
    }

    @Test
    public void testFactorialOne() {
        Assert.assertEquals(1, Loop.factorial(1));
    }

    @Test
    public void testFactorialFive() {
        Assert.assertEquals(120, Loop.factorial(5));
    }

    @Test
    public void testCountNegativesEmptyArray() {
        Assert.assertEquals(0, Loop.countNegatives(new int[]{}));
    }

    @Test
    public void testCountNegativesAllPositive() {
        Assert.assertEquals(0, Loop.countNegatives(new int[]{1, 2, 3}));
    }

    @Test
    public void testCountNegativesMixed() {
        Assert.assertEquals(2, Loop.countNegatives(new int[]{-1, 2, -3, 4}));
    }

    @Test
    public void testCountNegativesAllNegative() {
        Assert.assertEquals(3, Loop.countNegatives(new int[]{-5, -2, -8}));
    }
}