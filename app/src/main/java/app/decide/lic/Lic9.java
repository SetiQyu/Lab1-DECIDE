package app.decide.lic;

import app.decide.Decide;

public class Lic9 implements ILic{

    /**
     * Computes the value of angle 1 2 3
     * @param x1 point 1 x coordinate
     * @param y1 point 1 y coordinate
     * @param x2 point 2 x coordinate
     * @param y2 point 2 y coordinate
     * @param x3 point 3 x coordinate
     * @param y3 point 3 y coordinate
     * @return The angle in radians
     */
    static double angle(double x1, double y1, double x2, double y2, double x3, double y3) {
        double angle1 = Math.atan2(y1 - y2, x1 - x2);
        double angle2 = Math.atan2(y3 - y2, x3 - x2);
        double angle = angle2 - angle1;
        if (angle >= 0) {
            return angle;
        } else {
            return 2*Math.PI + angle;
        }
    }

    @Override
    public boolean condition(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        if(parameters.C_PTS < 1 || parameters.D_PTS < 1 || parameters.C_PTS + parameters.D_PTS > num_points - 3) {
            return false;
        }
        if((x.length != y.length) || (num_points != y.length)){
            throw new IllegalArgumentException("The length of the inputs do not match");
        }

        for(int i = 0; i < num_points - parameters.C_PTS - parameters.D_PTS - 2; i++) {
            double x1 = x[i];
            double y1 = y[i];
            double x2 = x[i+parameters.C_PTS+1];
            double y2 = y[i+parameters.C_PTS+1];
            double x3 = x[i+parameters.C_PTS+parameters.D_PTS+2];
            double y3 = y[i+parameters.C_PTS+parameters.D_PTS+2];

            double angle = angle(x1, y1, x2, y2, x3, y3);

            if(angle < Math.PI - parameters.EPSILON || angle > Math.PI + parameters.EPSILON) {
                return true;
            }
        }
        return false;
    }
}

