public class Decide {
    // Type definitions 
    public class Parameters {
        public double LENGTH1, RADIUS1, EPSILON, AREA1, DIST, LENGTH2, RADIUS2, AREA2;
        public int Q_PTS, QUADS, N_PTS, K_PTS, A_PTS, B_PTS, C_PTS, D_PTS, E_PTS, F_PTS, G_PTS;
        
        // Creates a parameter instance from the lists p1 and p2. 
        public Parameters(double[] p1, int[] p2) {
            // Double-type variables
            this.LENGTH1 = p1[0]; // LENGTH1: Length in LICs 0, 7, 12
            this.RADIUS1 = p1[1]; // RADIUS1: Radius in LICs 1, 8, 13
            this.EPSILON = p1[2]; // EPSILON: Deviation from PI in LICs 2, 9
            this.AREA1 = p1[3]; // AREA1: Area in Lics 3, 10, 14
            this.DIST = p1[4]; // DIST: Distance in LIC 6
            this.LENGTH2 = p1[5]; // LENGTH2: Maximum length in LIC 12
            this.RADIUS2 = p1[6]; // RADIUS2: Maximum radius in LIC 13
            this.AREA2 = p1[7]; // AREA2:  Maximum area in LIC 14

            // Int-type variables
            this.Q_PTS = p2[0]; // Q_PTS: No. of consecutive points in LIC 4
            this.QUADS = p2[1]; // QUADS: No. of quadrants in LIC 4
            this.N_PTS = p2[2]; // N_PTS: No. of int. pts. in LICs 7, 12
            this.K_PTS = p2[3]; // K_PTS: No. of int. pts. in LICs 8, 13
            this.A_PTS = p2[4]; // A_PTS: No. of int. pts. in LICs 8, 13
            this.B_PTS = p2[5]; // B_PTS: No. of int. pts. in LICs 8, 13
            this.C_PTS = p2[6]; // C_PTS: No. of int. pts. in LIC 9
            this.D_PTS = p2[7]; // D_PTS: No. of int. pts. in LIC 9
            this.E_PTS = p2[8]; // E_PTS: No. of int. pts. in LICs 10, 14
            this.F_PTS = p2[9]; // F_PTS: No. of int. pts. in LICs 10, 14
            this.G_PTS = p2[10]; // G_PTS: No. of int. pts. in LIC 11
        }
    }
    public static enum CONNECTORS { 
        NOTUSED(777),
        ORR(778), // 778 was not specified in the assignment.
        ANDD(779); // 779 was not specified in the assignment
    
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
        EQ(1110), // 1110 was not specified in the assignment
        GT(1100); // 1100 was not specified in the assignment

        private final int compVal;

        COMPTYPE(int value){
            if(value == 1111 || value == 1110 || value == 1100){
                this.compVal = value;
            }
            else{
                throw new IllegalArgumentException("Argument \"value\" must be 1111(LT), 1110(EQ) or 1100(GT))");
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

