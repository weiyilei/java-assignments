package Exercise5;

import com.sun.javafx.image.IntPixelGetter;
import sun.util.resources.cldr.pl.CalendarData_pl_PL;

import javax.jnlp.IntegrationService;
import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Exercise5 {
    public static void main(String[] args) throws IOException {
        Path wordsPath = Paths.get("words.txt");
        Path populationPath = Paths.get("worldpop.txt");
        Path areaPath = Paths.get("worldarea.txt");
        E19_14(wordsPath);
    }
    //E19_4
    public static void E19_4(Path path) throws IOException {
        Files.lines(path)
                .distinct()
                .filter(s -> s.length() <= 4)
                .forEach(System.out::println);
    }
    //E19_5
    public static <T> String toString(Stream<T> stream, int n) {
        return stream.limit(n)
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }
    //E19_6
    public static void E19_6(){
         Currency.getAvailableCurrencies().stream()
                 .map(String::valueOf)
                 .sorted()
                 .forEach(s -> System.out.print(s + ","));
    }
    //E19_7
    @FunctionalInterface public interface Function<T,R>{
        R apply(T t);
    }

    public static<T,R> R convertTo(T t, Function<T,R> f){
        return f.apply(t);
    }

    public static void E19_7(Path path) throws IOException{
        Files.lines(path)
                .filter(s -> s.length() >= 2)
                .map(s -> convertTo(s,j -> j.charAt(0) + "..." + j.charAt(j.length() - 1)))
                .forEach(s -> System.out.print(s + ","));
    }
    //E19_10
    public static Optional<Integer> smallestProperDivisor(int n){
        return Optional.of(IntStream.range(1, n)
                .filter(i -> n % i == 0)
                .filter(i -> i != 1)
                .min()
                .orElse(-1));
    }
    //E19_11
    public static void E19_11(int n){
        IntStream.range(1,n + 1)
                .map(i -> i * i)
                .filter(i -> {int temp = i;int rev = 0;while (temp > 0){int rem = temp%10;rev = rev*10 + rem;temp /= 10;}return i == rev;})
                .forEach(i -> System.out.print(i + " "));
    }
    //E19_12
    public static Stream<String> characters(String str){
        return str.codePoints()
                .mapToObj(i -> {if(i > 65535){
                    String hex = "";
                    while(i != 0){
                        if(i % 16 <= 9){
                            hex = (char)(i % 16 + '0') + hex;
                            i = i / 16;
                        }
                        else{
                            hex = (char)(i % 16 - 10 + 'A') +hex;
                            i = i /16;
                        }
                    }
                    return String.valueOf((char)Integer.parseInt(hex.substring(0,4),16) + (char)Integer.parseInt(hex.substring(4),16));
                }else{
                    return String.valueOf((char)i);}
                });
    }
    //E19_13
    public static void E19_13(Path path) throws IOException{
        Files.lines(path)
                .max(Comparator.comparing(s -> {
                    String str = String.valueOf(s);
                    char c;
                    int count = 0;
                    for(int i = 0;i < str.length();i++){
                        c = str.charAt(i);
                        if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
                            count++;
                        }
                    }
                    return count;
                }))
                .ifPresent(System.out::println);
    }
    //E19_14
    public static void E19_14(Path path)throws IOException{
        File file = new File(String.valueOf(path));
        String line;
        List<String> array = new ArrayList<>();
        try(FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr)){
            while((line = br.readLine()) != null){
                array.add(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        array.stream()
                .parallel()
                .map(String::valueOf)
                .filter(s -> s.length() >= 5)
                .filter(s -> {String result = "";
                for(int i = 0;i < s.length();i++){
                    result = result.concat(String.valueOf(s.charAt(s.length() - 1 - i)));
                }
                return result.equals(s);
                })
                .findAny()
                .ifPresent(System.out::println);
    }
    //E19_16
    public static void E19_16(Path path) throws IOException{
        Map<Object, Double> map = Files.lines(path)
                .collect(Collectors.groupingBy(s -> s.substring(0,1).toLowerCase(),
                        Collectors.averagingInt(String::length)));
        System.out.print(map);
    }
    //E19_17
    public static Map<String,Double> E19_17(List<BankAccount> list){
        return list.stream()
                .collect(Collectors.groupingBy(BankAccount::getName,
                        Collectors.summingDouble(BankAccount::getBalance)));
    }
    //E19_18
    public static void E19_18(Path populationPath, Path areaPath) throws IOException{
        File populationFile = new File(String.valueOf(populationPath));
        File areaFile = new File(String.valueOf(areaPath));
        String line1;
        String line2;
        int index1 = 0;
        int index2 = 0;
        String name = "";
        long pop = 0;
        long area = 0;
        double dense = 0;
        List<Country> array = new ArrayList<>();
        try(FileInputStream fis1 = new FileInputStream(populationFile);
        InputStreamReader isr1 = new InputStreamReader(fis1,StandardCharsets.UTF_8);
        BufferedReader br1 = new BufferedReader(isr1);
        FileInputStream fis2 = new FileInputStream(areaFile);
        InputStreamReader isr2 = new InputStreamReader(fis2,StandardCharsets.UTF_8);
        BufferedReader br2 = new BufferedReader(isr2)){
            while((line1 = br1.readLine()) != null){
                line2 = br2.readLine();
                for(int i = 0;i < line1.length();i++){
                    if(line1.charAt(i) >= '0' && line1.charAt(i) <= '9'){
                        index1 = i;
                        break;
                    }
                }
                for(int i = 0;i < line2.length();i++){
                    if(line2.charAt(i) >= '0' && line1.charAt(i) <= '9'){
                        index2 = i;
                        break;
                    }
                }
                if(Integer.parseInt(line2.substring(index2)) != 0){
                    name = line1.substring(0,index1);
                    pop = Long.parseLong(line1.substring(index1));
                    area = Long.parseLong(line2.substring(index2));
                    dense = 1.0*pop/area;
                    array.add(new Country(name,pop,area,dense));
                }
                System.out.println(name+" "+pop+" "+area+" "+dense);
            }
        }
        array.stream()
//                .max(Comparator.comparingDouble(Country::getDense))
//                .ifPresent(c -> System.out.println(c.getName()));
                .max((c1,c2) -> {if(c1.getPopulation()/c1.getArea() - c2.getPopulation()/c2.getArea() > 0){
                    return 1;
                }else if(c1.getPopulation()/c1.getArea() - c2.getPopulation()/c2.getArea() < 0){
                    return -1;
                }else{
                    return 0;
                }
                })
                .ifPresent(c -> System.out.println(c.getName()));
    }

    //E19_19
    //it seems easier to implement the function without stream
    public static List<String> E19_19(String str,char c){
        List<String> result1 =  IntStream.range(0,str.length())
                .filter(i -> str.charAt(i) == c)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());
        List<String> result2 = new ArrayList<>();
        for(int i = 0;i < str.length();i++){
            if(str.charAt(i) == c){
                result2.add(String.valueOf(i));
            }
        }
        return result2;
    }
    //E19_20
    @FunctionalInterface public interface Predicate<T>{
        boolean test(T t);
    }

    public static <T> List<T> isDuplicate(Stream<T> stream,Predicate<T> p){
        List<T> result = new ArrayList<>();
        stream.filter(p::test)
                .forEach(result::add);
        return result;
    }

    public static List<String> E19_20(Stream<String> stream){
        String[] array = new String[1];
        array[0] = "";
        String temp = "";
        return isDuplicate(stream,t -> {
            if (t.equals(array[0])) {
                return true;
            }else{
                array[0] = String.valueOf(t);
                return false;
            }
        });
    }
    //P19_1
    //until 1000000
    public static void P19_1(int n){
        Random generator = new Random();
        long time1 = System.currentTimeMillis();
        System.out.println(generator.ints()
                .filter(i -> i % 2 == 1)
                .limit(n)
                .sum());
        System.out.println(System.currentTimeMillis() - time1);
        long time2 = System.currentTimeMillis();
        System.out.println(generator.ints()
                .parallel()
                .filter(i -> i % 2 == 1)
                .limit(n)
                .sum());
        System.out.println(System.currentTimeMillis() - time2);
    }
    //P19_2
    public static void P19_2(int n){
        Stream.iterate(new BigInteger("0"), i -> i.add(new BigInteger("1")))
                .map(i -> i.pow(2))
                .filter(i -> {String result = "";
                String str = String.valueOf(i);
                for(int j = 0;j < str.length();j++){
                    result = result.concat(String.valueOf(str.charAt(str.length() - 1 - j)));
                }
                return result.equals(str);
                })
                .limit(n)
                .forEach(System.out::println);
    }


}