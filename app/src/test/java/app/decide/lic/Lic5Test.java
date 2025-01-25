package app.decide.lic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lic5Test {
    @Test void trueCondition() {
        var lic = new Lic5();
        double[] x = {1, 2, 3, 2, 3};
        double[] y = {1, 2, 3, 4, 4};
        boolean condition = lic.condition(x, y, 5, null);
        assertTrue(condition, "Should be true, there are two consecutive data points for which x[i+1] - x[i) < 0 (i = 2)");
    }

    @Test void falseCondition() {
        var lic = new Lic5();
        double[] x = {1, 2, 3, 4, 5};
        double[] y = {1, 2, 3, 4, 4};
        boolean condition = lic.condition(x, y, 5, null);
        assertFalse(condition, "Should be false, there are no two consecutive points for which x[i+1] - x[i] < 0");
    }

    @Test void limitCondition() {
        var lic = new Lic5();
        double[] x = {1, 2, 3, 3, 4};
        double[] y = {1, 2, 3, 4, 4};
        boolean condition = lic.condition(x, y, 5, null);
        assertFalse(condition, "Should be false, there are two consecutive points for which x[i+1] - x[i] == 0");
    }
}