package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import app.decide.Decide.Parameters;

/**
* Lic 14 condition:  
* There exists at least one set of three data points, separated by exactly E PTS and F PTS consecutive intervening points, respectively, that are the vertices of a triangle with area greater
* than AREA1. In addition, there exist three data points (which can be the same or different
* from the three data points just mentioned) separated by exactly E PTS and F PTS consecutive intervening points, respectively, that are the vertices of a triangle with area less than
* AREA2. Both parts must be true for the LIC to be true. The condition is not met when
* NUMPOINTS < 5.
* 0 ≤ AREA2
*/
public class Lic14Test {
    private Lic14 lic14;
    private Decide decide;
    private Decide.Parameters parameters;
    @BeforeEach
    public void setUp() {
        lic14 = new Lic14();
        decide = new Decide();
        parameters = new Decide().new Parameters(new double[8], new int[11]);
    }

    /**
     * Verify that the area of 3 collinear points are 0.0 which should be greater than -1.
     */
    @Test void testCheckArea(){
        // Test data points on line y = x
        double ax = 1.0; 
        double ay = 1.0;
        double bx = 3.0;
        double by = 3.0;
        double cx = 5.0;
        double cy = 5.0;
        // Result
        boolean result = lic14.checkArea(ax, ay, bx, by, cx, cy, -1, true);
        assertTrue(result, "The area formed by the points should be greater than -1.");
    }

    /**
     * Verify that the condition function finds the triangle formed by the points (1.0, 1.0), (4.0, 1.0) and (2.0, 4.0) 
     * that has an area of 4.5 which upholds conditions 4.5 > 4 and 4.5 < 6. 
     */
    @Test void findsCorrectTriangle(){
        parameters.AREA1 = 4.0;
        parameters.AREA2 = 6.0;
        parameters.E_PTS = 2;
        parameters.F_PTS = 2;
        double[] x = {1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 2.0};
        double[] y = {1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0};
        // Result
        boolean condition = lic14.condition(x, y, x.length, parameters);
        assertTrue(condition, "There exists a triangle that upholds conditions of Lic14.");
    }

    /**
     * Verify that the condition function finds two different triangles one formed by the points (1.0, 1.0), (4.0, 1.0) and (2.0, 4.0) 
     * that has an area of 4.5 which upholds conditions 4.5 < 5. The other triangle formed by the points (4.0, 1.0), (2.0, 4.0), (6.0, 4.0) have an area of 6.0
     * which is greater than 5.    
     */
    @Test void findsTwoDifferentTriangles(){
        parameters.AREA1 = 5.0;
        parameters.AREA2 = 5.0;
        parameters.E_PTS = 2;
        parameters.F_PTS = 2;
        double[] x = {1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 2.0, 0.0, 0.0, 6.0};
        double[] y = {1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 4.0};
        //Result
        boolean condition = lic14.condition(x, y, x.length, parameters);
        assertTrue(condition, "There exists two triangles that togheter uphold the conditions of Lic14.");
    }

    /**
     * Verify that the condition function throws an exception when length of array x is 8 and the length of array y is 7.
     */
    @Test void testInvalidInput(){
        parameters.AREA1 = 4.0;
        parameters.AREA2 = 6.0;
        parameters.E_PTS = 2;
        parameters.F_PTS = 2;
        double[] x = {1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0}; // x is shorter than y.
        double[] y = {1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0};
        // Result
        assertThrows(IllegalArgumentException.class, () -> lic14.condition(x, y, x.length, parameters));
       
    }
}
