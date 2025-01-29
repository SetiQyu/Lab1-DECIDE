package app.decide.lic;
import app.decide.Decide.*;
public class Lic11 implements ILic{
    @Override
    public boolean condition(double[] x, double[] y, int num_points, Parameters parameters){
        // Pre-conditions
        if(num_points < 3) return false;
        if(parameters.G_PTS < 1 || parameters.G_PTS > num_points - 2) return false;
        if((x.length != y.length) || (num_points != y.length)){
            throw new IllegalArgumentException("The length of the inputs do not match");
        } 
        int compare_index;
        // [A, x_1,..x_G_PTS, B]
        int maximum_index = (num_points - parameters.G_PTS) - 2; // This also ensures i < compare_index
        for(int i = 0; i <= maximum_index; i++){
            compare_index = (i + parameters.G_PTS + 1);
            if((x[compare_index] - x[i] < 0)){
                return true; 
            }
        }
        return false; 
    }
}
