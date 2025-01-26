package app.decide;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import app.decide.lic.Lic12;
import app.decide.Decide.Parameters;
public class Lic12Test {
    private Lic12 test_Lic12;
    private Decide decide;

    @BeforeEach 
    public void setUp() {
        test_Lic12 = new Lic12();
        decide = new Decide();
    }

    /**
     * If there is only one pair that upholds the conditions for Lic12 it is found and the function returns true. 
     */
    @Test
    public void same_pair_upholds_conditions(){
        double[] p1 = {1.0, 0.0, 0.0, 0.0, 0.0, 4.0, 0.0, 0.0};  // Length1 and length2 set to 1.0 and 4.0 respectively
        int[] p2 = {0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0}; // K_PTS set to 3
        Parameters parameters = decide.new Parameters(p1, p2);
        // Data
        double[] x = {1.0, 0.0, 0.0, 0.0, 3.0, 0.0};
        double[] y = {1.0, 0.0, 0.0, 0.0, 1.0, 0.0}; 
        // The pair (1, 1), (3, 1) Has a euclidian distance of 2 which is in the interval length1 < 2 < length2

        assertTrue(test_Lic12.condition(x, y, x.length, parameters));

    }

    /**
     * If there is two different pairs that togheter uphold the condtions for Lic12 the function returns true.
     */
    @Test
    public void different_pairs_uphold_conditions(){
        double[] p1 = {3.0, 0.0, 0.0, 0.0, 0.0, 5.0, 0.0, 0.0};  // Length1 and length2 set to 3.0 and 5 respectively
        int[] p2 = {0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0}; // K_PTS set to 3
        Parameters parameters = decide.new Parameters(p1, p2);
        // Data
        double[] x = {3.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 5.0}; 
        double[] y = {1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0}; 
        // The pair (3, 1), (1, 1) upholds condition condition dist < length2 and pair (1, 1), (5, 1) upholds condition dist > length1. 

        assertTrue(test_Lic12.condition(x, y, x.length, parameters));
    }

    /**
     * If there is no pairs that uphold the conditons for Lic12 the function returns false. 
     */
    @Test
    public void no_pairs(){
        double[] p1 = {3.0, 0.0, 0.0, 0.0, 0.0, 5.0, 0.0, 0.0};  // Length1 and length2 set to 3.0 and 5 respectively
        int[] p2 = {0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0}; // K_PTS set to 3
        Parameters parameters = decide.new Parameters(p1, p2);
        // Data
        double[] x = {0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0}; 
        double[] y = {0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0}; 

        assertTrue(!test_Lic12.condition(x, y, x.length, parameters));
    }
}
