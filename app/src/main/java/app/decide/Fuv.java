package app.decide;

public class Fuv {
    
    public static boolean[] getFUV(boolean[][] PUM, boolean[] PUV, int dimension){
        
        boolean[] FUV = new boolean[dimension];
        
        for(int i = 0; i < dimension; i++){
            
            if(PUV[i] == false){
                FUV[i] = true;
                continue;
            }
            
            FUV[i] = true;
            for(int j = 0; j < dimension; j++){
                if(PUM[i][j] == false){
                    FUV[i] = false;
                    break;
                }
            }
        }
        return FUV;
    }
}
