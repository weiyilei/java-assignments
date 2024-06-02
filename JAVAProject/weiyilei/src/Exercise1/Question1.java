package Exercise1;

import java.awt.*;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;
import java.util.Scanner;

public class Question1 {
    public static void main(String[] args) throws FileNotFoundException{
        String Filename = args[0];                 //NAFFLY.jpg in this example
        double angle = Double.parseDouble(args[1]);//for example,0.5235 means pi/6
        Picture pic = new Picture(Filename);
        int width = pic.width();
        int height = pic.height();
        Picture picture = new Picture(width,height);
        int ci = width/2;
        int cj = height/2;
        int tj,ti;
        for(int i = 0;i < width;i++){
            for(int j = 0;j < height;j++){
                Color color = pic.get(i,j);
                tj = (int)Math.round(-(i - ci)*Math.sin(angle) + (j - cj)*Math.cos(angle) + ci);
                ti = (int)Math.round((i - ci)*Math.cos(angle) + (j - cj)*Math.sin(angle) + cj);
                if(tj < height && tj >= 0 && ti < width && ti >= 0){
                    picture.set(ti,tj,color);
                }
            }
        }
        picture.show();
    }
}