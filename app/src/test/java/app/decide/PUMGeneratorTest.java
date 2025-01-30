package app.decide;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the PUMGenerator class.
 */
public class PUMGeneratorTest {

    private Decide.CONNECTORS[][] mixed_lcm;
    private Decide.CONNECTORS[][] notused_lcm;
    private boolean[] alternating_cmv;
    private boolean[] false_cmv;
    private int test_dimension;

    
    @BeforeEach public void setUp() {
        test_dimension = 15;

        // Initialize LCM with a mix of ANDD, ORR, and NOTUSED
        mixed_lcm = new Decide.CONNECTORS[test_dimension][test_dimension];
        for (int i = 0; i < test_dimension; i++) {
            for (int j = 0; j < test_dimension; j++) {
                if (i == j) {
                    mixed_lcm[i][j] = Decide.CONNECTORS.NOTUSED;
                } else if ((i + j) % 2 == 0) {
                    mixed_lcm[i][j] = Decide.CONNECTORS.ANDD;
                } else {
                    mixed_lcm[i][j] = Decide.CONNECTORS.ORR;
                }
            }
        }

        // Initialize LCM with all NOTUSED
        notused_lcm = new Decide.CONNECTORS[test_dimension][test_dimension];
        for (int i = 0; i < test_dimension; i++) {
            for (int j = 0; j < test_dimension; j++) {
                notused_lcm[i][j] = Decide.CONNECTORS.NOTUSED;
            }
        }

        // Initialize CMV with alternating true and false values
        alternating_cmv = new boolean[test_dimension];
        for (int i = 0; i < test_dimension; i++) {
            alternating_cmv[i] = (i % 2 == 0); // Alternate true and false
        }

        // Initialize CMV with all false values
        false_cmv = new boolean[test_dimension];
        java.util.Arrays.fill(false_cmv, false);
    }

    /**
     * Contract: If LCM[i][j] is set to NOTUSED, the corresponding entry in PUM[i][j] should always be true.
     *           ANDD requires both CMV[i] and CMV[j] to be true.
     *           ORR requires at least one of CMV[i] or CMV[j] to be true.
     * Test Purpose: This test verifies that the PUM is correctly generated with a mix of ANDD, ORR, and NOTUSED.
     */
    @Test
    public void testGeneratePUM_ValidInput() {
        boolean[][] pum = PUMGenerator.generatePUM(mixed_lcm, alternating_cmv);

        // Manually verify a few expected results
        assertTrue(pum[0][0], "NOTUSED connector should default to true.");
        assertFalse(pum[3][1], "ORR connector should be false if both CMV[3] and CMV[1] are false.");
        assertTrue(pum[1][2], "ORR connector should be true if either CMV[1] or CMV[2] is true.");
        assertTrue(pum[2][4], "ANDD connector should be true if both CMV[2] and CMV[4] are true.");
    }

    /**
     * Contract: If all LCM[i][j] connectors are NOTUSED, the corresponding entries in the PUM should always be true,
     *           regardless of the values in the CMV.
     * Test Purpose: This test verifies that when all LCM connectors are set to NOTUSED, all entries in the PUM are true.
     */
    @Test
    public void testGeneratePUM_AllNOTUSED() {
        boolean[][] pum = PUMGenerator.generatePUM(notused_lcm, false_cmv);

        // Assert that all entries in the PUM are true (default behavior for NOTUSED)
        for (int i = 0; i < test_dimension; i++) {
            for (int j = 0; j < test_dimension; j++) {
                assertTrue(pum[i][j], "NOTUSED connector should result in true in the PUM.");
            }
        }
    }
}
