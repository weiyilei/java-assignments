package DSAA;

import java.util.Scanner;
public class Main16 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int i,n,j,yh,sum,bj;

        for(i = 1;i <= t;i++){
            n = in.nextInt();
            sum = 0;
            if(n == 1){
                int[] array = new int[1];
                array[0] = in.nextInt();
                System.out.println(1);
            }else{
                int[] array = new int[n];
                for(j = 0;j < n;j++){
                    array[j] = in.nextInt();
                }
                yh = array[0];
                for(j = 1;j < n;j++){
                    yh = yh^array[j];
                }
                if(yh == 0){
                    System.out.println(0);
                }else{
                    for (j = 0; j < n; j++) {
                        bj = yh ^ array[j];
                        if (bj <= array[j]) {
                            sum = sum + 1;
                        }
                    }
                    System.out.println(sum);
                }
            }
        }
    }
}
