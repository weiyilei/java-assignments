package DSAA;

import java.util.Scanner;
public class Main27 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int i,n,k,m,j,a,b,mid;
        int result = 0;

        for(i = 1;i <= t;i++){
            n = in.nextInt();
            k = in.nextInt();
            m = in.nextInt();
            int[] array = new int[n];
            for(j = 0;j < n;j++){
                array[j] = in.nextInt();
            }
            a = 1;
            b = 2000000000;
            while(a < b){
                mid = (a + b)/2;
                if(judge(mid,n,k,m,array) == true){
                    result = mid;
                    a = mid + 1;
                }
                else{
                    b = mid;
                }
            }
            System.out.println(result);
        }
    }
    static boolean judge(int x,int n,int k,int m,int[] array){
        int sum = 0;
        long len = 0;
        int p = 0;
        int q = 0;
        for(p = 0;p < n;p++){
            if(array[p] >= x){
                sum = sum + 1;
            }
            if(sum == k){
                len = len + n - p;
                while(array[q] < x){
                    len = len + n - p;
                    q = q + 1;
                }
                sum = sum - 1;
                q = q + 1;
            }
        }
        if(len < m){
            return false;
        }
        else{
            return true;
        }
    }
}
