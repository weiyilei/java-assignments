package DSAA;

import java.util.Scanner;
public class Main11 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int a;
        int b;
        int c;
        int t = input.nextInt();
        int n;
        String result = "";

        for(int i = 1;i <= t;i++){
            a = input.nextInt();
            b = input.nextInt();
            c = a^b;
            n = input.nextInt();
            if(n % 3 == 0){
                result = result + a + "\n";
            }else if(n % 3 == 1){
                result = result + b + "\n";
            }else{
                result = result + c + "\n";
            }
        }
        System.out.print(result);
    }
}