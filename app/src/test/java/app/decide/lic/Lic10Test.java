package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import app.decide.Decide.Parameters;;

/**
 * Lic 10 condition: 
 * There exists at least one set of three data points separated by exactly E PTS and F PTS consecutive intervening points, respectively, that are the vertices of a triangle with area greater 
 * than AREA1. The condition is not met when NUMPOINTS < 5.
 * 1 ≤ E PTS, 1 ≤ F PTS 
 * E PTS+F PTS ≤ NUMPOINTS−3
 */
public class Lic10Test {

    private Lic10 lic10;
    private Decide decide;
    private Decide.Parameters parameters;

    @BeforeEach 
    public void setUp() {
        lic10 = new Lic10();
        decide = new Decide();
        parameters = new Decide().new Parameters(new double[8], new int[11]);
    }

    /**
    * Verify that calcArea correctly returns an area of 0.0 when the given points are collinear. 
    */ 
    @Test void testTriangleCalculation(){
        // Test data points on line y = x
        double ax = 1.0; 
        double ay = 1.0;
        double bx = 3.0; 
        double by = 3.0;
        double cx = 5.0; 
        double cy = 5.0;
        double delta = 0.000001;
        // Result
        double expected = 0.0;
        double result = lic10.calcArea(ax, ay, bx, by, cx, cy);
        assertEquals(expected, lic10.calcArea(ax, ay, bx, by, cx, cy), delta, "Collinear points should return an area of 0.");

    }

    /**
     * Verify that the condition function finds the triangle (1.0, 1.0)-(4.0, 1.0)-(2.0, 4.0) that has an area of 4.5 which is greater than AREA1.
     */
    @Test void findsCorrectTriangle(){
        parameters.AREA1 = 4.0;
        parameters.E_PTS = 2;
        parameters.F_PTS = 2;
        double[] x = {1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 2.0};
        double[] y = {1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0};
        // Result 
        boolean condition = lic10.condition(x, y, x.length, parameters);
        assertTrue(condition, "There exists a triangle had upholds conditions of Lic10");

    }

    /**
     * Verify that the condition function throws an exception when length of array x is 8 and the length of array y is 7.
     */
    @Test void testInvalidInput(){
        parameters.AREA1 = 4.0;
        parameters.E_PTS = 2;
        parameters.F_PTS = 2;
        double[] x = {1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0}; // x is shorter than y.
        double[] y = {1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0};
        // Result
        assertThrows(IllegalArgumentException.class, () -> lic10.condition(x, y, x.length, parameters));
        
    }
}
