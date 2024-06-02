//
//   Practice1.java
//
//   You should use this program for testing your Translit class.
//   To run it in a console:
//      $ java Practice1 <name of the file to convert>
//
//   To run it from Eclipse you need first to go to
//       Run/Run Configurations...
//   then click on the tab "(x)= Arguments" and enter the full access
//   path to the file in the "Program arguments:" entry field.
//

import javafx.scene.chart.ScatterChart;

import java.io.*;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Define class Translit here.
// You can also define it as a public class in a separate file named
// Translit.java

class Translit{
    Map<String,String> map = new LinkedHashMap<>();
    File file = new File("C:\\Users\\86177\\Downloads\\Practice1\\translit_table.txt");
    String string = new String("");
    public String convert(String str){
        try(FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis,"utf-8");
            BufferedReader br = new BufferedReader(isr)
            ){
            String[] arrays;
            String[] arrays1;
            String line;
            int index1a,index1b,index2a,index2b,index3a,index3b;
            String str1,str2,str3;
            while((line = br.readLine()) != null){
                arrays = line.split(",");
                index1a = arrays[0].indexOf("'");
                index1b = arrays[0].lastIndexOf("'");
                str1 = arrays[0].substring(index1a + 1,index1b);
                index2a = arrays[1].indexOf("'");
                index2b = arrays[1].lastIndexOf("'");
                str2 = arrays[1].substring(index2a + 1,index2b);
                index3a = arrays[2].indexOf('"');
                index3b = arrays[2].lastIndexOf('"');
                str3 = arrays[2].substring(index3a + 1,index3b);
                map.put(str1,str3);
                map.put(str2,str3);
            }
            //Pattern patPunc = Pattern.compile("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]$");
            arrays = str.split("\n");
            for(int i = 0;i < arrays.length;i++){
                arrays1 = arrays[i].split(" ");
                for(int j = 0;j < arrays1.length;j++){
                    for(int k = 0;k < arrays1[j].length();k++){
                        //Matcher matcher = patPunc.matcher(String.valueOf(arrays1[j].charAt(k)));
                        if(arrays1[j].charAt(k) >= 'А' && arrays1[j].charAt(k) <= 'я'){
                            string = string + map.get(String.valueOf(arrays1[j].charAt(k)));
                        }
                        else{
                            string = string + arrays1[j].charAt(k);
                        }
                    }
                    string = string + " ";
                }
                string = string + "\n";
            }
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
}
public static class Practice1 {

    static String fileContent = new String("");

    // This method reads the contents of a file into a String.
    // It specifies that the characters in the file are encoded
    // with the UTF-8 encoding scheme (this is the standard on the Web
    // and on Linux machines; Windows machines use a different default
    // encoding scheme)
    // We will see files in detail later in the course.
    private static void readFile(String fileName){
        File file1 = new File(fileName);
        try{
            FileInputStream fis=new FileInputStream(file1);
            InputStreamReader isr=new InputStreamReader(fis,"utf-8");
            BufferedReader br=new BufferedReader(isr);
            String line;
            while((line = br.readLine()) != null){
                fileContent = fileContent + line;
                fileContent = fileContent + "\n";
            }
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // The program takes the name of the file from the command line.
        // Wen it runs, it finds command line parameters into the args array.
        if (args.length > 0) {
            try {
                // Load the content of the file in memory
                readFile(args[0]);
                // Display what has been read for control.
                System.out.println("Input:");
                System.out.println(fileContent);
                // Create a Translit object
                Translit tr = new Translit();
                // Convert and display. It will all be in lowercase.
                System.out.println("Output:");
                System.out.println(tr.convert(fileContent));
            } catch (Exception e) {
                // If anything goes wrong
                e.printStackTrace();
            }
        }
    }
    }
}

