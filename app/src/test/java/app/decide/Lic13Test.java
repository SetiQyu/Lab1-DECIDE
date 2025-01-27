package app.decide;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import app.decide.lic.Lic13;
import app.decide.Decide.Parameters;
public class Lic13Test {

    private Lic13 test_Lic13;
    private Decide decide;

    @BeforeEach 
    public void setUp() {
        test_Lic13 = new Lic13();
        decide = new Decide();
    }

    /**
     * If there is one set of 3 points that form a triangle which circumradius is within the interval [RADIUS1, RADIUS2] the function returns true.  
     */
    @Test
    public void valid_triangle_no_line(){
        double[] p1 = {0.0, 3.0, 0.0, 0.0, 0.0, 0.0, 7.0, 0.0};  // RADIUS1 and RADIUS2 set to 3.0 and 7.0 respectively
        int[] p2 = {0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0}; // A_PTS  and B_PTS set to 2
        Parameters parameters = decide.new Parameters(p1, p2);

        // Data
        double[] x = {0.0, 0.0, 0.0, 6.0, 0.0, 0.0, 3.0}; // Points set up with distance A_PTS and B_PTS
        double[] y = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 4.0}; // Triangle has side lengths 6 5 5

        // Circumradius of triangle is 3.125 which means that it will not fit within Radius1 but will fit within radius2. 

        // Check if Lic13 finds the triangle that upholds condition and returns true;
        assertTrue(test_Lic13.condition(x, y, x.length, parameters));
    }

    /** 
     * If there is no set of 3 points that either form a line or a triangle the function returns false.  
    */
    @Test
    public void no_triangle_no_line(){
        double[] p1 = {0.0, 3.0, 0.0, 0.0, 0.0, 0.0, 7.0, 0.0};  // RADIUS1 and RADIUS2 set to 3.0 and 7.0 respectively
        int[] p2 = {0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0}; // A_PTS  and B_PTS set to 2
        Parameters parameters = decide.new Parameters(p1, p2);

        // Data
        double[] x = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}; 
        double[] y = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}; // No triangle present or line present


        // Check if Lic13 returns false if there exists no triangle or line that upholds the condition.
        assertTrue(!test_Lic13.condition(x, y, x.length, parameters));
    }
    
    /** 
     * If there is 3 points that uphold the conditions for spacing that form a line where the greatest distance is within the interval [RADIUS1, RADIUS2] the function returns true
     */
    @Test
    public void no_triangle_valid_line(){
        double[] p1 = {0.0, 2.0, 0.0, 0.0, 0.0, 0.0, 7.0, 0.0};  // RADIUS1 and RADIUS2 set to 2.0 and 7.0 respectively
        int[] p2 = {0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0}; // A_PTS  and B_PTS set to 2
        Parameters parameters = decide.new Parameters(p1, p2);

        // Data
        double[] x = {0.0, 0.0, 0.0, 5.0, 0.0, 0.0, 6.0}; 
        double[] y = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}; // The line (0.0, 0.0) - (5.0, 0.0) - (6.0, 0.0) will fit into radius2 but not radius1. 


        // Check if Lic13 returns true if there exists no triangle but a line that upholds the condition.
        assertTrue(test_Lic13.condition(x, y, x.length, parameters));
    }


    /**
     * Invalid input should throws an exception. 
     */
    @Test
    public void test_invalid_input(){
        double[] p1 = {0.0, 3.0, 0.0, 0.0, 0.0, 0.0, 7.0, 0.0};  // RADIUS1 and RADIUS2 set to 3.0 and 7.0 respectively
        int[] p2 = {0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0}; // A_PTS  and B_PTS set to 2
        Parameters parameters = decide.new Parameters(p1, p2);

        // Data
        double[] x = {0.0, 0.0, 0.0, 6.0, 0.0, 0.0, 3.0, 0.0}; // The last x-coordinate has no corresponding y-coordinate. 
        double[] y = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 4.0}; 
 
        // Check if program throws exception if coordinates are invalid.
        boolean thrown_exception = false; 
        try{
            test_Lic13.condition(x, y, x.length, parameters);
        }catch(IllegalArgumentException e){
            thrown_exception = true;
        }
        assertTrue(thrown_exception);
    }



}
