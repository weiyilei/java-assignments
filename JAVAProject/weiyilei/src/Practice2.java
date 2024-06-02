import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Practice2 {

    @FunctionalInterface public interface Predicate<T>{
        boolean test(T t);
    }

    @FunctionalInterface public interface Function<T,R>{
        R apply(T t);
    }

    public static boolean Prime(int n) {
        if (n < 3) {
            return n > 1;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static <T> List<T> isOdd(List<T> list,Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T t : list){
            if(p.test(t)){
                result.add(t);
            }
        }
        return result;
    }

    public static <T> List<T> isEven(List<T> list,Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T t : list){
            if(p.test(t)){
                result.add(t);
            }
        }
        return result;
    }

    public static <T> List<T> isPrime(List<T> list,Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T t : list){
            if(p.test(t)){
                result.add(t);
            }
        }
        return result;
    }

    public static<T,R> List<R> quadratic(List<T> list,Function<T,R> f){
        List<R> result = new ArrayList<>();
        for(T t : list){
            result.add(f.apply(t));
        }
        return result;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        //1
        System.out.println("Sample Input:");
        int n = in.nextInt();
        List<Integer> array1 = new ArrayList<>();
        for(int i = 0;i < n;i++){
            array1.add(in.nextInt());
        }
        List<Integer> array1ResultArray = isEven(array1,integer -> integer%2 == 0);
        System.out.println("Sample Output:");
        for(Integer integer : array1ResultArray){
            System.out.print(integer + " ");
        }
        System.out.println();
        //2
        System.out.println("Sample Input:");
        n = in.nextInt();
        List<Integer> array2 = new ArrayList<>();
        for(int i = 0;i < n;i++){
            array2.add(in.nextInt());
        }
        List<Integer> array2ResultArray = isOdd(array2,integer -> integer%2 != 0);
        System.out.println("Sample Output:");
        for(Integer integer : array2ResultArray){
            System.out.print(integer + " ");
        }
        System.out.println();
        //3
        System.out.println("Sample Input:");
        n = in.nextInt();
        List<Integer> array3 = new ArrayList<>();
        for(int i = 0;i < n;i++){
            array3.add(in.nextInt());
        }
        List<Integer> array3ResultArray = isPrime(array3,integer -> Prime(integer));
        System.out.println("Sample Output:");
        for(Integer integer : array3ResultArray){
            System.out.print(integer + " ");
        }
        System.out.println();
        //4
        System.out.println("Sample Input:");
        n = in.nextInt();
        List<Integer> array4 = new ArrayList<>();
        for(int i = 0;i < n;i++){
            array4.add(in.nextInt());
        }
        List<Integer> array4ResultArray = isPrime(array4,integer -> Prime(integer) && integer > 5);
        System.out.println("Sample Output:");
        for(Integer integer : array4ResultArray){
            System.out.print(integer + " ");
        }
        System.out.println();
        //5
        System.out.println("Sample Input:");
        n = in.nextInt();
        List<Integer> array5 = new ArrayList<>();
        for(int i = 0;i < n;i++){
            array5.add(in.nextInt());
        }
        List<Integer> array5ResultArray = quadratic(array5,integer -> (int)Math.pow(integer,2));
        int array5Result = 0;
        for(Integer integer : array5ResultArray){
            array5Result = array5Result + integer;
        }
        System.out.println("Sample Output:");
        System.out.println(array5Result);
    }
}
