package app.decide.lic;

import java.util.concurrent.locks.Condition;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import app.decide.Decide;

public class Lic2Test {
    static double[] x;
    static double[] y;
    static Decide.Parameters params;
    
    /**
     * Condition is true because the angle deviates from π by more than EPSILON.
     */
    @Test
    void trueCondition() {
    Lic2 lic = new Lic2();

    // Define a set of data points
    double[] x = {0.0, 1.0, 2.0};  
    double[] y = {0.0, 1.0, 0.0};  // Forms an angle ≠ π
    
    Decide instance = new Decide();
    instance.getClass();
    Decide.Parameters params = instance.new Parameters(new double[8], new int[11]);
 
    params.EPSILON = 0.1; // Small margin from π

    boolean condition = lic.condition(x, y, x.length, params);
    Assertions.assertTrue(condition, 
        "Should return true because the angle deviates from π by more than EPSILON.");
    }

    /**
     * Condition is false since at least three data points are required.
     */
    @Test
    void falseCondition() {
    Lic2 lic = new Lic2();

    // Define a set of data points
    double[] x = {0.0, 1.0};  
    double[] y = {0.0, 1.0};  // Only two points

    Decide instance = new Decide();
    instance.getClass();
    Decide.Parameters params = instance.new Parameters(new double[8], new int[11]);

    params.EPSILON = 0.1;  

    boolean condition = lic.condition(x, y, x.length, params);
    Assertions.assertFalse(condition, 
        "Should return false because there are not enough points to form an angle.");
    }

    /**
     * Condition is false since the angle deviates from π by less than EPSILON.
     */
    @Test
    void falseConditionAngle() {
    Lic2 lic = new Lic2();

    // Define a set of data points
    double[] x = {0.0, 2.0, 6.0};  
    double[] y = {0.0, 1.0, 0.0}; 

    Decide instance = new Decide();
    instance.getClass();
    Decide.Parameters params = instance.new Parameters(new double[8], new int[11]);

    // High margin from π
    params.EPSILON = 1;

    boolean condition = lic.condition(x, y, x.length, params);
    Assertions.assertFalse(condition, 
        "Should return false because the angle is exactly π and within EPSILON.");
    }


}

