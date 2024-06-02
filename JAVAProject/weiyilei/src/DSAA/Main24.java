package DSAA;

import java.util.Scanner;
public class Main24 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int l = in.nextInt();
        int a,b,p,q,mid;
        int i;
        int[] d = new int[n];
        int sum = 0;
        int result = 0;
        int max = -1;

        a = in.nextInt();
        for(i = 0;i < n - 1;i++){
            b = in.nextInt();
            d[i] = b - a;
            if(d[i] > max){
                max = d[i];
            }
            sum = sum + d[i];
            a = b;
        }
        d[n - 1] = l - a;
        if(d[n - 1] > max){
            max = d[n - 1];
        }
        sum = sum + d[n - 1];
        p = max;
        q = sum;
        while(p <= q){
            mid = (p + q)/2;
            if(judge(mid,n,m,d) == true){
                result = mid;
                q = mid - 1;
            }
            else{
                p = mid + 1;
            }
        }
        System.out.println(result);
    }
    static boolean judge(int x, int n, int m, int[] array){
        int ddl = 0;
        int sum = 0;
        for(int i = 0;i < n;i++){
            if(sum + array[i] > x){
                ddl++;
                sum = array[i];
            }
            else{
                sum = sum + array[i];
            }
        }
        if(ddl >= m){
            return false;
        }
        else{
            return true;
        }
    }
}
