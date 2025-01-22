public class Decide {
    // Type definitions 
    public class Parameters {
        // LENGTH1: Length in LICs 0, 7, 12
        // RADIUS1: Radius in LICs 1, 8, 13
        // EPSILON: Deviation from PI in LICs 2, 9
        // AREA1: Area in Lics 3, 10, 14
        // Q_PTS: No. of consecutive points in LIC 4
        // QUADS: No. of quadrants in LIC 4
        // DIST: Distance in LIC 6
        // N_PTS: No. of int. pts. in LICs 7, 12
        // K_PTS: No. of int. pts. in LICs 8, 13
        // B_PTS: No. of int. pts. in LICs 8, 13
        // C_PTS: No. of int. pts. in LIC 9
        // D_PTS: No. of int. pts. in LIC 9
        // E_PTS: No. of int. pts. in LICs 10, 14
        // F_PTS: No. of int. pts. in LICs 10, 14
        // G_PTS: No. of int. pts. in LIC 11
        // LENGTH2: Maximum length in LIC 12
        // RADIUS2: Maximum radius in LIC 13
        // AREA2:  Maximum area in LIC 14
        double LENGTH1, RADIUS1, EPSILON, AREA1, DIST, LENGTH2, RADIUS2, AREA2;
        int Q_PTS, QUADS, N_PTS, K_PTS, A_PTS, B_PTS, C_PTS, D_PTS, E_PTS, F_PTS, G_PTS;
        
        public Parameters(double[] p1, int[] p2) {
            // Double typed variables
            this.LENGTH1 = p1[0];
            this.RADIUS1 = p1[1];
            this.EPSILON = p1[2];
            this.AREA1 = p1[3];
            this.DIST = p1[4];
            this.LENGTH2 = p1[5];
            this.RADIUS2 = p1[6];

            // Int type variables
            this.Q_PTS = p2[0];
            this.QUADS = p2[1];
            this.N_PTS = p2[2];
            this.K_PTS = p2[3];
            this.A_PTS = p2[4];
            this.B_PTS = p2[5];
            this.C_PTS = p2[6];
            this.D_PTS = p2[7];
            this.E_PTS = p2[8];
            this.G_PTS = p2[9];
        }
    }
    public enum CONNECTORS { 
        NOTUSED(777),
        ORR(778),
        ANDD(779);
    
        private final int val;
        CONNECTORS(int value){
            this.val = value;
        }
    
        public int getValue() {
            return val;
        }
    }

    
    public static enum COMPTYPE {
        LT(1111),
        EQ(1110),
        GT(1100);

        private final int compVal;

        COMPTYPE(int value){
            if(value == 1111 || value == 1110 || value == 1100){
                this.compVal = value;
            }
            else{
                throw new IllegalArgumentException("Argument \"value\" must be 1111, 1110 or 1100)");
            }
        }

        public int getValue(){
            return compVal; 
        }

    }

    // Global variables

        private static final double PI = 3.14159;

        // X coordinates of data points
        private double[] X;
        private static double[] X2;

        // Y coordinates of data points
        private double[] Y;
        private static double[] Y2;

        // Number of data points
        private int NUMPOINTS;
        private static int NUMPOINTS2;

        // Logical Connector Matrix
        private CONNECTORS[][] LCM;
        private static CONNECTORS[][] LCM2;

        // Preliminary Unlocking Matrix;
        private boolean[][] PUM;
        private static boolean[][] PUM2;

        // Conditions Met Vector
        private boolean[] CMV;
        private static boolean[] CMV2;

        // Final Unlocking Vector
        private boolean[] FUV;
        private static boolean[] FUV2;

        // Decision: Launch or No Launch
        private boolean LAUNCH;
        private static boolean LAUNCH2;

    public static COMPTYPE DOUBLECOMPARE(double A, double B){
        if(Math.abs(A - B) <  0.000001) return COMPTYPE.EQ;
        if (A < B) return COMPTYPE.LT;
        return COMPTYPE.GT;
    }
    
    public static String writeOut(){ 
        return "Test print";
    } 

    void DECIDE(){

    }
    
    public static void main(String[] args) {
        System.out.println(writeOut());
    }
    
}

