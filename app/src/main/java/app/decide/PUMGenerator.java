package app.decide;

import app.decide.Decide.CONNECTORS;


public class PUMGenerator {

    private static final int REQUIRED_LENGTH = 15;

    /**
     * Generates the Preliminary Unlocking Matrix (PUM).
     *
     * @param LCM A 15x15 matrix.
     * @param CMV A boolean array indicating which LICs are satisfied (true) or not (false).
     * @return A 15x15 symmetric boolean matrix.
     * @throws IllegalArgumentException If the dimensions of the LCM or CMV do not match the required length.
     */
    public static boolean[][] generatePUM(CONNECTORS[][] LCM, boolean[] CMV) {
        // Validate input dimensions
        if (LCM.length != REQUIRED_LENGTH || LCM[0].length != REQUIRED_LENGTH || CMV.length != REQUIRED_LENGTH) {
            throw new IllegalArgumentException("Input lengths do not match the required length of " + REQUIRED_LENGTH);
        }
        
        boolean[][] pum = new boolean[REQUIRED_LENGTH][REQUIRED_LENGTH];
        for (int i = 0; i < REQUIRED_LENGTH; ++i) {
            pum[i][i] = true;
            for (int j = i; j < REQUIRED_LENGTH; ++j) {
                pum[i][j] = pum[j][i] = findValue(CMV[i], CMV[j], LCM[i][j]);
            }
        }

        return pum; 
    }

    /**
     * Evaluates the logical relationship between two LIC results based on the connector.
     *
     * @param i The result of the first LIC .
     * @param j The result of the second LIC .
     * @param op The logical connector specifying how to combine the two results (ANDD, ORR, NOTUSED).
     * @return The boolean result of the logical operation.
     */
    private static boolean findValue(boolean i, boolean j, CONNECTORS op) {
        return switch (op) {
            case ANDD -> i && j;
            case ORR -> i || j;
            case NOTUSED -> true;
        };
    }
}
