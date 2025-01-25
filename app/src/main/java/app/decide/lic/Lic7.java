package app.decide.lic;

import app.decide.Decide;

import static java.awt.geom.Point2D.distance;

public class Lic7 implements ILic {

    @Override
    public boolean condition(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        if (num_points < 3) {
            return false;
        }
        for (int i = 0; i < num_points - parameters.K_PTS - 1; i++) {
            if (distance(x[i], y[i], x[i + parameters.K_PTS + 1], y[i + parameters.K_PTS + 1]) > parameters.LENGTH1) {
                return true;
            }
        }
        return false;
    }
}
