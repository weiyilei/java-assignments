package DSAA;

import java.util.Scanner;
public class Main12 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        String result = "";
        int n;
        long calculate;

        for(int i = 1;i <= t;i++) {
            n = input.nextInt();
            calculate = 2;
            if(n == 1){
                result = result + 2 + "\n";
            }else{
                for(int j = 1;j <= n - 1;j++){
                    calculate = 2 + 3 * calculate;
                    calculate = calculate % 1000000007;
                }
                result = result + calculate +"\n";
            }
        }
        System.out.print(result);
    }
}
