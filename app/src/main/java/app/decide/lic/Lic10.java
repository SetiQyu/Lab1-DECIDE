package app.decide.lic;
import app.decide.Decide.*;
import app.decide.lic.ILic;

public class Lic10 implements ILic{ 
    @Override
    public boolean condition(double[] x, double[] y, int num_points, Parameters parameters){
        // Pre conditions
        if(num_points < 5) return false; 
        if(parameters.E_PTS < 1 || parameters.F_PTS < 1) return false;
        if(parameters.E_PTS + parameters.F_PTS > (num_points - 3)) return false; 
        if((x.length != y.length) || (num_points != y.length)){
            throw new IllegalArgumentException("The length of the inputs do not match");
        }

        int E_index;
        int F_index;
        double area = 0; 
        for(int i = 0; i < num_points; i++){
            E_index = (i + parameters.E_PTS + 1) % num_points;
            F_index = (E_index + parameters.F_PTS + 1) % num_points;
            area = calcArea(x[i], y[i], x[E_index], y[E_index], x[F_index], y[F_index]);
            if(area > parameters.AREA1){
                return true;
            }
        }
        return false;
    }
    /**
     * Calculates the Area of the triangle formed by the points (ax, ay), (bx, by) and (cx, cy).
     * @param ax - First x-coordinate x_1. 
     * @param ay - First y-coordinate y_1.
     * @param bx - Second x-coordinate x_2.
     * @param by - Second y-coordinate y_2.
     * @param cx - Third x-coordinate x_3.
     * @param cy - Third y-coordinate y_3.
     * @return The area of the triangle.
     */
    public double calcArea(double ax, double ay, double bx, double by, double cx, double cy){
        return (0.5)*(Math.abs(
            ax*(by - cy) + 
            bx*(cy - ay) + 
            cx*(ay - by))
            ); // Based on the formula (1/2)*|x_1(y_2 - y_3) + x_2(y_3 - y_1) + x_3(y_1 - y_2)|
    }
    
}
