package app.decide;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class DecideTest {
    @BeforeEach
    void init() {
        Decide.X = new double[] {0, 0, 1};
        Decide.Y = new double[] {0, 1, 0};
        Decide.NUMPOINTS = 3;

        Decide.LCM = new Decide.CONNECTORS[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Decide.LCM[i][j] = Decide.CONNECTORS.ANDD;
            }
        }

        Decide.PUV = new boolean[15];

        Decide.parameters = new Decide().new Parameters(new double[8], new int[11]);

        Decide.parameters.A_PTS = 1;
        Decide.parameters.B_PTS = 1;
        Decide.parameters.C_PTS = 1;
        Decide.parameters.D_PTS = 1;
        Decide.parameters.E_PTS = 1;
        Decide.parameters.F_PTS = 1;
        Decide.parameters.G_PTS = 1;
        Decide.parameters.K_PTS = 1;
        Decide.parameters.N_PTS = 1;
        Decide.parameters.Q_PTS = 1;
        Decide.parameters.LENGTH1 = 1;
        Decide.parameters.LENGTH2 = 1;
        Decide.parameters.RADIUS1 = 1;
        Decide.parameters.RADIUS2 = 1;
        Decide.parameters.AREA1 = 1;
        Decide.parameters.AREA2 = 1;
        Decide.parameters.QUADS = 1;
        Decide.parameters.EPSILON = Math.PI;
        Decide.parameters.DIST = 1;
    }

    @Test void testInvalidLength() {
        Decide.NUMPOINTS = 5;

        assertThrows(IllegalArgumentException.class, Decide::DECIDE);
    }

    /**
     * Since PUV is all false, FUV is all true so LAUNCH should be true
     */
    @Test void testPuvFalse() {
        Decide.DECIDE();
        assertTrue(Decide.LAUNCH);
    }

    /**
     * PUV[0] is true, but since at least one LIC is false (for example LIC 8 because it requires 5 points),
     * the PUM is not entirely true on row 0, fo FUV[0] is false
     * This means that LAUNCH should be false
     */
    @Test void testFalseCondition() {
        Decide.PUV[0] = true;

        Decide.DECIDE();
        assertFalse(Decide.LAUNCH);
    }
}
