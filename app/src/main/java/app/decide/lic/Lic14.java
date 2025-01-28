package app.decide.lic;

import app.decide.Decide.Parameters;;
public class Lic14 implements ILic {
    @Override
    public boolean condition(double[] x, double[] y, int num_points, Parameters parameters){
        // Pre-conditions
        if(num_points < 5) return false;
        if(parameters.AREA2 <= 0) return false;
        if(x.length != y.length || y.length != num_points){
            throw new IllegalArgumentException("Invalid length of coordinates, lengths do not match"); 
        }

        // Local variables
        boolean found_a1 = false;
        boolean found_a2 = false;
        double[] A;
        double[] B;
        double[] C;  
        int e_index;
        int f_index;
        int max_index = num_points - parameters.E_PTS - parameters.F_PTS - 3;//  num_points - 1 -(E_PTS + 1 + F_pts + 1)
        for(int i = 0; i <= max_index; i++){
            e_index = i + parameters.E_PTS + 1;
            f_index = e_index + parameters.F_PTS + 1;
            A = new double[] {x[i], y[i]};
            B = new double[] {x[e_index], y[e_index]};
            C = new double[] {x[f_index], y[f_index]};
            if(!found_a1 && check_area(A, B, C, parameters.AREA1, true)){
                found_a1 = true;
            }
            if(!found_a2 && check_area(A, B, C, parameters.AREA2, false)){
                found_a2 = true; 
            }
            if(found_a1 && found_a2){
                return true;
            }
        }
        return false; 

    }
    /** 
     * Checks if the area formed by the triangle A-B-C greater than or less than(depending on the value of variable greater_than) the given parameter area.
     * @param A First data point (x_1, y_1)
     * @param B Second data point (x_2, y_2)
     * @param C Third data point (x_3, y_3) 
     * @param area The area to compare to.
     * @param greater_than True if the compare should be greater than.
     * @return The boolean result of the compare.
     */
    public static boolean check_area(double[] A, double[] B, double[] C, double area, boolean greater_than){
        double tri_area = (0.5)*(Math.abs(
            A[0]*(B[1] - C[1]) + 
            B[0]*(C[1] - A[1]) + 
            C[0]*(A[1] - B[1]))
            ); // Based on the formula (1/2)*|x_1(y_2 - y_3) + x_2(y_3 - y_1) + x_3(y_1 - y_2)|
            if(greater_than){
                return tri_area > area;
            }
            return tri_area < area;
    }
    
    
}
