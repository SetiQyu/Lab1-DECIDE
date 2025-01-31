package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import app.decide.Decide.Parameters;

/**
* Lic 12 condition:
* There exists at least one set of two data points, separated by exactly K PTS consecutive intervening points, which are a distance greater than the length, LENGTH1, apart. 
* In addition, there exists at least one set of two data points (which can be the same or different from
* the two data points just mentioned), separated by exactly K PTS consecutive intervening
* points, that are a distance less than the length, LENGTH2, apart. Both parts must be true
* for the LIC to be true. The condition is not met when NUMPOINTS < 3.
* 0 ≤ LENGTH2
*/
public class Lic12Test {
    private Lic12 lic12;
    private Decide decide;
    private Decide.Parameters parameters;
    @BeforeEach 
    public void setUp() {
        lic12 = new Lic12();
        decide = new Decide();
        parameters = new Decide().new Parameters(new double[8], new int[11]);
    }

    /**
     * The function should find the pair (1.0, 1.0) and (3.0, 1.0) that are 3 consecutive points apart and the distance between being (2.0) within the interval 1 < 2 < 4 is found
     * and should return true. 
     */
    @Test void samePairUpholdsConditions(){
        parameters.LENGTH1 = 1.0;
        parameters.LENGTH2 = 4.0;
        parameters.K_PTS = 3;
        // Data
        double[] x = {1.0, 0.0, 0.0, 0.0, 3.0, 0.0};
        double[] y = {1.0, 0.0, 0.0, 0.0, 1.0, 0.0}; 
        // Result 
        boolean condition = lic12.condition(x, y, x.length, parameters);
        assertTrue(condition, "There exists one pair that upholds condition for Lic12.");

    }

    /**
     * Verify that the condition function finds the pair (3.0, 1.0) and (1.0, 1.0) that are 3 consecutive points apart and have a distance of 2.0 
     * between them which upholds condition 2 < 5 and 
     * that the function finds the pair (1.0, 1.0) and (5.0, 1.0) that are 3 consecutive points apart and have a distance of 4 between them which upholds condition 4 > 3.  
     */
    @Test void differentPairsUpholdConditions(){
        parameters.LENGTH1 = 3.0;
        parameters.LENGTH2 = 5.0;
        parameters.K_PTS = 3;
        // Data
        double[] x = {3.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 5.0}; 
        double[] y = {1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0};  
        // Result
        boolean condition = lic12.condition(x, y, x.length, parameters);
        assertTrue(condition, "There exists two pairs that togheter uphold conditions of Lic12");
    }

    /**
     * Verify that the condition function recognizes that there are no pairs separated by 3 consecutive points that have a distance > 3 
     * and the function returns false.  
     */
    @Test void noPairsUpholdConditions(){
        parameters.LENGTH1 = 3.0;
        parameters.LENGTH2 = 5.0;
        parameters.K_PTS = 3;
        // Data
        double[] x = {0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0}; 
        double[] y = {0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0}; 
        // Result
        boolean condition = lic12.condition(x, y, x.length, parameters);
        assertFalse(condition, "No pairs that uphold conditions of Lic12 exist.");
    }
}
