package app.decide.lic;
import app.decide.Decide;
import java.lang.Math; 



public class Lic2 implements ILic{

    @Override
    public boolean condition(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        return Angle(x, y, num_points, parameters);
    }


    public boolean Angle(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        for (int i = 0; i < num_points-2; i++) {
            double side1 = Math.sqrt( Math.pow(x[i+1]- x[i], 2) + Math.pow(y[i+1]- y[i], 2) );
            double side2 = Math.sqrt( Math.pow(x[i+2]- x[i+1], 2) + Math.pow(y[i+2]- y[i+1], 2) );
            double side3 = Math.sqrt( Math.pow(x[i+2]- x[i], 2) + Math.pow(y[i+2]- y[i], 2) );
            

            double angle = Math.acos( (Math.pow(side1, 2) + Math.pow(side2, 2) - Math.pow(side3, 2)) / (2 * side1 * side2));
            
            if (angle < Math.PI - parameters.EPSILON || angle > Math.PI + parameters.EPSILON) return true;
        }
        return false;
    }
}
