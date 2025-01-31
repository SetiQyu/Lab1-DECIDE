package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import app.decide.Decide.Parameters;

/**
* Lic 13 condition:  
* There exists at least one set of three data points, separated by exactly A PTS and B PTS
* consecutive intervening points, respectively, that cannot be contained within or on a circle of
* radius RADIUS1. In addition, there exists at least one set of three data points (which can be
* the same or different from the three data points just mentioned) separated by exactly A PTS
* and B PTS consecutive intervening points, respectively, that can be contained in or on a
* circle of radius RADIUS2. Both parts must be true for the LIC to be true. The condition is
* not met when NUMPOINTS < 5.
* 0 ≤ RADIUS2
*/
public class Lic13Test {
    private Lic13 lic13;
    private Decide decide;
    private Decide.Parameters parameters;

    @BeforeEach 
    void setUp() {
        lic13 = new Lic13();
        decide = new Decide();
        parameters = new Decide().new Parameters(new double[8], new int[11]);
    }

    /** 
     * Verify that the condition function finds the triangle formed by (0.0)-(6.0)-(3, 4) which are separated by 2 consecutive points 
     * have a circumradius of 3.125 which is greater than 3 and smaller than 7 so the function should return true. 
     */
    @Test void validTriangleNoLine(){
        parameters.RADIUS1 = 3.0;
        parameters.RADIUS2 = 7.0;
        parameters.A_PTS = 2;
        parameters.B_PTS = 2;
        // Data
        double[] x = {0.0, 0.0, 0.0, 6.0, 0.0, 0.0, 3.0}; // Points set up with distance A_PTS and B_PTS
        double[] y = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 4.0}; // Triangle has side lengths 6 5 5
        // Result
        boolean condition = lic13.condition(x, y, x.length, parameters);
        assertTrue(condition, "There exists a triangle that upholds conditions of Lic13.");
    }

    /**
     * Verify that the condition function recognizes that the input forms no triangle or line that upholds conditions of Lic13 and returns false. 
     */
    @Test void noTriangleNoLine(){
        parameters.RADIUS1 = 3.0;
        parameters.RADIUS2 = 7.0;
        parameters.A_PTS = 2;
        parameters.B_PTS = 2;
        // Data
        double[] x = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}; 
        double[] y = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}; // No triangle present or line present
        // Result
        boolean condition = lic13.condition(x, y, x.length, parameters);
        assertFalse(condition, "There is no triangle or line that upholds conditions of Lic13.");
    }
    
    /**
     * Verify that the condition function finds the points (0.0, 0.0), (5.0, 0.0), (6.0, 0.0) that are separated by 2 consecutive points 
     * form a line with length 6 
     * which is > 3 and < 7 and the function returns true.   
     */
    @Test void noTriangleValidLine(){
        parameters.RADIUS1 = 2.0;
        parameters.RADIUS2 = 7.0;
        parameters.A_PTS = 2;
        parameters.B_PTS = 2;
        // Data
        double[] x = {0.0, 0.0, 0.0, 5.0, 0.0, 0.0, 6.0}; 
        double[] y = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}; // The line (0.0, 0.0) - (5.0, 0.0) - (6.0, 0.0) will fit into radius2 but not radius1. 
        // Result
        boolean condition = lic13.condition(x, y, x.length, parameters);
        assertTrue(condition, "There exists a line that uphold conditions of Lic13");
    }


    /**
     * Verify that the condition function throws an exception when length of array x is 8 and the length of array y is 7. 
     */
    @Test void testInvalidInput(){
        parameters.RADIUS1 = 3.0;
        parameters.RADIUS2 = 7.0;
        parameters.A_PTS = 2;
        parameters.B_PTS = 2;
        // Data
        double[] x = {0.0, 0.0, 0.0, 6.0, 0.0, 0.0, 3.0, 0.0}; // The last x-coordinate has no corresponding y-coordinate. 
        double[] y = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 4.0}; 
        // Result
        assertThrows(IllegalArgumentException.class, () -> lic13.condition(x, y, x.length, parameters));
        
    }


}
