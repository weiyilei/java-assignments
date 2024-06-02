package DSAA;

import java.util.Scanner;
public class Main13 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        int n,m;
        String result = "";

        for(int i = 1;i <= t;i++){
            n = input.nextInt();
            m = input.nextInt();
            if((n == 1) && (m == 1)){
                result = result + "Bob\n";
            }else{
                result = result + "Alice\n";
            }
        }
        System.out.print(result);
    }
}
