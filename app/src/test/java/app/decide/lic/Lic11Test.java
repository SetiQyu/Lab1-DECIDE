package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import app.decide.Decide.Parameters;

/**
* Lic 11 condition:
* There exists at least one set of two data points, (X[i],Y[i]) and (X[j],Y[j]), separated by
* exactly G PTS consecutive intervening points, such that X[j] - X[i] < 0. (where i < j ) The
* condition is not met when NUMPOINTS < 3.
* 1 ≤ G PTS ≤ NUMPOINTS−2
*/
public class Lic11Test {

    private Lic11 lic11;
    private Decide decide;
    private Decide.Parameters parameters;

    @BeforeEach 
    public void setUp() {
        lic11 = new Lic11();
        decide = new Decide();
        parameters = new Decide().new Parameters(new double[8], new int[11]);
    }
    /**
     * Verify that the condition function returns true as x[6] < x[2] which upholds conditions for Lic 11.  
     */
    @Test void findsCorrectPair(){
        parameters.G_PTS = 3;
        // Data
        double[] x = {3.0, 0.0, 4.0, 0.0, 3.0, 0.0, 3.0}; // It should find the combination 4.0(index 2) and 3.0(index 6). 
        double[] y = {0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 3.0}; // Also not relevant for the LIC.
        // Results
        boolean condition = lic11.condition(x, y, x.length, parameters);
        assertTrue(condition, "There exists one pair that upholds conditions of Lic11");
    }
    
    /**
     * Verify that if no pair from the input array x that are separated by 3 consecutive points uphold conditions x[j] - x[i] < 0 where (i > j)
     * the function returns false.    
     */ 
    @Test void noPairs(){
        parameters.G_PTS = 3;
        // Data
        double[] x = {3.0, 0.0, 3.0, 0.0, 3.0, 0.0, 3.0}; // No pair upholds the condition x[j] - x[i] < 0. 
        double[] y = {0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 3.0}; // Also not relevant for the LIC.
        // Result
        boolean condition = lic11.condition(x, y, x.length, parameters);
        assertFalse(condition, "No pairs exist that uphold conditions of Lic11.");
    }

    /**
     * Verify that the condition function throws an exception when length of array x is 8 and the length of array y is 7.
     */
    @Test void testInvalidInput(){
        parameters.G_PTS = 3;
        double[] x = {1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0}; 
        double[] y = {1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0}; // y is shorter than x
        // Result
        assertThrows(IllegalArgumentException.class, () -> lic11.condition(x, y, x.length, parameters));
        
    }
}


