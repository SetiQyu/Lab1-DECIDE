package app.decide.lic;

import app.decide.Decide;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import app.decide.Decide.Parameters;;

public class Lic10Test {

    private Lic10 test_Lic10;
    private Decide decide;

    @BeforeEach 
    public void setUp() {
        test_Lic10 = new Lic10();
        decide = new Decide();
    }


    /**
     * Tests if three collinear points returns an area of 0.
     * */ 
    @Test
    public void test_triangle_calculation(){
        // Test data points on line y = x
        double ax = 1.0; 
        double ay = 1.0;
        double bx = 3.0; 
        double by = 3.0;
        double cx = 5.0; 
        double cy = 5.0;
        double delta = 0.000001;
        // The area should be 0 as the points are on the same line.
        assertEquals(0.0, test_Lic10.calcArea(ax, ay, bx, by, cx, cy), delta);

    }

    /**
     * If there is one triangle that upholds the constraints of Lic10 with area > AREA1 the method finds it. 
     */
    @Test
    public void finds_correct_triangle(){
        double[] p1 = {0.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0};
        int[] p2 = {0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0};
        Parameters parameters = decide.new Parameters(p1, p2);
        // The points with the correct spacing results in a triangle with a larger area than the one specififed in p1. 
        double[] x = {1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 2.0};
        double[] y = {1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0};

        assertTrue(test_Lic10.condition(x, y, x.length, parameters));

    }

    /**
     * Tests if an invalid input throws an exception. 
     */
    @Test
    public void test_invalid_input(){
        // Local variables
        double[] p1 = {0.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0};
        int[] p2 = {0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0};
        Parameters parameters = decide.new Parameters(p1, p2);
        double[] x = {1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0}; // x is shorter than y.
        double[] y = {1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0};

        assertThrows(IllegalArgumentException.class, () -> test_Lic10.condition(x, y, x.length, parameters));
        
    }
}
