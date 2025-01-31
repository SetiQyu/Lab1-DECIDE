package app.decide.lic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import app.decide.Decide;

/**
 * There exists at least one set of Q PTS consecutive data points that lie in more than QUADS
 * quadrants. Where there is ambiguity as to which quadrant contains a given point, priority
 * of decision will be by quadrant number, i.e., I, II, III, IV. For example, the data point (0,0)
 * is in quadrant I, the point (-l,0) is in quadrant II, the point (0,-l) is in quadrant III, the point
 * (0,1) is in quadrant I and the point (1,0) is in quadrant I. (2 <= Q_PTS <= NUMPOINTS), (1 <= QUADS <= 3)
 */
public class Lic4Test {
  
    /**
     * Should be true since at least one group of Q_PTS spans more than QUADS quadrants
     */
    @Test void trueCondition() {
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
     * Should be false since all points stay within QUADS quadrants
     */
    @Test void falseCondition() {
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
