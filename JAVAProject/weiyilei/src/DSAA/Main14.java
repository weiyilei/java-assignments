package DSAA;

import java.util.Scanner;
public class Main14 {
    public static void main(String[] args){
        Scanner in= new Scanner(System.in);
        int t = in.nextInt();
        int a,b,c,m,n,j,k;

        for(int i = 1;i <= t;i++){
            a = in.nextInt();
            b = in.nextInt();
            c = in.nextInt();
            String[][] array = new String[1+2*(c+b)][1+2*(b+a)];
            for(m = 0;m < 2 * b;m++){
                for(n = 0;n < 2 * b;n++){
                    if(m + n < 2 * b){
                        array[m][n] = ".";
                    }
                }
            }
            for(m = 2*c + 1;m < 1 + 2*(c+b);m++){
                for(n = 2*a + 1;n < 1 + 2*(b+a);n++){
                    if(m + n >= 2*(a + b + c) + 1){
                        array[m][n] = ".";
                    }
                }
            }
            for(m = 1;m <= b;m++){
                for(n = b-m + 1;n <= b-m+a;n++){
                    array[2*m - 1][2*n] = ".";
                    array[2*m - 2][2*n + 1] = "-";
                }
            }
            for(m = 1;m <= b;m++){
                for(n = b-m + 1;n <= b-m+a+1;n++){
                    array[2*m - 1][2*n - 1] = "/";
                    array[2*m - 2][2*n] = "+";
                }
            }
            for(m = b+1;m <= b + c;m++){
                for(n = 1;n <= a;n++){
                    array[2*m - 1][2*n - 1] = ".";
                }
            }
            for(m = b;m <= b + c;m++){
                for(n = 1;n <= a;n++){
                    array[2*m][2*n - 1] = "-";
                }
            }
            for(m = b;m <= b + c;m++){
                for(n = 0;n <= a;n++){
                    array[2*m][2*n] = "+";
                }
            }
            for(m = b+1;m <= b + c;m++){
                for(n = 0;n <= a;n++){
                    array[2*m - 1][2*n] = "|";
                }
            }
            for(n = a + b -1;n >= a;n--){
                for(m = 1;m <= c;m++){
                    array[2* (m+a+b-1-n) ][2*n + 1] = ".";
                    array[2* (m+a+b-1-n) -1][2*n + 2] = "|";
                    array[2* (m+a+b-1-n) ][2*n + 2] = "+";
                    array[2* (m+a+b-1-n) +1][2*n + 1] = "/";
                }
            }

            for(j = 0;j < 1+2*(c+b);j++){
                for(k = 0;k < 1+2*(b+a);k++){
                    System.out.print(array[j][k]);
                }
                System.out.println("");
            }
        }
    }
}
