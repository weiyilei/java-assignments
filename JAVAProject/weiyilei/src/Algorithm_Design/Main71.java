package Algorithm_Design;

import java.util.Scanner;
public class Main71 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long m = in.nextLong();
        String a;
        long b,x = 0,y = 1;
        int i;
        for(i = 0;i < n;i++){
            a = in.next();
            b = in.nextLong();
            if(a.equals("AND")){
                x = x & b;
                y = y & b;
            }
            else if(a.equals("OR")){
                x = x | b;
                y = y | b;
            }
            else if(a.equals("XOR")){
                x = x ^ b;
                y = y ^ b;
            }
        }
        if(x > y){
            System.out.println(x);
        }
        else{
            System.out.println(y);
        }
    }
}
