package DSAA;

import java.util.Scanner;
public class Main74_1 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int i,n,j;
        for(i = 1;i <= t;i++){
            n = in.nextInt();
            int[] pre = new int[n];
            int[] ino = new int[n];
            for(j = 0;j < n;j++){
                pre[j] = in.nextInt();
            }
            for(j = 0;j < n;j++){
                ino[j] = in.nextInt();
            }
            pos(pre,ino,0,0,n - 1);
            System.out.println();
        }
    }

    public static void pos(int[] pre,int[] ino,int root,int l,int r){
        if(l > r){
            return;
        }
        else{
            int m = l;
            while(m < r && ino[m]!= pre[root]){
                m++;
            }
            pos(pre,ino,root + 1,l,m - 1);
            pos(pre,ino,root + m - l + 1,m + 1,r);
            System.out.print(pre[root] + " ");
        }
    }
}
