package app.decide.lic;

import app.decide.Decide;

public class Lic8 implements ILic{
    static double radius(double x1, double y1, double x2, double y2, double x3, double y3) {
        // Formula from https://math.stackexchange.com/a/2836488

        double dx2 = x2 - x1;
        double dy2 = y2 - y1;
        double dx3 = x3 - x1;
        double dy3 = y3 - y1;

        double numerator1 = dx2*dx2*dy3 - dx3*dx3*dy2 + dy2*dy2*dy3 - dy3*dy3*dy2;
        double numerator2 = dx2*dx2*dx3 - dx3*dx3*dx2 + dy2*dy2*dx3 - dy3*dy3*dx2;

        double denominator = 2 * (dx2*dy3 - dx3*dy2);

        return Math.sqrt(Math.pow(numerator1/denominator, 2) + Math.pow(numerator2/denominator, 2));
    }

    @Override
    public boolean condition(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        if(num_points < 5) {
            return false;
        }
        for(int i = 0; i < num_points - parameters.A_PTS - parameters.B_PTS - 2; i++) {
            double x1 = x[i];
            double y1 = y[i];
            double x2 = x[i+parameters.A_PTS+1];
            double y2 = y[i+parameters.A_PTS+1];
            double x3 = x[i+parameters.A_PTS+parameters.B_PTS+2];
            double y3 = y[i+parameters.A_PTS+parameters.B_PTS+2];
            if(radius(x1, y1, x2, y2, x3, y3) > parameters.RADIUS1) {
                return true;
            }
        }
        return false;
    }
}
