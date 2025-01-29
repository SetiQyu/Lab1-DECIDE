package app.decide.lic;

import app.decide.Decide.*;
import static java.awt.geom.Point2D.distance; // 

public class Lic12 implements ILic{
    @Override
    public boolean condition(double[] x, double[] y, int num_points, Parameters parameters){
        // Pre-conditions
        if(num_points < 3) return false;
        if(parameters.LENGTH2 < 0) return false;
        if((x.length != y.length) || (num_points != y.length)){
            throw new IllegalArgumentException("The length of the inputs do not match");
        } 
        
        // Local variables
        int compare_index;
        int max_index = num_points - parameters.K_PTS - 2;
        boolean l1_found = false;
        boolean l2_found = false;
        // Check if condition for LIC12 is met
        for(int i = 0; i <= max_index; i++){
            compare_index = i + parameters.K_PTS + 1;
            if(distance(x[i], y[i], x[compare_index], y[compare_index]) > parameters.LENGTH1){
                l1_found = true;
            }
            if(distance(x[i], y[i], x[compare_index], y[compare_index]) < parameters.LENGTH2){
                l2_found = true; 
            }
            if(l1_found && l2_found) return true;
        }
        return false; 
    }

}
