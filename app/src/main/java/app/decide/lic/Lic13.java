package app.decide.lic;

import static java.awt.geom.Point2D.distance;
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
        double ax, ay;
        double bx, by;
        double cx, cy;  
        int a_index;
        int b_index;
        int max_index = num_points - parameters.A_PTS - parameters.B_PTS - 3;//  num_points - 1 -(A_PTS + 1 + B_pts + 1)
        for(int i = 0; i <= max_index; i++){
            a_index = i + parameters.A_PTS + 1;
            b_index = a_index + parameters.B_PTS + 1; 
            ax = x[i]; ay = y[i];
            bx = x[a_index]; by = y[a_index];
            cx = x[b_index]; cy = y[b_index];
            if(!found_r1 && !containedInCircle(ax, ay, bx, by, cx, cy, parameters.RADIUS1)){
                found_r1 = true;
            }
            if(!found_r2 && containedInCircle(ax, ay, bx, by, cx, cy, parameters.RADIUS2)){
                found_r2 = true; 
            }
            if(found_r1 && found_r2){
                return true; 
            }
        }
        return false; 
    }

    /**
     * Checks if the triangle formed by the points (ax, ay), (bx, by) and (cx, cy) can be contained within a circle with a given radius. 
     * @param ax - First x-coordinate x_1. 
     * @param ay - First y-coordinate y_1.
     * @param bx - Second x-coordinate x_2.
     * @param by - Second y-coordinate y_2.
     * @param cx - Third x-coordinate x_3.
     * @param cy - Third y-coordinate y_3.
     * @param radius - The radius of the circle that should contain the points. 
     * @return True if a circle with the given radius can contain points (ax, ay), (bx, by) and (cx, cy). 
     */
    public boolean containedInCircle(double ax, double ay, double bx, double by, double cx, double cy, double radius){
        double area = (0.5)*(Math.abs(
            ax*(by - cy) + 
            bx*(cy - ay) + 
            cx*(ay - by))
            ); // Based on the formula (1/2)*|x_1(y_2 - y_3) + x_2(y_3 - y_1) + x_3(y_1 - y_2)|
        // Check if points are collinear.
        if(area == 0){
            return containedInCircleCollinear(ax, ay, bx, by, cx, cy, radius);
        }
        // Calculate side lengths 
        double ab = distance(ax, ay, bx, by);
        double ac = distance(ax, ay, cx, cy);
        double bc = distance(bx, by, cx, cy);
        // Using the formula for Circumradius R = abc/4A which is the radius of the circle that has all 3 vertices of the triangle on the circle. 
        double circ_rad = (ab * ac * bc)/(4 * area);
        if(circ_rad <= radius){
            return true; 
        }
        return false; 
    }

    /**
     * If the line formed by points (ax, ay), (bx, by) and (cx, cy) can be contained by a circle with the given radius. 
     * @param ax - First x-coordinate x_1. 
     * @param ay - First y-coordinate y_1.
     * @param bx - Second x-coordinate x_2.
     * @param by - Second y-coordinate y_2.
     * @param cx - Third x-coordinate x_3.
     * @param cy - Third y-coordinate y_3.
     * @param radius - Radius of circle
     * @return - True if the circle can contain the points (ax, ay), (bx, by) and (cx, cy). 
     */
    public boolean containedInCircleCollinear(double ax, double ay, double bx, double by, double cx, double cy, double radius){
        // distances between points
        double ab = distance(ax, ay, bx, by);
        double ac = distance(ax, ay, cx, cy);
        double bc = distance(bx, by, cx, cy);
        double max_distance = Math.max(Math.max(ab, ac), bc);
        // Check if the maximum distance on the line is larger than the diameter of the circle. 
        if(max_distance <= (2*radius)){
            return true;
        }
        return false;
    }

}
