package DSAA;

import java.util.Scanner;
public class Main15 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int n;
        int i,j,max,k,im1,im,cha;

        for(i = 1;i <= t;i++){
            n = in.nextInt();
            int[] array = new int[n];
            for(j = 0;j < n;j++){
                array[j] = in.nextInt();
            }
            im = 0;
            im1 = 1;
            max = -1;
            while(im1 < n){
                if(array[im1] <= array[im]){
                    cha = array[im] - array[im1];
                    if(cha > max){
                        max = cha;
                    }
                    im1 = im1 + 1;
                }else if(array[im1] > array[im]){
                    im = im1;
                    im1 = im1 + 1;
                }
            }
            System.out.println(max);
        }
    }
}
