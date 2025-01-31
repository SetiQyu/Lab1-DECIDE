package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import app.decide.Decide.Parameters;

/**
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

    private Lic13 test_Lic13;
    private Decide decide;

    @BeforeEach 
    public void setUp() {
        test_Lic13 = new Lic13();
        decide = new Decide();
    }

    /**
     * <b>Contract:</b> The condition function finds a triangle that upholds conditions of Lic13. <p>
     * <b>Input: </b> RADIUS1 = 3.0, RADIUS2 = 7.0, A_PTS = 2, B_PTS = 2, x = (0.0, 0.0, 0.0, 6.0, 0.0, 0.0, 3.0), y = (0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 4.0) <p>
     * <b>Expected output:</b> true <p>
     * <b>Test Purpose:</b> Verify that the condition function finds the triangle formed by (0.0)-(6.0)-(3, 4) which are separated by 2 consecutive points 
     * have a circumradius of 3.125 which is greater than 3 and smaller than 7 so the function should return true. 
     */
    @Test
    public void validTriangleNoLine(){
        double[] p1 = {0.0, 3.0, 0.0, 0.0, 0.0, 0.0, 7.0, 0.0};  // RADIUS1 and RADIUS2 set to 3.0 and 7.0 respectively
        int[] p2 = {0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0}; // A_PTS  and B_PTS set to 2
        Parameters parameters = decide.new Parameters(p1, p2);

        // Data
        double[] x = {0.0, 0.0, 0.0, 6.0, 0.0, 0.0, 3.0}; // Points set up with distance A_PTS and B_PTS
        double[] y = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 4.0}; // Triangle has side lengths 6 5 5

        assertTrue(test_Lic13.condition(x, y, x.length, parameters));
    }

    /**
     * <b>Contract:</b> The condition function recognizes that there exists no triangle or line that upholds conditions of Lic13. <p>
     * <b>Input: </b> RADIUS1 = 3.0, RADIUS2 = 7.0, A_PTS = 2, B_PTS = 2, x = (0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), y = (0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0) <p>
     * <b>Expected output:</b> false <p>
     * <b>Test Purpose:</b> Verify that the condition function recognizes that the input forms no triangle or line that upholds conditions of Lic13 and returns false. 
     */
    @Test
    public void noTriangleNoLine(){
        double[] p1 = {0.0, 3.0, 0.0, 0.0, 0.0, 0.0, 7.0, 0.0};  // RADIUS1 and RADIUS2 set to 3.0 and 7.0 respectively
        int[] p2 = {0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0}; // A_PTS  and B_PTS set to 2
        Parameters parameters = decide.new Parameters(p1, p2);

        // Data
        double[] x = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}; 
        double[] y = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}; // No triangle present or line present


        // Check if Lic13 returns false if there exists no triangle or line that upholds the condition.
        assertFalse(test_Lic13.condition(x, y, x.length, parameters));
    }
    
    /**
     * <b>Contract:</b> The condition function finds a line that upholds conditions of Lic13. <p>
     * <b>Input: </b> RADIUS1 = 3.0, RADIUS2 = 7.0, A_PTS = 2, B_PTS = 2, x = (0.0, 2.0, 0.0, 0.0, 0.0, 0.0, 7.0, 0.0), y = (0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0) <p>
     * <b>Expected output:</b> true <p>
     * <b>Test Purpose:</b> Verify that the condition function finds the points (0.0, 0.0), (5.0, 0.0), (6.0, 0.0) that are separated by 2 consecutive points 
     * form a line with length 6 
     * which is > 3 and < 7 and the function returns true.   
     */
    @Test
    public void noTriangleValidLine(){
        double[] p1 = {0.0, 2.0, 0.0, 0.0, 0.0, 0.0, 7.0, 0.0};  // RADIUS1 and RADIUS2 set to 2.0 and 7.0 respectively
        int[] p2 = {0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0}; // A_PTS  and B_PTS set to 2
        Parameters parameters = decide.new Parameters(p1, p2);
        // Data
        double[] x = {0.0, 0.0, 0.0, 5.0, 0.0, 0.0, 6.0}; 
        double[] y = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}; // The line (0.0, 0.0) - (5.0, 0.0) - (6.0, 0.0) will fit into radius2 but not radius1. 

        assertTrue(test_Lic13.condition(x, y, x.length, parameters));
    }


    /**
     * <b>Contract:</b> The condition function throws an exception when the lengths of the x and y points do not match. <p>
     * <b>Input: </b> RADIUS1 = 3.0, RADIUS2 = 7.0, A_PTS = 2, B_PTS = 2,x = (0.0, 0.0, 0.0, 6.0, 0.0, 0.0, 3.0, 0.0), y = (0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 4.0) <p>
     * <b>Expected output: IllegalArgumentException </b> <p>
     * <b>Test Purpose:</b> Verify that the condition function throws an exception when length of array x is 8 and the length of array y is 7. 
     */
    @Test
    public void testInvalidInput(){
        double[] p1 = {0.0, 3.0, 0.0, 0.0, 0.0, 0.0, 7.0, 0.0};  // RADIUS1 and RADIUS2 set to 3.0 and 7.0 respectively
        int[] p2 = {0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0}; // A_PTS  and B_PTS set to 2
        Parameters parameters = decide.new Parameters(p1, p2);

        // Data
        double[] x = {0.0, 0.0, 0.0, 6.0, 0.0, 0.0, 3.0, 0.0}; // The last x-coordinate has no corresponding y-coordinate. 
        double[] y = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 4.0}; 
 
        // Check if program throws exception if coordinates are invalid.
        
        assertThrows(IllegalArgumentException.class, () -> test_Lic13.condition(x, y, x.length, parameters));
        
    }


}
