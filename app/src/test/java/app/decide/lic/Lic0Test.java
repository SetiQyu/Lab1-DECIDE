package app.decide.lic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import app.decide.Decide;

/**
 * Lic0 condition:
 * There exists at least one set of two consecutive data points that are 
 * a distance greater than the length, LENGTH1, apart. (0 <= LENGTH1)
 */
public class Lic0Test {
    static double[] x;
    static double[] y;
    static Decide.Parameters params;

    /**
     * Should be true since at least one pair of consecutive points exceed LENGTH1
     */
    @Test void trueCondition() {
       Lic0 lic = new Lic0();
       double[] x = {0.0, 3.0, 6.0, 9.0, 12.0};  
       double[] y = {0.0, 4.0, 8.0, 12.0, 16.0}; 
        
       Decide instance = new Decide();
       instance.getClass();
       Decide.Parameters params = instance.new Parameters(new double[8], new int[11]);
  
       params.LENGTH1 = 4.0;
       boolean condition = lic.condition(x, y, x.length, params);
       Assertions.assertTrue(condition, "Should return true because at least one pair of consecutive points exceeds LENGTH1.");
    }
    /**
     * Should be false since no consecutive pair of points exceed LENGTH1  
     */
    @Test void falseCondition() {
       Lic0 lic = new Lic0();
       double[] x = {0.0, 3.0, 6.0, 9.0, 12.0};  
       double[] y = {0.0, 4.0, 8.0, 12.0, 16.0}; 
 
       Decide instance = new Decide();
       instance.getClass();
       Decide.Parameters params = instance.new Parameters(new double[8], new int[11]);
 
       params.LENGTH1 = 6.0;
       boolean condition = lic.condition(x, y, x.length, params);
       Assertions.assertFalse(condition, "Should return false because no pair of consecutive points exceeds LENGTH1.");
    } 
 }
