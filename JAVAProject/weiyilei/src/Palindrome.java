public class Palindrome {
    public boolean judge (String str) {
        int len = str.length();
        for(int i = 0;i < len;i++){
            if(str.charAt(i) == str.charAt(len-1-i))
                continue;
            else
                return false;
        }
        return true;
    }
}

