package DSAA;

import java.util.Scanner;
public class Main21 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        long t = in.nextInt();
        long i,n;
        long sum;

        for(i = 1;i <= t;i++){
            n = in.nextInt();
            sum = ( ((n*(n+1)/2)%1000000007) * ((n*(n+1)/2)%1000000007) )%1000000007;
            System.out.println(sum);
        }
    }
}
