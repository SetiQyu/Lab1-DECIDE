package app.decide;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.Random;
class FuvTest {

    private boolean[][] false_pum;
    private boolean[][] true_pum;
    private boolean[][] mixed_pum;
    private boolean[] false_puv; 
    private boolean[] true_puv; 
    private boolean[] mixed_puv; 
    private int test_dimension;

    @BeforeEach public void setUp(){
        test_dimension = 15;
        Random random = new Random();

        mixed_pum = new boolean[15][15];
        for (int i = 0; i < mixed_pum.length; i++) {
            java.util.Arrays.fill(mixed_pum[i], random.nextBoolean());
        }
        true_pum = new boolean[15][15];
        for (int i = 0; i < true_pum.length; i++) {
            java.util.Arrays.fill(true_pum[i], true);
        }
        
        false_pum = new boolean[15][15];
        for (int i = 0; i < false_pum.length; i++) {
            java.util.Arrays.fill(false_pum[i], false);
        }
        
        false_puv = new boolean[15]; 
        java.util.Arrays.fill(false_puv, false);
        
        true_puv = new boolean[15]; 
        java.util.Arrays.fill(true_puv, true);
        
        mixed_puv = new boolean[15];
        for(boolean val : mixed_puv){
            val = random.nextBoolean();
        }

    }
    @Test void testFalsePumFalsePuv() { 
        boolean[] expect = new boolean[15];
        java.util.Arrays.fill(expect, true);
        assertArrayEquals(expect, Fuv.getFUV(false_pum, false_puv, test_dimension),
                "All false PUV all false PUM gives all true FUV?");
    }
    @Test void testFalsePumTruePuv(){
        boolean[] expect = new boolean[15];
        java.util.Arrays.fill(expect, false);
        assertArrayEquals(expect, Fuv.getFUV(false_pum, true_puv, test_dimension),
                "All true PUV and all false PUM gives all false FUV?");
    }
    @Test void testTruePumFalsePuv(){
        boolean[] expect = new boolean[15];
        java.util.Arrays.fill(expect, true);
        assertArrayEquals(expect, Fuv.getFUV(true_pum, false_puv, test_dimension),
                "All false PUV and all true PUM gives all true FUV?");
    }
    @Test void testMixedPumFalsePuv(){
        boolean[] expect = new boolean[15];
        java.util.Arrays.fill(expect, true);

        assertArrayEquals(expect, Fuv.getFUV(mixed_pum, false_puv, test_dimension),
                "All false PUV and random PUM gives all true FUV?");
    }
    @Test void testTruePumMixedPuv(){
        boolean[] expect = new boolean[15];
        java.util.Arrays.fill(expect, true);

        assertArrayEquals(expect, Fuv.getFUV(true_pum, mixed_puv, test_dimension),
                "Random PUV and all true PUM gives all true FUV?");
    }
}
