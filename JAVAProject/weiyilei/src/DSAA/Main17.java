package DSAA;

import java.util.Scanner;
public class Main17 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int i,s,j,sum,ind,cha,ind1,zhi;
        char n;
        String a,result;

        for(i = 1;i <= t;i++){
            a = in.next();
            s = in.nextInt();
            sum = 0;
            for(j = 0;j < a.length();j++){
                sum = sum + Integer.parseInt(String.valueOf(a.charAt(j)));
            }
            if(sum <= s){
                System.out.println(0);
            }
            else{
                result = "";
                ind = a.length() - 1;
                while(sum > s){
                    StringBuilder str = new StringBuilder(a);
                    if(str.charAt(ind) == '0'){
                        result = 0 + result;
                        ind = ind - 1;
                    }
                    else{
                        cha = 10 - Integer.parseInt(String.valueOf(str.charAt(ind)));
                        result = cha + result;
                        str.setCharAt(ind,'0');
                        ind1 = ind;
                        while(ind1 > 0){
                            if(str.charAt(ind1) == '0'){
                                if(str.charAt(ind1 - 1) == '9'){
                                    str.setCharAt(ind1 - 1,'0');
                                    ind1 = ind1 - 1;
                                }else{
                                    zhi = Integer.parseInt(String.valueOf(str.charAt(ind1 - 1))) + 1;
                                    str.setCharAt(ind1 - 1,String.valueOf(zhi).toCharArray()[0]);
                                    ind1 = ind1 - 1;
                                }
                            }else{
                                break;
                            }
                        }
                        a = str.toString();
                        if( (ind1 == 0) && (str.charAt(ind1) == '0') ){
                            a = 1 + a;
                        }
                        sum = 0;
                        for(j = 0;j < a.length();j++){
                            sum = sum + Integer.parseInt(String.valueOf(a.charAt(j)));
                        }
                        ind = ind - 1;
                    }
                }
                System.out.println(result);
            }
        }
    }
}
