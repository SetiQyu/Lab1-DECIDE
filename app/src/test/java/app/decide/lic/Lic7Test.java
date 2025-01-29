package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class Lic7Test {
    static double[] x;
    static double[] y;
    static Decide.Parameters params;

    @BeforeEach
    void initPoints() {
        x = new double[]{2, 7, 3, -7, -1};
        y = new double[]{1, 2, 3, 100, 5};
        params = new Decide().new Parameters(new double[8], new int[11]);
    }

    /**
     * The condition is not met with less than three points
     */
    @Test void testLessThanThreePoints() {
        var lic7 = new Lic7();
        params.LENGTH1 = 50;
        params.K_PTS = 1;
        assertFalse(lic7.condition(new double[2], new double[2], 2, params));
    }

    /**
     * When num_points is not the same length, inputs are invalid
     */
    @Test void testInvalidLength() {
        params.LENGTH1 = 0;
        params.K_PTS = 1;
        assertThrows(IllegalArgumentException.class, () -> new Lic7().condition(new double[2], new double[2], 3, params));
    }

    /**
     * This is true because points (2, 1) and (-7, 100) are separated by 2 points and are at a distance greater than 50
     */
    @Test
    void trueCondition() {
        var lic7 = new Lic7();
        params.LENGTH1 = 50;
        params.K_PTS = 2;
        assertTrue(lic7.condition(x, y, x.length, params));
    }

    /**
     * This is false because points (2, 1) and (-1, 5) is the only pair separated by three points and their distance is
     * smaller than 50
     */
    @Test void falseCondition() {
        var lic7 = new Lic7();
        params.LENGTH1 = 50;
        params.K_PTS = 3;
        assertFalse(lic7.condition(x, y, x.length, params));
    }
}