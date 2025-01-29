package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lic6Test {

    static double[] x;
    static double[] y;
    static ILic lic;
    static Decide.Parameters params;

    @BeforeEach
    void initPoints() {
        x = new double[]{1, 7, -4, 4, 5, -3, 8};
        y = new double[]{2, 5, 5, 6, -2, -5, 1};
        lic = new Lic6();
        params = new Decide().new Parameters(new double[8], new int[11]);
    }

    @Test void testDistance() {
        var distance = Lic6.distance(1, 2, 7, 5, -4, 5);
        assertEquals(4.9193495505, distance, 0.000001);
    }

    /**
     * With less than three points, the condition is not met
     */
    @Test void testLessThanThreePoints() {
        assertFalse(lic.condition(new double[2], new double[2], 2, params));
    }

    /**
     * num_points different for x and y arrays length
     */
    @Test void testInvalidLength() {
        params.N_PTS = 3;
        assertThrows(IllegalArgumentException.class, () -> lic.condition(x, y, 3, params));
    }

    /**
     * Condition if true because point (7, 5) is at a distance 5.66 > 5 of line (1, 2) to (-4, -5)
     */
    @Test void trueCondition3Points() {
        params.N_PTS = 3;
        params.DIST = 5;
        assertTrue(lic.condition(x, y, x.length, params));
    }

    /**
     * Condition is false because no point is at distance greater than 5.7 from his line
     */
    @Test void falseCondition3Points() {
        params.N_PTS = 3;
        params.DIST = 8;
        assertFalse(lic.condition(x, y, x.length, params));
    }

    /**
     * This is true because point (-3, -4) is at distance 12.34 > 12.3 from line (4, 6), (8, 1)
     */
    @Test void trueCondition4Points() {
        params.N_PTS = 4;
        params.DIST = 12.3;
        assertTrue(lic.condition(x, y, x.length, params));
    }

    /**
     * This is false because point (-3, -4) is at distance 12.34 < 12.4 from line (4, 6), (8, 1)
     */
    @Test void falseCondition4Points() {
        params.N_PTS = 4;
        params.DIST = 12.4;
        assertFalse(lic.condition(x, y, x.length, params));
    }
}