package app.decide.lic;

import app.decide.Decide.*;
public class Lic13 implements ILic{
    @Override
    public boolean condition(double[] x, double[] y, int num_points, Parameters parameters){
        // Pre conditions
        if(num_points < 5) return false;
        if(parameters.RADIUS2 <= 0) return false; 
        if(x.length != y.length || y.length != num_points){
            throw new IllegalArgumentException("Invalid length of coordinates, lengths do not match"); 
        }

        // Local variables
        boolean found_r1 = false;
        boolean found_r2 = false;
        double[] A;
        double[] B;
        double[] C;  
        int a_index;
        int b_index;
        int max_index = num_points - parameters.A_PTS - parameters.B_PTS - 3;//  num_points - 1 -(A_PTS + 1 + B_pts + 1)
        for(int i = 0; i <= max_index; i++){
            a_index = i + parameters.A_PTS + 1;
            b_index = a_index + parameters.B_PTS + 1; 
            A = new double[] {x[i], y[i]};
            B = new double[] {x[a_index], y[a_index]};
            C = new double[] {x[b_index], y[b_index]};
            if(!contained_in_circle(A, B, C, parameters.RADIUS1)){
                found_r1 = true;
            }
            if(contained_in_circle(A, B, C, parameters.RADIUS2)){
                found_r2 = true; 
            }
            if(found_r1 && found_r2){
                return true; 
            }
        }
        return false; 
    }

    /**
     * Checks if the triangle formed by the points A, B and C can be contained within a circle with a given radius. 
     * @param A - First point (x_1, y_1)
     * @param B - Second point (x_2, y_2)
     * @param C - Third point (x_3, y_3)
     * @param radius - The radius of the circle that should contain the points. 
     * @return True if a circle with the given radius can contain points A, B and C. 
     */
    public boolean contained_in_circle(double[] A, double[] B, double[] C, double radius){
        double area = (0.5)*(Math.abs(
            A[0]*(B[1] - C[1]) + 
            B[0]*(C[1] - A[1]) + 
            C[0]*(A[1] - B[1]))
            ); // Based on the formula (1/2)*|x_1(y_2 - y_3) + x_2(y_3 - y_1) + x_3(y_1 - y_2)|
        // Check if points are collinear.
        if(area == 0){
            return contained_in_circle_collinear(A, B, C, radius);
        }
        // Calculate side lengths 
        double ab = euclidian_distance(A, B);
        double ac = euclidian_distance(A, C);
        double bc = euclidian_distance(B, C);
        // Using the formula for Circumradius R = abc/4A which is the radius of the circle that has all 3 vertices of the triangle on the circle. 
        double circ_rad = (ab * ac * bc)/(4 * area);
        if(circ_rad <= radius){
            return true; 
        }
        return false; 
    }

    /**
     * If the line formed by points A, B and C can be contained by a circle with the given radius. 
     * @param A - First point (x_1, y_1)
     * @param B - Second point (x_2, y_2)
     * @param C - Third point (x_3, y_3)
     * @param radius - Radius of circle
     * @return - True if the circle can contain the points A, B and C. 
     */
    public boolean contained_in_circle_collinear(double[] A, double[] B, double[] C, double radius){
        // distances between points
        double ab = euclidian_distance(A, B);
        double ac = euclidian_distance(A, C);
        double bc = euclidian_distance(B, C);
        double max_distance = Math.max(Math.max(ab, ac), bc);
        // Check if the maximum distance on the line is larger than the diameter of the circle. 
        if(max_distance <= (2*radius)){
            return true;
        }
        return false;
    }

    /**
     * Calculates the euclidian distance between points p and q. 
     * @param p - (x_1, y_1)
     * @param q - (x_2, y_2)
     * @return The distance between the points. 
     */
    public double euclidian_distance(double[] p, double[] q){
        return Math.sqrt(
            Math.pow((p[0] - q[0]), 2) +
            Math.pow((p[1] - q[1]), 2)
        );
    }
}
