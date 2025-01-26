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
            area = calcArea(new double[]{x[i], y[i]},new double[] {x[E_index], y[E_index]}, new double[] {x[F_index], y[F_index]});
            if(area > parameters.AREA1){
                return true;
            }
        }
        return false;
    }
    /**
     * Calculates the Area of the triangle formed by the points A, B and C.
     * @param A First point (x_1, y_1)
     * @param B Second point (x_2, y_2)
     * @param C Third point (x_3, y_3)
     * @return The area of the triangle.
     */
    public double calcArea(double[] A, double[] B, double[] C){
        return (0.5)*(Math.abs(
            A[0]*(B[1] - C[1]) + 
            B[0]*(C[1] - A[1]) + 
            C[0]*(A[1] - B[1]))
            ); // Based on the formula (1/2)*|x_1(y_2 - y_3) + x_2(y_3 - y_1) + x_3(y_1 - y_2)|
    }
    
}
