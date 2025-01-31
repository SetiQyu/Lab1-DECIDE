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

    private Lic10 test_Lic10;
    private Decide decide;

    @BeforeEach 
    public void setUp() {
        test_Lic10 = new Lic10();
        decide = new Decide();
    }


    /**
     * <b>Contract:</b> The calcArea function calculates the correct area of a triangle given three 2D points. <p>
     * <b>Input:</b> ax = 1.0 ay = 1.0, bx = 3.0, by = 3.0, cx = 5.0, cy = 5.0. <p>
     * <b>Expected output:</b>  0.0 <p>
     * <b>Test Purpose:</b> Verify that calcArea correctly returns an area of 0.0 when the given points are collinear. 
    */ 
    @Test
    public void testTriangleCalculation(){
        // Test data points on line y = x
        double ax = 1.0; 
        double ay = 1.0;
        double bx = 3.0; 
        double by = 3.0;
        double cx = 5.0; 
        double cy = 5.0;
        double delta = 0.000001;
        // The area should be 0 as the points are collinear.
        assertEquals(0.0, test_Lic10.calcArea(ax, ay, bx, by, cx, cy), delta);

    }

    /**
     * <b>Contract:</b> The condition function finds points that uphold conditions of Lic10. <p>
     * <b>Input: </b> AREA1 = 4.0, E_PTS = 2, F_PTS = 2, x = (1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 2.0), y = (1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0) <p>
     * <b>Expected output:</b> true <p>
     * <b>Test Purpose:</b> Verify that the condition function finds the triangle (1.0, 1.0)-(4.0, 1.0)-(2.0, 4.0) that has an area of 4.5 which is greater than AREA1.
     */
    @Test
    public void findsCorrectTriangle(){
        double[] p1 = {0.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0};
        int[] p2 = {0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0};
        Parameters parameters = decide.new Parameters(p1, p2);
        // The points with the correct spacing results in a triangle with a larger area than the one specififed in p1. 
        double[] x = {1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 2.0};
        double[] y = {1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0};

        assertTrue(test_Lic10.condition(x, y, x.length, parameters));

    }

    /**
     * <b>Contract:</b> The condition function throws an exception when the lengths of the x and y points do not match. <p>
     * <b>Input: </b> AREA1 = 4.0, E_PTS = 2, F_PTS = 2, x = (1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0), y = (1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0) <p>
     * <b>Expected output: IllegalArgumentException </b> <p>
     * <b>Test Purpose:</b> Verify that the condition function throws an exception when length of array x is 8 and the length of array y is 7.
     */
    @Test
    public void testInvalidInput(){
        // Local variables
        double[] p1 = {0.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0};
        int[] p2 = {0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0};
        Parameters parameters = decide.new Parameters(p1, p2);
        double[] x = {1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0}; // x is shorter than y.
        double[] y = {1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0};

        assertThrows(IllegalArgumentException.class, () -> test_Lic10.condition(x, y, x.length, parameters));
        
    }
}
