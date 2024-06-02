import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class E7_23andE7_24 {
    public static void main(String[] args){
        Pair<Rectangle> pair = new Pair<>();
        pair.setX(new Rectangle(0,0,10,10));
        pair.setY(new Rectangle(10,10,10,10));
        Pair<Rectangle> pair2 = pair.clone();
        System.out.println(pair2.getX().height);
        System.out.println(pair2.getY().x);
        try(FileOutputStream fos = new FileOutputStream("output.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(pair);
            oos.writeObject(pair2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static class Pair<E> implements Cloneable, Serializable {
        private E x;
        private E y;
        public void setX(E x){this.x = x;}
        public void setY(E y){this.y = y;}
        public E getX(){return this.x;}
        public E getY(){return this.y;}

        @Override
        public Pair<E> clone() {
            try {
                Pair clone = (Pair) super.clone();
                // TODO: copy mutable state here, so the clone can't change the internals of the original
                clone.x = this.x;
                clone.y = this.y;
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }
}
