package app.decide.lic;

import app.decide.Decide.Parameters;

public interface ILic {
    boolean condition(double[] x, double[] y, int num_points, Parameters parameters);
}
