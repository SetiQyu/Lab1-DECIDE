package app.decide.lic;

import app.decide.Decide;

import static java.lang.Math.*;

public class Lic6 implements ILic {
    /**
     * Computes distance from {@code point} to the line goint through {@code start} and {@code end}
     * @param start_x x coordinate of start point
     * @param start_y y coordinate of start point
     * @param end_x x coordinate of end point
     * @param end_y y coordinate of end point
     * @param point_x x coordinate of point
     * @param point_y y coordinate of point
     * @return the distance
     */
    static double distance(double start_x, double start_y, double end_x, double end_y, double point_x, double point_y) {
        var numerator = abs((end_y - start_y) * point_x - (end_x - start_x) * point_y + end_x * start_y - end_y * start_x);
        var denominator = sqrt(pow(end_y - start_y, 2) + pow(end_x - start_x, 2));
        return numerator / denominator;
    }

    @Override
    public boolean condition(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        if(3 > parameters.N_PTS || parameters.N_PTS > num_points || parameters.DIST < 0){
            return false;
        }
        if(x.length != y.length || num_points != y.length){
            throw new IllegalArgumentException("The length of the inputs do not match");
        }

        for (int i = 0; i < num_points - parameters.N_PTS + 1; i++) {
            for (int j = 1; j < parameters.N_PTS - 1; j++) {
                if (distance(x[i], y[i], x[i + parameters.N_PTS - 1], y[i + parameters.N_PTS - 1], x[i + j], y[i + j]) > parameters.DIST) {
                    return true;
                }
            }
        }
        return false;
    }
}
