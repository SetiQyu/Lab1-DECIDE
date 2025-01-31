package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import app.decide.Decide.Parameters;

/**
* There exists at least one set of two data points, separated by exactly K PTS consecutive intervening points, which are a distance greater than the length, LENGTH1, apart. 
* In addition, there exists at least one set of two data points (which can be the same or different from
* the two data points just mentioned), separated by exactly K PTS consecutive intervening
* points, that are a distance less than the length, LENGTH2, apart. Both parts must be true
* for the LIC to be true. The condition is not met when NUMPOINTS < 3.
* 0 ≤ LENGTH2
*/
public class Lic12Test {
    private Lic12 test_Lic12;
    private Decide decide;

    @BeforeEach 
    public void setUp() {
        test_Lic12 = new Lic12();
        decide = new Decide();
    }

    /**
     * <b>Contract:</b> The function finds a pair that upholds conditions of Lic 12. <p>
     * <b>Input: </b> LENGTH1 = 1.0, LENGTH2 = 4.0, K_PTS = 3, x = (1.0, 0.0, 0.0, 0.0, 3.0, 0.0), y = (1.0, 0.0, 0.0, 0.0, 1.0, 0.0) <p>
     * <b>Expected output:</b> true <p>
     * <b>Test Purpose:</b> Verify that the pair (1.0, 1.0) and (3.0, 1.0) that are 3 consecutive points apart and the distance between being (2.0) within the interval 1 < 2 < 4 is found
     * and the function returns true. 
     */
    @Test
    public void samePairUpholdsConditions(){
        double[] p1 = {1.0, 0.0, 0.0, 0.0, 0.0, 4.0, 0.0, 0.0};  // Length1 and length2 set to 1.0 and 4.0 respectively
        int[] p2 = {0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0}; // K_PTS set to 3
        Parameters parameters = decide.new Parameters(p1, p2);
        // Data
        double[] x = {1.0, 0.0, 0.0, 0.0, 3.0, 0.0};
        double[] y = {1.0, 0.0, 0.0, 0.0, 1.0, 0.0}; 

        assertTrue(test_Lic12.condition(x, y, x.length, parameters));

    }

    /**
     * <b>Contract:</b> The condition function finds two different pairs that uphold conditions of Lic 12. <p>
     * <b>Input: </b> LENGTH1 = 3.0, LENGTH2 = 5.0, K_PTS = 3, x = (3.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 5.0), y = (1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0) <p>
     * <b>Expected output:</b> true <p>
     * <b>Test Purpose:</b> Verify that the condition function finds the pair (3.0, 1.0) and (1.0, 1.0) that are 3 consecutive points apart and have a distance of 2.0 
     * between them which upholds condition 2 < 5 and 
     * that the function finds the pair (1.0, 1.0) and (5.0, 1.0) that are 3 consecutive points apart and have a distance of 4 between them which upholds condition 4 > 3.  
     */
    @Test
    public void differentPairsUpholdConditions(){
        double[] p1 = {3.0, 0.0, 0.0, 0.0, 0.0, 4.0, 0.0, 0.0};  // Length1 and length2 set to 3.0 and 5 respectively
        int[] p2 = {0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0}; // K_PTS set to 3
        Parameters parameters = decide.new Parameters(p1, p2);
        // Data
        double[] x = {3.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 5.0}; 
        double[] y = {1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0};  

        assertTrue(test_Lic12.condition(x, y, x.length, parameters));
    }

    /**
     * <b>Contract:</b> The condition function recognizes that there are no pairs that uphold conditions of Lic12. <p>
     * <b>Input: </b> LENGTH1 = 3.0, LENGTH2 = 5.0, K_PTS = 3, x = (0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0), y = (0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0) <p>
     * <b>Expected output:</b> false <p>
     * <b>Test Purpose:</b> Verify that the condition function recognizes that there are no pairs separated by 3 consecutive points that have a distance > 3 
     * and the function returns false.  
     */
    @Test
    public void noPairsUpholdConditions(){
        double[] p1 = {3.0, 0.0, 0.0, 0.0, 0.0, 5.0, 0.0, 0.0};  // Length1 and length2 set to 3.0 and 5 respectively
        int[] p2 = {0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0}; // K_PTS set to 3
        Parameters parameters = decide.new Parameters(p1, p2);
        // Data
        double[] x = {0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0}; 
        double[] y = {0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0}; 

        assertFalse(test_Lic12.condition(x, y, x.length, parameters));
    }
}
