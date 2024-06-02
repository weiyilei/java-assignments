package Exercise9;

import java.io.PrintWriter;
import java.util.Scanner;

public class Question1 {

    static class Counter{
        int cnt;
        void increase(){this.cnt += 1;}
        int getCnt(){return this.cnt;}
    }

    static Counter counter;
    static int number;

    static class Incrementer extends Thread{
        public void run(){
            for (int i = 0;i < number;i++){
                counter.increase();
            }
        }
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println();
            System.out.print("How many threads do you want to run (Enter 0 to end)? ");
            int n = in.nextInt();
            if (n <= 0){break;}
            do{
                System.out.println();
                System.out.println("How many times should each thread increment the counter? ");
                number = in.nextInt();
                if(number < 1){
                    System.out.println("Number of increments must be positive.");
                    number = 1;
                }
            } while(number <= 0);
            System.out.println();
            System.out.println("Using " + n + " threads.");
            System.out.println("Each thread increments the counter " + number + " times.");
            System.out.println();
            System.out.println("Working...");
            System.out.println();
            Incrementer[] workers = new Incrementer[n];
            counter = new Counter();
            for (int i = 0;i < n;i++){
                workers[i] = new Incrementer();
            }
            for(int i = 0;i < n;i++){
                workers[i].start();
            }
            for(int i = 0;i < n;i++){
                try{
                    workers[i].join();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

            System.out.println("The final value of the counter should be " + (n * number));
            System.out.println("Actual final value of counter is: " + counter.getCnt());
            System.out.println();
            System.out.println();
        }
    }
}
