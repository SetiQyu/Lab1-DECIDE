package app.decide.lic;

import app.decide.Decide;

import java.util.Arrays;

public class Lic5 implements ILic{
    @Override
    public boolean condition(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        for (int i = 0; i < num_points-1; i++) {
            if(x[i+1] - x[i] < 0) {
                return true;
            }
        }
        return false;
    }
}
