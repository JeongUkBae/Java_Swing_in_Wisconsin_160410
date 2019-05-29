package lotto;

import java.util.Arrays;

public class Lotto {
int[] lotto = new int[6];
    
    public void setLotto() {
        
        for(int i=0;i<lotto.length;i++){
            lotto[i] = 0;
        }
        
        for(int i=0;i<lotto.length;i++){
            int randomNum = (int) (Math.random()*45 + 1);
            
            boolean exist = false;
            
            for(int j = 0;j<lotto.length;j++){
                if(randomNum == lotto[j]){
                    exist = true;
                    break; 
                }
            }
            if(exist){ 
                
                i--;
                continue; 
            }else{
                
                lotto[i] = randomNum;
            }
            
        }
        Arrays.sort(lotto);  
        
    }
    public int[] getLotto() {
        return lotto;
    }
    
}
