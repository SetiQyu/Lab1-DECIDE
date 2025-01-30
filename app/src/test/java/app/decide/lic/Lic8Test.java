package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Lic 8 condition :
 * There exists at least one set of three data points separated by exactly A PTS and B PTS
 * consecutive intervening points, respectively, that cannot be contained within or on a circle of
 * radius RADIUS1. The condition is not met when NUMPOINTS < 5.
 * 1 ≤ A PTS, 1 ≤ B PTS
 * A PTS + B PTS ≤ (NUMPOINTS − 3)
 */
class Lic8Test {
    double[] x;
    double[] y;
    Decide.Parameters params;

    @BeforeEach
    void initPoints() {
        params = new Decide().new Parameters(new double[8], new int[11]);
    }

    @Test void testCircumRadius() {
        assertEquals(7.740630166330, Lic8.circumRadius(4, 8, 12, 2, -2, -4), 0.000001);
    }

    /**
     * When num_points is not the same length, inputs are invalid
     */
    @Test void testInvalidLength() {
        params.A_PTS = 1;
        params.B_PTS= 1;
        assertThrows(IllegalArgumentException.class, () -> new Lic8().condition(new double[2], new double[2], 5, params));
    }

    /**
     * There are two points of three points to check : (5, 5), (3, 2), (2, 4) and (3, 3), (6, 1), (2, 9)
     * the first one can be contained in radius 2, not the second one.
     */
    @Test void testTrueCondition() {
        var lic8 = new Lic8();
        x = new double[] {5, 3, 3, 6, 2, 2};
        y = new double[] {5, 3, 2, 1, 4, 9};
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.RADIUS1 = 2;

        assertTrue(lic8.condition(x, y, x.length, params));
    }

    /**
     * When the three points are aligned, check that no error occurs
     */
    @Test void testAlignedPoints() {
        var lic8 = new Lic8();
        x = new double[] {0, 4, 1, -3, 2};
        y = new double[] {0, 8, 1, 2, 2};
        params.RADIUS1 = 2;
        params.A_PTS = 1;
        params.B_PTS = 1;

        assertFalse(lic8.condition(x, y, x.length, params));
    }

    /**
     * When the three points are equal, check that no error occurs
     */
    @Test void testPointsEqual() {
        var lic8 = new Lic8();
        x = new double[] {1, 4, 1, -3, 1};
        y = new double[] {1, 8, 1, 2, 1};
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.RADIUS1 = 0;
        assertFalse(lic8.condition(x, y, x.length, params));
    }

    /**
     * Test with different values of {@code A_PTS} and {@code B_PTS}.
     * We check that we compare the good points by setting them equal and having the {@code RADIUS1} set to 0.
     */
    @Test void testDifferentAAndBValues() {
        var lic8 = new Lic8();
        x = new double[] {1, 4, 1, -3, 4, 1};
        y = new double[] {1, 8, 1, 2, -2, 1};
        params.A_PTS = 1;
        params.B_PTS = 2;
        params.RADIUS1 = 0;
        assertFalse(lic8.condition(x, y, x.length, params));
    }

    /**
     * We check that when the triangle is obtuse, the radius is computed as half the max distance.
     * The triangle (3, 3), (6, 1), (2, 9) is obtuse and cannot be contained in a circle of radius of 3.20
     */
    @Test void testObtuseTriangleTrueCondition() {
        var lic8 = new Lic8();
        x = new double[] {3, 4, 6, -3, 2};
        y = new double[] {3, 8, 1, 2, 6};
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.RADIUS1 = 3.20;
        assertTrue(lic8.condition(x, y, x.length, params));
    }

    /**
     * We check that when the triangle is obtuse, the radius is computed as half the max distance.
     * The triangle (3, 3), (6, 1), (2, 9) is obtuse and can be contained in a circle of radius of 3.21
     */
    @Test void testObtuseTriangleFalseCondition() {
        var lic8 = new Lic8();
        x = new double[] {3, 4, 6, -3, 2};
        y = new double[] {3, 8, 1, 2, 6};
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.RADIUS1 = 3.21;
        assertFalse(lic8.condition(x, y, x.length, params));
    }

    /**
     * We check that when the triangle is not obtuse, the radius is computed as the circumradius of the triangle.
     * The triangle (5, 5), (3, 2), (2, 4) is not obtuse and cannot be contained in a circle of radius of 1.82
     */
    @Test void testNonObtuseTriangleTrueCondition() {
        var lic8 = new Lic8();
        x = new double[] {5, 4, 3, -3, 2};
        y = new double[] {5, 8, 2, 2, 4};
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.RADIUS1 = 1.82;
        assertTrue(lic8.condition(x, y, x.length, params));
    }

    /**
     * We check that when the triangle is not obtuse, the radius is computed as the circumradius of the triangle.
     * The triangle (5, 5), (3, 2), (2, 4) is not obtuse and can be contained in a circle of radius of 1.83
     */
    @Test void testNonObtuseTriangleFalseCondition() {
        var lic8 = new Lic8();
        x = new double[] {5, 4, 3, -3, 2};
        y = new double[] {5, 8, 2, 2, 4};
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.RADIUS1 = 1.83;
        assertFalse(lic8.condition(x, y, x.length, params));
    }
}