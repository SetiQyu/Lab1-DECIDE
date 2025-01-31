package app.decide.lic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import app.decide.Decide;

public class Lic4Test {
    


    /**
     * Condition is true since the given points are spread across three different quadrants (I, II, and III), which is more than QUADS (2).
     */
    @Test
    void trueCondition() {
        Lic4 lic = new Lic4();
        double[] x = {1.0, -1.0, -2.0, 2.0};  // in quadrants: I, II, III
        double[] y = {1.0, 1.0, -1.0, 1.0};  

        Decide instance = new Decide();
        instance.getClass();
        Decide.Parameters params = instance.new Parameters(new double[8], new int[11]);

        params.Q_PTS = 4;  
        params.QUADS = 2;  

        boolean condition = lic.condition(x, y, x.length, params);
        Assertions.assertTrue(condition, 
            "Should return true because at least one group of Q_PTS points spans more than QUADS quadrants.");
    }

     /**
     * Condition is false since all points are in quadrant I which is less than QUADS quadrants.
     */
    @Test
    void falseCondition() {
        Lic4 lic = new Lic4();
        double[] x = {1.0, 2.0, 3.0, 4.0};  // All in quadrant I
        double[] y = {1.0, 2.0, 3.0, 4.0};  

        Decide instance = new Decide();
        instance.getClass();
        Decide.Parameters params = instance.new Parameters(new double[8], new int[11]);

        params.Q_PTS = 4;
        params.QUADS = 2; 

        boolean condition = lic.condition(x, y, x.length, params);
        Assertions.assertFalse(condition, 
            "Should return false because all points stay within QUADS quadrants.");
    }
}
