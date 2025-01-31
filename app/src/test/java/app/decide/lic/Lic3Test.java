package app.decide.lic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import app.decide.Decide;

/**
* There exists at least one set of three consecutive data points that are the vertices of a triangle
* with area greater than AREA1. (0 <= AREA1)
*/
public class Lic3Test {

    /**
    * Should be true because at least one triangle has an area greater than AREA1 
    */
    @Test void trueCondition() {
        Lic3 lic = new Lic3();
        double[] x = {0.0, 2.0, 4.0};  
        double[] y = {0.0, 3.0, 0.0};  // triangle with area = 6

        Decide instance = new Decide();
        instance.getClass();
        Decide.Parameters params = instance.new Parameters(new double[8], new int[11]);

        params.AREA1 = 4.0; 

        boolean condition = lic.condition(x, y, x.length, params);
        Assertions.assertTrue(condition, 
            "Should return true because at least one triangle has an area greater than AREA1.");
    }

    /**
    * Should be false because all triangles have an area <= AREA1 
    */
    @Test void falseCondition_AllTrianglesBelowThreshold() {
        Lic3 lic = new Lic3();
        double[] x = {0.0, 1.0, 2.0};  
        double[] y = {0.0, 0.5, 0.0};  // triangle with area = 1/2

        Decide instance = new Decide();
        instance.getClass();
        Decide.Parameters params = instance.new Parameters(new double[8], new int[11]);

        params.AREA1 = 1.0; 

        boolean condition = lic.condition(x, y, x.length, params);
        Assertions.assertFalse(condition, 
            "Should return false because all triangles have an area ≤ AREA1.");
    }
}
