package Exercise2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        File file = new File(fileName);
        Map<String,String> letterToNum = new LinkedHashMap<>();
        Map<String,String> map = new LinkedHashMap<>();
        Scanner in = new Scanner(System.in);
        String key;
        letterToNum.put("A","2");
        letterToNum.put("B","2");
        letterToNum.put("C","2");
        letterToNum.put("D","3");
        letterToNum.put("E","3");
        letterToNum.put("F","3");
        letterToNum.put("G","4");
        letterToNum.put("H","4");
        letterToNum.put("I","4");
        letterToNum.put("J","5");
        letterToNum.put("K","5");
        letterToNum.put("L","5");
        letterToNum.put("M","6");
        letterToNum.put("N","6");
        letterToNum.put("O","6");
        letterToNum.put("P","7");
        letterToNum.put("Q","7");
        letterToNum.put("R","7");
        letterToNum.put("S","7");
        letterToNum.put("T","8");
        letterToNum.put("U","8");
        letterToNum.put("V","8");
        letterToNum.put("W","9");
        letterToNum.put("X","9");
        letterToNum.put("Y","9");
        letterToNum.put("Z","9");
        try(FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);){
            String line;
            String value;
            while( (line = br.readLine()) != null){
                value = "";
                for(int i = 0;i < line.length();i++){
                    value = value + letterToNum.get(String.valueOf(line.charAt(i)));
                }
                map.put(line,value);
            }
        }
        while(true){
            System.out.print("Enter a phone number(0 for exit):");
            key = in.next();
            if(key.equals("0")){
                break;
            }
            int num = 0;
            ArrayList<String> array = new ArrayList<>();
            for(String str : map.keySet()){
                if(key.equals(map.get(str))){
                    num++;
                    array.add(str);
                }
            }
            if(num == 0){
                System.out.println("no words for " + key + ".");
            }
            else{
                System.out.println("words for " + key + " :");
                System.out.println(array);
            }
        }
    }
}
