package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class Lic7Test {
    static double[] x;
    static double[] y;
    static Decide.Parameters params;

    @BeforeAll
    static void initPoints() {
        x = new double[]{2, 7, 3, -7, -1};
        y = new double[]{1, 2, 3, 100, 5};
        params = new Decide().new Parameters(new double[8], new int[11]);
    }

    @Test
    void trueCondition() {
        var lic7 = new Lic7();
        params.LENGTH1 = 50;
        params.K_PTS = 2;
        assertTrue(lic7.condition(x, y, x.length, params));
    }

    @Test void falseCondition() {
        var lic7 = new Lic7();
        params.LENGTH1 = 50;
        params.K_PTS = 3;
        assertFalse(lic7.condition(x, y, x.length, params));
    }
}