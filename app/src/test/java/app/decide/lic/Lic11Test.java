package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import app.decide.Decide.Parameters;

/**
* There exists at least one set of two data points, (X[i],Y[i]) and (X[j],Y[j]), separated by
* exactly G PTS consecutive intervening points, such that X[j] - X[i] < 0. (where i < j ) The
* condition is not met when NUMPOINTS < 3.
* 1 ≤ G PTS ≤ NUMPOINTS−2
*/
public class Lic11Test {

    private Lic11 test_Lic11;
    private Decide decide;

    @BeforeEach 
    public void setUp() {
        test_Lic11 = new Lic11();
        decide = new Decide();
    }
    /**
     * Verify that the condition function returns true as x[6] < x[2] which upholds conditions for Lic 11.  
     */
    @Test
    public void findsCorrectPair(){
        double[] p1 = {0.0, 0.0, 0.0, 0, 0.0, 0.0, 0.0, 0.0};  // Not relevant for this LIC
        int[] p2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3}; // G_PTS set to 3
        Parameters parameters = decide.new Parameters(p1, p2);
        // Data
        double[] x = {3.0, 0.0, 4.0, 0.0, 3.0, 0.0, 3.0}; // It should find the combination 4.0(index 2) and 3.0(index 6). 
        double[] y = {0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 3.0}; // Also not relevant for the LIC.
        // Results
        boolean condition = test_Lic11.condition(x, y, x.length, parameters);
        assertTrue(condition, "There exists one pair that upholds conditions of Lic11");
    }
    
    /**
     * Verify that if no pair from the input array x that are separated by 3 consecutive points uphold conditions x[j] - x[i] < 0 where (i > j)
     * the function returns false.    
     */ 
    @Test
    public void noPairs(){
        double[] p1 = {0.0, 0.0, 0.0, 0, 0.0, 0.0, 0.0, 0.0};  // Not relevant for this LIC
        int[] p2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3}; // G_PTS set to 3
        Parameters parameters = decide.new Parameters(p1, p2);
        // Data
        double[] x = {3.0, 0.0, 3.0, 0.0, 3.0, 0.0, 3.0}; // No pair upholds the condition x[j] - x[i] < 0. 
        double[] y = {0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 3.0}; // Also not relevant for the LIC.
        // Result
        boolean condition = test_Lic11.condition(x, y, x.length, parameters);
        assertFalse(condition, "No pairs exist that uphold conditions of Lic11.");
    }

    /**
     * Verify that the condition function throws an exception when length of array x is 8 and the length of array y is 7.
     */
    @Test
    public void testInvalidInput(){
        boolean thrown_exception = false;
        double[] p1 = {0.0, 0.0, 0.0, 0, 0.0, 0.0, 0.0, 0.0};  // Not relevant for this LIC
        int[] p2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3}; // G_PTS set to 3
        Parameters parameters = decide.new Parameters(p1, p2);
        double[] x = {1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0}; 
        double[] y = {1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0}; // y is shorter than x
        
        assertThrows(IllegalArgumentException.class, () -> test_Lic11.condition(x, y, x.length, parameters));
        
    }
}


