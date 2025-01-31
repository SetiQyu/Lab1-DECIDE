package app.decide.lic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import app.decide.Decide;

/**
 * There exists at least one set of three consecutive data points which form an angle such that: 
 * angle < (PI − EPSILON) or angle > (PI + EPSILON)
 * The second of the three consecutive points is always the vertex of the angle. If either the first
 * point or the last point (or both) coincides with the vertex, the angle is undefined and the LIC
 * is not satisfied by those three points. (0 <= EPSILON <= PI)
 */
public class Lic2Test {
    static double[] x;
    static double[] y;
    static Decide.Parameters params;
    
    /**
     * Should be true since the angle deviates from pi by more than EPSILON
     */
    @Test void trueCondition() {
    Lic2 lic = new Lic2();
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
     * Should be false since there are not enough points to form an angle
     */
    @Test void falseCondition() {
    Lic2 lic = new Lic2();
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
     * Should be false because the angle is exactly pi and within EPSILON
     */
    @Test void falseConditionAngle() {
    Lic2 lic = new Lic2();
    double[] x = {0.0, 2.0, 6.0};  
    double[] y = {0.0, 1.0, 0.0}; 

    Decide instance = new Decide();
    instance.getClass();
    Decide.Parameters params = instance.new Parameters(new double[8], new int[11]);

    params.EPSILON = 1;

    boolean condition = lic.condition(x, y, x.length, params);
    Assertions.assertFalse(condition, 
        "Should return false because the angle is exactly π and within EPSILON.");
    }
}
