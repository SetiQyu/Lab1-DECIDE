package app.decide.lic;
import app.decide.Decide;
import java.lang.Math; 

public class Lic0 implements ILic{
    public boolean distance_points(double[] x, double[] y, int num_points, Decide.Parameters parameters, double length1) {
        for (int i = 0; i < num_points-1; i++) {
            double distance = Math.sqrt( Math.pow(x[i+1]- x[i], 2) + Math.pow(y[i+1]- y[i], 2) );
            if(distance > length1) {
                return true;
            }
        }
        return false;
    }
}
