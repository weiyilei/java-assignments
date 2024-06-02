package DSAA;

import java.util.Scanner;
public class Main23 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int s = in.nextInt();
        int i,j,k;
        long sum = 0;
        int[] array = new int[n];

        for(i = 0;i < n;i++){
            array[i] = in.nextInt();
        }
        for(i = 0;i < n - 2;i++){
            for(j = i + 1;j < n - 1;j++){
                k = n - 1;
                while(k > j){
                    if(array[i] + array[j] + array[k] > s){
                        k = k - 1;
                    }
                    else if(array[i] + array[j] + array[k] == s){
                        sum = sum + 1;
                        k = k - 1;
                    }
                    else if(array[i] + array[j] + array[k] < s){
                        break;
                    }
                }
            }
        }
        System.out.println(sum);
    }
}
