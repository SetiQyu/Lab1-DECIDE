package app.decide;

import app.decide.lic.*;
public class Decide {
    // Enum for logical connectors
    public enum CONNECTORS { 
        NOTUSED, ORR, ANDD
    }

    // Inner class for Parameters
    public class Parameters {
        public double LENGTH1, RADIUS1, EPSILON, AREA1, DIST, LENGTH2, RADIUS2, AREA2;
        public int Q_PTS, QUADS, N_PTS, K_PTS, A_PTS, B_PTS, C_PTS, D_PTS, E_PTS, F_PTS, G_PTS;

        public Parameters(double[] p1, int[] p2) {
            this.LENGTH1 = p1[0]; this.RADIUS1 = p1[1]; this.EPSILON = p1[2]; this.AREA1 = p1[3];
            this.DIST = p1[4]; this.LENGTH2 = p1[5]; this.RADIUS2 = p1[6]; this.AREA2 = p1[7];

            this.Q_PTS = p2[0]; this.QUADS = p2[1]; this.N_PTS = p2[2]; this.K_PTS = p2[3];
            this.A_PTS = p2[4]; this.B_PTS = p2[5]; this.C_PTS = p2[6]; this.D_PTS = p2[7];
            this.E_PTS = p2[8]; this.F_PTS = p2[9]; this.G_PTS = p2[10];
        }
    }

    // Global variables
    public static double[] X, Y;
    public static int NUMPOINTS;
    public static CONNECTORS[][] LCM;
    public static boolean[][] PUM;
    public static boolean[] PUV;
    public static boolean[] CMV;
    public static boolean[] FUV;
    public static Parameters parameters;
    public static boolean LAUNCH;

    // Compute CMV, PUM, and FUV and determine final decision based on FUV
    public static void DECIDE() {
        // Step 1: Compute CMV (Conditions Met Vector)
        CMV = new boolean[15];
        // Uncomment when all LIC classes are available
        CMV[0] = new Lic0().condition(X, Y, NUMPOINTS, parameters);
        CMV[1] = new Lic1().condition(X, Y, NUMPOINTS, parameters);
        CMV[2] = new Lic2().condition(X, Y, NUMPOINTS, parameters);
        CMV[3] = new Lic3().condition(X, Y, NUMPOINTS, parameters);
        CMV[4] = new Lic4().condition(X, Y, NUMPOINTS, parameters);
        CMV[5] = new Lic5().condition(X, Y, NUMPOINTS, parameters);
        CMV[6] = new Lic6().condition(X, Y, NUMPOINTS, parameters);
        CMV[7] = new Lic7().condition(X, Y, NUMPOINTS, parameters);
        CMV[8] = new Lic8().condition(X, Y, NUMPOINTS, parameters);
        CMV[9] = new Lic9().condition(X, Y, NUMPOINTS, parameters);
        CMV[10] = new Lic10().condition(X, Y, NUMPOINTS, parameters);
        CMV[11] = new Lic11().condition(X, Y, NUMPOINTS, parameters);
        CMV[12] = new Lic12().condition(X, Y, NUMPOINTS, parameters);
        CMV[13] = new Lic13().condition(X, Y, NUMPOINTS, parameters);
        CMV[14] = new Lic14().condition(X, Y, NUMPOINTS, parameters);

        // Step 2: Compute PUM using PUMGenerator
        PUM = PUMGenerator.generatePUM(LCM, CMV);

        // Step 3: Compute FUV using Fuv.getFUV
        FUV = Fuv.getFUV(PUM, PUV, 15);

        LAUNCH = true;
        for (boolean fuvValue : FUV) {
            if (!fuvValue) {
                LAUNCH = false;
                break; // Exit early if any condition is false
            }
        }
    }

    public static void main(String[] args) {
        // Compute and decide launch
        DECIDE();
        // Print final decision
        System.out.println("Launch Decision: " + LAUNCH);
    }
}
