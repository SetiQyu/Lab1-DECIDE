package app.decide.lic;

import app.decide.Decide;

import static java.awt.geom.Point2D.distance;

public class Lic7 implements ILic {

    @Override
    public boolean condition(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        if(1 > parameters.K_PTS || parameters.K_PTS > num_points - 2) {
            return false;
        }
        if(x.length != y.length || num_points != y.length){
            throw new IllegalArgumentException("The length of the inputs do not match");
        }

        for (int i = 0; i < num_points - parameters.K_PTS - 1; i++) {
            if (distance(x[i], y[i], x[i + parameters.K_PTS + 1], y[i + parameters.K_PTS + 1]) > parameters.LENGTH1) {
                return true;
            }
        }
        return false;
    }
}
