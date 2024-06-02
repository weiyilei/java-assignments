import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Practice3 {
    public static void main(String[] args){
        String regex1 = "年|月|日";
        String[] array = new String[3];
        array[0] = "2021 年 11 月 8 日星期一";
        array[1] = "2021 年 11 月 9 日星期二";
        array[2] = "2021 年 11 月 10 日星期三";
        String[] result1 = new String[4];
        for(int i = 0;i < 3;i++){
            result1 = array[i].split(regex1);
            for(int j = 0;j < 3;j++){
                System.out.println(result1[j].trim());
            }
        }
        array[0] = "11910001@mail.sustc.edu.cn";
        array[1] = "11810002@mail.sustech.edu.cn";
        array[2] = "12010003@mail.sustc.edu.cn";
        String regex2 = "^(\\d+)@mail\\.(sustc|sustech)\\.edu\\.cn$";
        Pattern pattern2 = Pattern.compile(regex2);
        for(int i = 0;i < 3;i++){
            Matcher matcher = pattern2.matcher(array[i]);
            if(matcher.find()){
                System.out.println(matcher.group(1));
            }
        }
        Pattern pattern31 = Pattern.compile("\\d");
        Pattern pattern32 = Pattern.compile("[A-Z]");
        Pattern pattern33 = Pattern.compile("[a-z]");
        Pattern pattern34 = Pattern.compile("[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\\\\]");
        array[0] = "1A11111a";
        array[1] = "123aBC.,";
        for(int i = 0;i < 2;i++){
            if(array[i].length() >= 8 && array[i].length() <= 16){
                if(pattern31.matcher(array[i]).find() &&
                pattern32.matcher(array[i]).find() &&
                pattern33.matcher(array[i]).find() &&
                pattern34.matcher(array[i]).find()){
                    System.out.println("True");
                }
                else{
                    System.out.println("False");
                }
            }
            else{
                System.out.println("False");
            }
        }
        String str = "广东省深圳市南山区学苑大道南山智园 A7 ,518055,13422221111,赵老师收";
        Pattern pattern4 = Pattern.compile(
                "(.+)省(.+)市(.+)区(.+)(大道)([^,]*),(\\d+),(\\d+),(.+)");
        Matcher matcher4 = pattern4.matcher(str);
        String street;
        if(matcher4.find()){
            System.out.println("Province:" + matcher4.group(1));
            System.out.println("City:" + matcher4.group(2));
            System.out.println("District:" + matcher4.group(3));
            System.out.println("Street:" +matcher4.group(4) + matcher4.group(5));
            System.out.println("Zip code:" + matcher4.group(7));
            System.out.println("Telephone number:" + matcher4.group(8));
        }
    }
}