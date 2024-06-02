package Exercise6;

import java.util.regex.Pattern;

public class Exercise6 {
    public static void main(String[] args){
        System.out.println(judgeRe6("ab"));
        System.out.println(judgeRe6("a*b+"));
        System.out.println(judgeRe6("aa[ab]"));
        System.out.println(judgeRe6("a(b*)"));
        System.out.println(judgeRe6("a(a(b*)b*)"));
        System.out.println(judgeRe6("11"));
        System.out.println(judgeRe6("010"));
        System.out.println(judgeRe6("001"));
        System.out.println(judgeRe6("00100"));
        System.out.println(judgeRe6("0010"));
        System.out.println(judgeRe6("0101"));
    }

    public static boolean judgeRe1(String str){
        Pattern patternRe1 = Pattern.compile("^1$|^0{1,3}$|^01$|^10$|^101$|^011$|^110$|^001$|^010$|^100$|^[01]{4,}$");
        return patternRe1.matcher(str).matches();
    }
    public static boolean judgeRe2(String str){
        Pattern patternRe2 = Pattern.compile("^(10|11)+1?$");
        return patternRe2.matcher(str).find();
    }
    public static boolean judgeRe3(String str){
        Pattern patternRe3 = Pattern.compile("^(0{2,}10*|0*10{2,}|01?0+|0+1?0)$");
        return patternRe3.matcher(str).find();
    }
    public static boolean judgeRe4(String str){
        Pattern patternRe4 = Pattern.compile("^(?!.*11)[01]+");
        return patternRe4.matcher(str).find();
    }
    public static boolean judgeRe6(String str){
        Pattern patternRe6 = Pattern.compile("^([^c-zA-Z()]*(\\([^c-zA-Z()]+\\))*[^c-zA-Z()]*)+$");
        return patternRe6.matcher(str).find();
    }
}
