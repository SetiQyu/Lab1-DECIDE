package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lic8Test {
    static double[] x;
    static double[] y;
    static Decide.Parameters params;

    @BeforeAll static void initPoints() {
        x = new double[]{8, 4, 6, 12, 0, -2};
        y = new double[]{4, 8, -4, 2, 4, -4};
        params = new Decide().new Parameters(new double[8], new int[11]);
    }

    @Test void testRadius() {
        assertEquals(7.740630166330, Lic8.radius(4, 8, 12, 2, -2, -4), 0.000001);
    }

    @Test void testTrueCondition1() {
        var lic8 = new Lic8();
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.RADIUS1 = 7.7;

        assertTrue(lic8.condition(x, y, x.length, params));
    }

    @Test void testTrueCondition2() {
        var lic8 = new Lic8();
        params.A_PTS = 2;
        params.B_PTS = 1;
        params.RADIUS1 = 8.3;

        assertTrue(lic8.condition(x, y, x.length, params));
    }

    @Test void testFalseCondition1() {
        var lic8 = new Lic8();
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.RADIUS1 = 7.8;

        assertFalse(lic8.condition(x, y, x.length, params));
    }

    @Test void testFalseCondition2() {
        var lic8 = new Lic8();
        params.A_PTS = 2;
        params.B_PTS = 1;
        params.RADIUS1 = 8.4;

        assertFalse(lic8.condition(x, y, x.length, params));
    }
}