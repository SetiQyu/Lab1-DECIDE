package app.decide;

public class Fuv {
    
    /** 
    * Generates Final Unlocking Vector (FUV)
    *
    * @param PUM 15x15 boolean matrix
    * @param PUV 15 element long boolean array
    * @param dimension an integer that should be 15
    * @return FUV 15 element long boolean array
    */
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
