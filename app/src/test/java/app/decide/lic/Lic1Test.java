package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lic1Test {
    /**
     * This should be true because the smallest circle enclosing the 3 points is of radius sqrt(2)/2 > sqrt(2)/2+0.01
     */
    @Test void testTrueCondition() {
        Lic1 lic1 = new Lic1();
        double[] x = {0, 0, 1};
        double[] y = {0, 1, 0};
        Decide.Parameters parameters = new Decide().new Parameters(new double[8], new int[11]);
        parameters.RADIUS1 = Math.sqrt(2)/2 - 0.01;
        assertTrue(lic1.condition(x, y, x.length, parameters));
    }

    /**
     * With less than 3 points, the condition should not be met
     */
    @Test void testLessThan3Points() {
        Lic1 lic1 = new Lic1();
        double[] x = {0, 0};
        double[] y = {0, 1};
        Decide.Parameters parameters = new Decide().new Parameters(new double[8], new int[11]);
        parameters.RADIUS1 = 2;
        assertFalse(lic1.condition(x, y, x.length, parameters));
    }

    /**
     * This false be true because the smallest circle enclosing the 3 points is of radius sqrt(2)/2 < sqrt(2)/2+0.01
     */
    @Test void testFalseCondition() {
        Lic1 lic1 = new Lic1();
        double[] x = {0, 0, 1};
        double[] y = {0, 1, 0};
        Decide.Parameters parameters = new Decide().new Parameters(new double[8], new int[11]);
        parameters.RADIUS1 = Math.sqrt(2)/2 + 0.01;
        assertFalse(lic1.condition(x, y, x.length, parameters));
    }

}