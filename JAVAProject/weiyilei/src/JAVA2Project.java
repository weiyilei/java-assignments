import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JAVA2Project {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("owid-covid-data.csv");
        File file = new File(String.valueOf(path));
        String line;
        String[] parts;
        int first = 0;
        int test;
        try(FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr)){
            while((line = br.readLine()) != null){
                test = 0;
                parts = line.split(",", -1);
                if(first < 5){
                    for(int i = 0;i < parts.length;i++){
                        if(parts[i].equals("")){
                            test++;
                        }
                    }
                    System.out.println(test);
                }
                first++;
            }
        }
    }
}
