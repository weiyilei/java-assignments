package Exercise1;

import java.io.FileNotFoundException;

public class Question3 {
    public static void main(String[] args)throws FileNotFoundException {
        String Filename = args[0];                 //NAFFLY.jpg
        Picture pic = new Picture(Filename);
        int width = pic.width();
        int height = pic.height();
        Picture picture = new Picture(width,height);
        int tj,ti;
        for(int i = 0;i < width;i++){
            for(int j = 0;j < height;j++){
                tj = -1;
                ti = -1;
                while(ti < 0 || ti >= width || tj < 0 || tj >= height){
                    ti = i + (int)(Math.random()*11-5);
                    tj = j + (int)(Math.random()*11-5);
                }
                picture.set(i,j,pic.get(ti,tj));
            }
        }
        picture.show();
    }
}
