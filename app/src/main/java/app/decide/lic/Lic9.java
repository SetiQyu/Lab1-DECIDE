package app.decide.lic;

import app.decide.Decide;

public class Lic9 implements ILic{
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
        if(num_points < 5) {
            return false;
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

