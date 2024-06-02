package DSAA;

import java.util.Scanner;
public class Main22 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int t = in.nextInt();
        int i,ind;
        int energy;
        int[] dif = new int[n];

        for(i = 0;i < n;i++){
            dif[i] = in.nextInt();
        }
        for(i = 1;i <= t;i++){
            energy = in.nextInt();
            ind = n - 1;
            while(ind >= 0){
                if(dif[ind] <= energy){
                    if(dif[ind] == energy){
                        System.out.println("Accept");
                    }
                    else{
                        System.out.println(energy - dif[ind]);
                    }
                    break;
                }
                ind = ind - 1;
            }
        }
    }
}
