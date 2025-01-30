package app.decide.lic;
import app.decide.Decide;
import java.lang.Math; 

public class Lic3 implements ILic{

    @Override
    public boolean condition(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        return triangle_area(x, y, num_points, parameters);
    }

    public boolean triangle_area(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        for (int i = 0; i < num_points - 2; i++) {
            double x1 = x[i], y1 = y[i];
            double x2 = x[i+1], y2 = y[i+1];
            double x3 = x[i+2], y3 = y[i+2];

            double side_a = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            double side_b = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
            double side_c = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));

            double s = (side_a + side_b + side_c) / 2.0;

            double area = Math.sqrt(s * (s - side_a) * (s - side_b) * (s - side_c));
            
            if (area > parameters.AREA1) return true;
        }
        return false;
    }
}
