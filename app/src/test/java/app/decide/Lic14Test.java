package app.decide;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import app.decide.lic.Lic14;
import app.decide.Decide.Parameters;
public class Lic14Test {
    private Lic14 test_Lic14;
    private Decide decide;

    @BeforeEach
    public void setUp() {
        test_Lic14 = new Lic14();
        decide = new Decide();
    }
    /**
     * Tests if the check_area function calculates the correct area and does the correct comparison. 
     */
    @Test 
    public void test_check_area(){
        // Test data points on line y = x
        double[] A = {1.0, 1.0};
        double[] B = {3.0, 3.0};
        double[] C = {5.0, 5.0};
        // The area should be 0 which is greater than -1.
        assertTrue(Lic14.check_area(A, B, C, -1, true));
    }
    /**
     * Tests if the method can find one triangle that upholds the conditions of Lic14.
     */
    @Test
    public void finds_correct_triangle(){
        double[] p1 = {0.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 6.0}; // Area1 set to 4 and Area2 set to 6
        int[] p2 = {0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0}; // E_PTS and F_PTS set to 2.
        Parameters parameters = decide.new Parameters(p1, p2);
        // The points with the correct spacing results in a triangle with area 4.5 that is within the specified range AREA1 < 4.5 < AREA2 
        double[] x = {1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 2.0};
        double[] y = {1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0};
        assertTrue(test_Lic14.condition(x, y, x.length, parameters));
    }

    /**
     * Tests if the method can find two different triangles that uphold the conditions of Lic14. 
     */
    @Test
    public void finds_two_different_triangles(){
        double[] p1 = {0.0, 0.0, 0.0, 5.0, 0.0, 0.0, 0.0, 5.0}; // AREA1 set to 5 and AREA2 set to 5
        int[] p2 = {0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0}; // E_PTS and F_PTS set to 2.
        Parameters parameters = decide.new Parameters(p1, p2);
        // The method should find triangle (1.0, 1.0), (4.0, 1.0), (2.0, 4.0) with area= 4.5 that upholds the condition of being smaller than AREA2.
        // The second triangle has an area greater than AREA1 is the triangle (4.0, 1.0), (2.0, 4.0), (6.0, 4.0) with area = 6.0.   
        double[] x = {1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 2.0, 0.0, 0.0, 6.0};
        double[] y = {1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 4.0};
        assertTrue(test_Lic14.condition(x, y, x.length, parameters));
    }

    /** 
     * Tests if an invalid input throws an exception. 
     */
    @Test
    public void test_invalid_input(){
        boolean thrown_exception = false;
        double[] p1 = {0.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 6.0};
        int[] p2 = {0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0};
        Parameters parameters = decide.new Parameters(p1, p2);
        double[] x = {1.0, 0.0, 0.0, 4.0, 0.0, 0.0, 0.0, 0.0}; // x is shorter than y.
        double[] y = {1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 4.0};
        try{
            test_Lic14.condition(x, y, x.length, parameters);
        }catch(IllegalArgumentException e){
            thrown_exception = true;
        }
        assertTrue(thrown_exception);
    }
}
