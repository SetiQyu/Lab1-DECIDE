package app.decide.lic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Lic 5 condition :
 * There exists at least one set of two consecutive data points, (X[i],Y[i]) and (X[j],Y[j]), such
 * that X[j] - X[i] < 0. (where i = j-1)
 */
class Lic5Test {

    /**
     * When num_points is not the same length, inputs are invalid
     */
    @Test void testInvalidLength() {
        assertThrows(IllegalArgumentException.class, () -> new Lic5().condition(new double[2], new double[2], 3, null));
    }

    /**
     * Should be true because X[3] - X{2] = -1 < 0
     */
    @Test void trueCondition() {
        var lic = new Lic5();
        double[] x = {1, 2, 3, 2, 3};
        double[] y = {1, 2, 3, 4, 4};
        boolean condition = lic.condition(x, y, 5, null);
        assertTrue(condition, "Should be true, there are two consecutive data points for which x[i+1] - x[i) < 0 (i = 2)");
    }

    /**
     * Should be false because all x's are in increasing order so no pair has X[i+1] - X[i] < 0
     */
    @Test void falseCondition() {
        var lic = new Lic5();
        double[] x = {1, 2, 3, 4, 5};
        double[] y = {1, 2, 3, 4, 4};
        boolean condition = lic.condition(x, y, 5, null);
        assertFalse(condition, "Should be false, there are no two consecutive points for which x[i+1] - x[i] < 0");
    }

    /**
     * We have X[1] - X[0] = 0 which is not < 0 so should be false
     */
    @Test void limitCondition() {
        var lic = new Lic5();
        double[] x = {1, 1};
        double[] y = {1, 2};
        boolean condition = lic.condition(x, y, 2, null);
        assertFalse(condition, "Should be false, there are two consecutive points for which x[i+1] - x[i] == 0");
    }
}