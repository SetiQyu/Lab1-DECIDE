package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import app.decide.Decide.Parameters;

/**
* There exists at least one set of three data points, separated by exactly E PTS and F PTS consecutive intervening points, respectively, that are the vertices of a triangle with area greater
* than AREA1. In addition, there exist three data points (which can be the same or different
* from the three data points just mentioned) separated by exactly E PTS and F PTS consecutive intervening points, respectively, that are the vertices of a triangle with area less than
* AREA2. Both parts must be true for the LIC to be true. The condition is not met when
* NUMPOINTS < 5.
* 0 ≤ AREA2
*/
public class Lic14Test {
    private Lic14 test_Lic14;
    private Decide decide;

    @BeforeEach
    public void setUp() {
        test_Lic14 = new Lic14();
        decide = new Decide();
    }

    /**
     * <b>Contract:</b>> The checkArea function calculates the correct area and does the correct comparison <p>
     * <b>Input: </b> ax = 1.0 ay = 1.0, bx = 3.0, by = 3.0, cx = 5.0, cy = 5.0, area = -1, greater_than = true <p>
     * <b>Expected output: </b> true <p>
     * <b>Test Purpose:</b> Verify that the area of 3 collinear points are 0.0 which should be greater than -1.
     */
    @Test 
    public void testCheckArea(){
        // Test data points on line y = x
        double ax = 1.0; 
        double ay = 1.0;
        double bx = 3.0;
        double by = 3.0;
        double cx = 5.0;
        double cy = 5.0;
        assertTrue(Lic14.checkArea(ax, ay, bx, by, cx, cy, -1, true));
    }

    /**
     * <b>Contract:</b>> The condition functions finds a triangle that upholds the conditions of Lic14 <p>
     * <b>Input: </b> AREA1 = 4.0 AREA2 = 6.0, E_PTS = 2, F_PTS = 2, x = (1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 2.0), y = (1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0) <p>
     * <b>Expected output: </b> true <p>
     * <b>Test Purpose:</b> Verify that the condition function finds the triangle formed by the points (1.0, 1.0), (4.0, 1.0) and (2.0, 4.0) 
     * that has an area of 4.5 which upholds conditions 4.5 > 4 and 4.5 < 6. 
     */
    @Test
    public void findsCorrectTriangle(){
        double[] p1 = {0.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 6.0}; // Area1 set to 4 and Area2 set to 6
        int[] p2 = {0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0}; // E_PTS and F_PTS set to 2.
        Parameters parameters = decide.new Parameters(p1, p2);
        // The points with the correct spacing results in a triangle with area 4.5 that is within the specified range AREA1 < 4.5 < AREA2 
        double[] x = {1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 2.0};
        double[] y = {1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0};
        assertTrue(test_Lic14.condition(x, y, x.length, parameters));
    }

    /**
     * <b>Contract:</b>> The condition functions finds a triangle that upholds the conditions of Lic14 <p>
     * <b>Input: </b> AREA1 = 5.0 AREA2 = 5.0, E_PTS = 2, F_PTS = 2, x = (1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 2.0), y = (1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0) <p>
     * <b>Expected output: </b> true <p>
     * <b>Test Purpose:</b> Verify that the condition function finds two different triangles one formed by the points (1.0, 1.0), (4.0, 1.0) and (2.0, 4.0) 
     * that has an area of 4.5 which upholds conditions 4.5 < 5. The other triangle formed by the points (4.0, 1.0), (2.0, 4.0), (6.0, 4.0) have an area of 6.0
     * which is greater than 5.    
     */
    @Test
    public void findsTwoDifferentTriangles(){
        double[] p1 = {0.0, 0.0, 0.0, 5.0, 0.0, 0.0, 0.0, 5.0}; // AREA1 set to 5 and AREA2 set to 5
        int[] p2 = {0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0}; // E_PTS and F_PTS set to 2.
        Parameters parameters = decide.new Parameters(p1, p2);  
        double[] x = {1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 2.0, 0.0, 0.0, 6.0};
        double[] y = {1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 4.0};
        assertTrue(test_Lic14.condition(x, y, x.length, parameters));
    }

    /**
     * <b>Contract:</b> The condition function throws an exception when the lengths of the x and y points do not match. <p>
     * <b>Input: </b> AREA1 = 5.0, AREA2 = 5.0, E_PTS = 2, F_PTS = 2 x = (1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0), y = (1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0) <p>
     * <b>Expected output: IllegalArgumentException </b> <p>
     * <b>Test Purpose:</b> Verify that the condition function throws an exception when length of array x is 8 and the length of array y is 7.
     */
    @Test
    public void testInvalidInput(){
        double[] p1 = {0.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 6.0};
        int[] p2 = {0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0};
        Parameters parameters = decide.new Parameters(p1, p2);
        double[] x = {1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0}; // x is shorter than y.
        double[] y = {1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0};
        
        assertThrows(IllegalArgumentException.class, () -> test_Lic14.condition(x, y, x.length, parameters));
       
    }
}
