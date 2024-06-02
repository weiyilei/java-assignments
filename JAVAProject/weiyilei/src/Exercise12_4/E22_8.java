package Exercise12_4;

import java.io.*;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class E22_8 {

    public static class Count{
        int count;
        Count(int count){
            this.count = count;
        }
        public void add(int number){
            this.count += number;
        }
    }

    public static class MyRunnable implements Runnable{
        File MyFile;
        Count count;
        MyRunnable(File file, Count count){
            this.MyFile = file;
            this.count = count;
        }
        @Override
        public synchronized void run() {
            try(FileInputStream fis = new FileInputStream(this.MyFile);
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr)){
                String line;
                int count = 0;
                while((line = br.readLine()) != null){
                    count += line.split(" ",-1).length;
                }
                System.out.println(this.MyFile.getName() + ": " + count);
                this.count.add(count);
                if(Thread.activeCount() == 3){
                    System.out.println(this.count.count);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        Path pathMovies = Paths.get("movies.txt");
        Path pathWords = Paths.get("words.txt");
        File fileMovies = new File(String.valueOf(pathMovies));
        File fileWords = new File(String.valueOf(pathWords));
        Count count = new Count(0);
        Runnable r1 = new MyRunnable(fileMovies, count);
        Runnable r2 = new MyRunnable(fileWords, count);
        Thread t1 = new Thread(r1);
        t1.start();
        Thread t2 = new Thread(r2);
        t2.start();
    }
}
