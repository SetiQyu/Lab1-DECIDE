package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import app.decide.lic.Lic11;
import app.decide.Decide.Parameters;;

public class Lic11Test {

    private Lic11 test_Lic11;
    private Decide decide;

    @BeforeEach 
    public void setUp() {
        test_Lic11 = new Lic11();
        decide = new Decide();
    }
    /**
     * Tests if the the correct pair that upholds the conditions for Lic11 is found.
     */
    @Test
    public void finds_correct_pair(){
        double[] p1 = {0.0, 0.0, 0.0, 0, 0.0, 0.0, 0.0, 0.0};  // Not relevant for this LIC
        int[] p2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3}; // G_PTS set to 3
        Parameters parameters = decide.new Parameters(p1, p2);
        // Data
        double[] x = {3.0, 0.0, 4.0, 0.0, 3.0, 0.0, 3.0}; // The combination here it should find the combination 4.0(index 2) and 3.0(index 6). 
        double[] y = {0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 3.0}; // Also not relevant for the LIC.

        assertTrue(test_Lic11.condition(x, y, x.length, parameters));
    }
    
    /**
     * If there is no pairs that uphold the condition for Lic11 the method should return false. 
     */
    @Test
    public void no_pairs(){
        double[] p1 = {0.0, 0.0, 0.0, 0, 0.0, 0.0, 0.0, 0.0};  // Not relevant for this LIC
        int[] p2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3}; // G_PTS set to 3
        Parameters parameters = decide.new Parameters(p1, p2);
        // Data
        double[] x = {3.0, 0.0, 3.0, 0.0, 3.0, 0.0, 3.0}; // No pair upholds the condition x[j] - x[i] < 0. 
        double[] y = {0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 3.0}; // Also not relevant for the LIC.

        assertFalse(test_Lic11.condition(x, y, x.length, parameters));
    }

    /**
     * Invalid input should throw an exception. 
     */
    @Test
    public void test_invalid_input(){
        boolean thrown_exception = false;
        double[] p1 = {0.0, 0.0, 0.0, 0, 0.0, 0.0, 0.0, 0.0};  // Not relevant for this LIC
        int[] p2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3}; // G_PTS set to 3
        Parameters parameters = decide.new Parameters(p1, p2);
        double[] x = {1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0}; 
        double[] y = {1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0}; // y is shorter than x
        
        assertThrows(IllegalArgumentException.class, () -> test_Lic11.condition(x, y, x.length, parameters));
        
    }
}


