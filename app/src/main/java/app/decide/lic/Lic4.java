package app.decide.lic;
import app.decide.Decide;
import java.lang.Math; 

public class Lic4 implements ILic{
    
    @Override
    public boolean condition(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        return in_quadrants(x, y, num_points, parameters);
    }

    public boolean in_quadrants(double[] x, double[] y, int num_points, Decide.Parameters parameters) {
        for (int i = 0; i <= num_points - parameters.Q_PTS; i++) {
            int[] quadrants = new int[4];  

            for (int j = 0; j < parameters.Q_PTS; j++) {
                int quad = quadrant(x[i + j], y[i + j]);
                quadrants[quad] = 1;  
            }

            int count = quadrants[0] + quadrants[1] + quadrants[2] + quadrants[3];
            if (count > parameters.QUADS) return true;  
        }
        return false;
    }

    private int quadrant(double x, double y) {
        if (x >= 0 && y > 0) return 0;  // quadrant 1
        if (x < 0 && y >= 0) return 1;  // quadrant 2
        if (x <= 0 && y < 0) return 2;  // quadrant 3
        return 3;  // quadrant 4
    }    
}
