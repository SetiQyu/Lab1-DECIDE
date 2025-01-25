package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lic6Test {

    static double[] x;
    static double[] y;
    static ILic lic;
    static Decide.Parameters params;

    @BeforeAll static void initPoints() {
        x = new double[]{1, 7, -4, 4, 5, -3, 8};
        y = new double[]{2, 5, 5, 6, -2, -5, 1};
        lic = new Lic6();
        params = new Decide().new Parameters(new double[8], new int[11]);
    }

    @Test void testDistance() {
        var distance = Lic6.distance(1, 2, 7, 5, -4, 5);
        assertEquals(4.9193495505, distance, 0.000001);
    }

    @Test void testLessThanThreePoints() {
        assertFalse(lic.condition(x, y, 2, params));
    }

    @Test void trueCondition3Points() {
        params.N_PTS = 3;
        params.DIST = 5;
        assertTrue(lic.condition(x, y, x.length, params));
    }

    @Test void falseCondition3Points() {
        params.N_PTS = 3;
        params.DIST = 8;
        assertFalse(lic.condition(x, y, x.length, params));
    }

    @Test void trueCondition4Points() {
        params.N_PTS = 4;
        params.DIST = 12.3;
        assertTrue(lic.condition(x, y, x.length, params));
    }

    @Test void falseCondition4Points() {
        params.N_PTS = 4;
        params.DIST = 12.4;
        assertFalse(lic.condition(x, y, x.length, params));
    }
}