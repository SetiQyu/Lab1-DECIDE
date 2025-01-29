package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lic9Test {

    static double[] x;
    static double[] y;
    static Decide.Parameters params;

    @BeforeAll static void initPoints() {
        x = new double[]{5, 8, 7, -3, -3, 3};
        y = new double[]{8, 2, 6, 3, -3, -4};
        params = new Decide().new Parameters(new double[8], new int[11]);
    }

    @Test void testAngle() {
        assertEquals(Math.PI / 2, Lic9.angle(4, 3, 3, 1, 1, 2), 0.000001, "inner right angle");
        assertEquals(Math.PI * 3/2, Lic9.angle(1, 2, 3, 1, 4, 3), 0.000001, "outer right angle");
    }

    @Test void testFalseCondition1() {
        var lic9 = new Lic9();
        params.C_PTS = 1;
        params.D_PTS = 1;
        params.EPSILON = Math.PI * 4/5;

        assertFalse(lic9.condition(x, y, x.length, params));
    }

    @Test void testFalseCondition2() {
        var lic9 = new Lic9();
        params.C_PTS = 2;
        params.D_PTS = 1;
        params.EPSILON = Math.PI * 3/4;

        assertFalse(lic9.condition(x, y, x.length, params));
    }

    @Test void testTrueCondition1() {
        var lic9 = new Lic9();
        params.C_PTS = 1;
        params.D_PTS = 1;
        params.EPSILON = Math.PI / 2;

        assertTrue(lic9.condition(x, y, x.length, params));
    }

    @Test void testTrueCondition2() {
        var lic9 = new Lic9();
        params.C_PTS = 2;
        params.D_PTS = 1;
        params.EPSILON = Math.PI / 2;

        assertTrue(lic9.condition(x, y, x.length, params));
    }
}