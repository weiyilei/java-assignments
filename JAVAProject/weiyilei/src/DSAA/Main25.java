package DSAA;

import java.util.Scanner;
public class Main25 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int i,n,j,ans;

        for(i = 1;i <= t;i++){
            n = in.nextInt();
            if( (n == 1) || (n == 2) || (n == 3) ){
                System.out.println(1);
            }
            else if( (n == 4) || (n == 5) ){
                System.out.println(3);
            }
            else{
                if( (n/2)%2 == 0){
                    int[] array = new int[(n/2)/2];
                    array[0] = 1;
                    array[1] = 3;
                    for(j = 2;j <= (n/2)/2 - 1;j++){
                        if(j % 2 == 0){
                            array[j] = array[j/2 - 1]*2 + array[j/2];
                        }
                        else{
                            array[j] = array[j/2 - 1] + array[j/2]*2;
                        }
                    }
                    ans = array[(n/2)/2 - 1]*2 + array[(n/2)/2 - 2];
                }
                else{
                    int[] array = new int[(n/2)/2 + 1];
                    array[0] = 1;
                    array[1] = 3;
                    for(j = 2;j <= (n/2)/2;j++){
                        if(j % 2 == 0){
                            array[j] = array[j/2 - 1]*2 + array[j/2];
                        }
                        else{
                            array[j] = array[j/2 - 1] + array[j/2]*2;
                        }
                    }
                    ans = array[(n/2)/2 - 1]*2 + array[(n/2)/2];
                }
                System.out.println(ans);
            }

        }
    }
}
