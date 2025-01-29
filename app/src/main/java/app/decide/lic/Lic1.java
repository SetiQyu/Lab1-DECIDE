package app.decide.lic;
import app.decide.Decide;
import java.lang.Math; 



public class Lic1 implements ILic{

    @Override
    public boolean condition(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        return in_circle(x, y, num_points, parameters);
    }

    public boolean in_circle(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        for (int i = 0; i < num_points-2; i++) {

            double ab = Math.sqrt(Math.pow(x[i+1] - x[i], 2) + Math.pow(y[i+1] - y[i], 2));
            double bc = Math.sqrt(Math.pow(x[i+2] - x[i+1], 2) + Math.pow(y[i+2] - y[i+1], 2));
            double ca = Math.sqrt(Math.pow(x[i+2] - x[i], 2) + Math.pow(y[i+2] - y[i], 2));
            
            double s = (ab + bc + ca) / 2;
            double area = Math.sqrt(s * (s - ab) * (s - bc) * (s - ca));
            double circumradius = (ab * bc * ca) / (4 * area);

            if(circumradius <= parameters.RADIUS1) {
                return true;
            }
        }
        return false;
    }
}
