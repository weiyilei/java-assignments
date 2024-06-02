package Exercise1;

import java.awt.*;
import java.io.FileNotFoundException;

public class Question2 {
    public static void main(String[] args)throws FileNotFoundException {
        String Filename = args[0];                 //NAFFLY.jpg
        Picture pic = new Picture(Filename);
        int width = pic.width();
        int height = pic.height();
        Picture picture = new Picture(width,height);
        int ci = width/2;
        int cj = height/2;
        int tj,ti;
        double angle,dis;
        Color color;
        for(int i = 0;i < width;i++){
            for(int j = 0;j < height;j++){
                color = pic.get(i,j);
                dis = Math.sqrt((i - ci)*(i - ci) + (j - cj)*(j - cj));
                angle = dis * Math.PI / 256.0;
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
