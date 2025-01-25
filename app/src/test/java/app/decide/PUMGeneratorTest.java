package app.decide;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the PUMGenerator class.
 */
public class PUMGeneratorTest {

    /**
     * Test for a valid PUM generation with a mix of ANDD, ORR, and NOTUSED.
     */
    @Test
    public void testGeneratePUM_ValidInput() {
        // Define a valid 15x15 LCM 
        Decide.CONNECTORS[][] lcm = new Decide.CONNECTORS[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (i == j) {
                    lcm[i][j] = Decide.CONNECTORS.NOTUSED; 
                } else if ((i + j) % 2 == 0) {
                    lcm[i][j] = Decide.CONNECTORS.ANDD;
                } else {
                    lcm[i][j] = Decide.CONNECTORS.ORR;
                }
            }
        }
    
        // Define a valid 15-element CMV (Conditions Met Vector)
        boolean[] cmv = new boolean[15];
        for (int i = 0; i < 15; i++) {
            cmv[i] = (i % 2 == 0); // Alternate true and false
        }
    
        // Generate the PUM
        boolean[][] pum = PUMGenerator.generatePUM(lcm, cmv);
    
        // Manually verify a few expected results
        assertTrue(pum[0][0]); //  always true
        assertFalse(pum[3][1]); // ORR -> CMV[3] (false) AND CMV[1] (false)
        assertTrue(pum[1][2]); // ORR -> CMV[1] (false) OR CMV[2] (true)
        assertTrue(pum[2][4]); // ANDD -> CMV[2] (true) AND CMV[4] (true)
    }

    /**
     * Test for an edge case where all connectors in LCM are NOTUSED.
     */
    @Test
    public void testGeneratePUM_AllNOTUSED() {
        // Define an LCM where all connectors are NOTUSED
        Decide.CONNECTORS[][] lcm = new Decide.CONNECTORS[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                lcm[i][j] = Decide.CONNECTORS.NOTUSED;
            }
        }

        // Define a sample CMV
        boolean[] cmv = new boolean[15];
        for (int i = 0; i < 15; i++) {
            cmv[i] = false;
        }

        // Generate the PUM
        boolean[][] pum = PUMGenerator.generatePUM(lcm, cmv);

        // Assert that all entries in the PUM are true (default behavior for NOTUSED)
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                assertTrue(pum[i][j]);
            }
        }
        // Print a message if the test passes
        System.out.println("Test passed: testGeneratePUM_ValidInput.");
    }
    
}
