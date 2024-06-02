import java.util.*;

public class Utils {
    public static <E> void fill (ArrayList<E> a, E value, int count) {
        for (int i = 0; i < count; i++)
            a.add( value );
    }

    public static <E, F extends E> void append (
            ArrayList<E> a, ArrayList<F> b, int count
    ) {
        for (int i = 0; i < count && i < b.size(); i++)
            a.add( b.get( i ) );
    }

    public static <E extends Comparable<? super E>> E getMax (
            ArrayList<E> a
    ) {
        E max = a.get(0);
        for (int i = 1; i < a.size(); i++)
            if (a.get( i ).compareTo( max ) > 0) max = a.get( i );
        return max;
    }

    public static <E> void fillWithDefaults (
            ArrayList<E> a, Class<? extends E> cl, int count
    ) throws InstantiationException, IllegalAccessException {
        for (int i = 0; i < count; i++)
            a.add(cl.newInstance());
    }

    public static <E> E7_23andE7_24.Pair<E> getFirstLast(ArrayList<E> a){
        E7_23andE7_24.Pair<E> pair = new E7_23andE7_24.Pair<>();
        pair.setX(a.get(0));
        pair.setY(a.get(a.size() - 1));
        return pair;
    }

    public static <E> void putFirstLast(ArrayList<E> a, E7_23andE7_24.Pair<E> p){
        p.setX(a.get(0));
        p.setY(a.get(a.size() - 1));
    }

    public static <E extends Comparable<? super E>> E7_23andE7_24.Pair<E> getMinMax(ArrayList<E> a){
        E7_23andE7_24.Pair<E> pair = new E7_23andE7_24.Pair<>();
        E max = a.get(0);
        for (int i = 1; i < a.size(); i++)
            if (a.get( i ).compareTo( max ) > 0) max = a.get( i );
        E min = a.get(0);
        for(int i = 1;i < a.size();i++){
            if(a.get(i).compareTo(min) < 0) min = a.get(i);
        }
        pair.setX(min);
        pair.setY(max);
        return pair;
    }
}

/*
public class Utils
{
   public static <E> void fill(ArrayList<E> a, E value, int count)
   {
      for (int i = 0; i < count; i++)
         a.add(value);
   }

   public static <E, F extends E> void append(ArrayList<E> a,
         ArrayList<F> b, int count)
   {
      for (int i = 0; i < count && i < b.size(); i++)
         a.add(b.get(i));
   }

   public static <E extends Comparable<? super E>>
         E getMax(ArrayList<E> a)
   {
      E max = a.get(0);
      for (int i = 1; i < a.size(); i++)
         if (a.get(i).compareTo(max) > 0) max = a.get(i);
      return max;
   }

   public static <E> void fillWithDefaults(ArrayList<E> a,
         Class<? extends E> cl, int count)
         throws InstantiationException, IllegalAccessException
   {
      for (int i = 0; i < count; i++)
         a.add(cl.newInstance());
   }
}
*/
