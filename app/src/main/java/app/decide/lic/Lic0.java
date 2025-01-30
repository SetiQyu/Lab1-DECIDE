package app.decide.lic;
import app.decide.Decide;

public class Lic0 implements ILic{

    @Override
    public boolean condition(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        return distance_points(x, y, num_points, parameters);
    }

    public boolean distance_points(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        for (int i = 0; i < num_points-1; i++) {
            double distance = Math.sqrt( Math.pow(x[i+1]- x[i], 2) + Math.pow(y[i+1]- y[i], 2) );
            if(distance > parameters.LENGTH1) {
                return true;
            }
        }
        return false;
    }
}
