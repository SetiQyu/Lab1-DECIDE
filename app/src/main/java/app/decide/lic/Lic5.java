package app.decide.lic;

import app.decide.Decide;

public class Lic5 implements ILic{
    @Override
    public boolean condition(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        if((x.length != y.length) || (num_points != y.length)){
            throw new IllegalArgumentException("The length of the inputs do not match");
        }

        for (int i = 0; i < num_points-1; i++) {
            if(x[i+1] - x[i] < 0) {
                return true;
            }
        }
        return false;
    }
}
