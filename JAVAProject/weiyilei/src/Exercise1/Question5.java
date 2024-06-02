package Exercise1;

public class Question5 {
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);                           //the degree u want to recursion
        Turtle t = new Turtle(1 - 1/Math.pow(2,n + 1),1/Math.pow(2,n + 1),180);
        double step = 1/Math.pow(2,n);
        StartAtRight(t,n,step);
    }

    public static void StartAtLeft(Turtle t,int n,double step){
        if(n == 0){
            return;
        }
        t.turnLeft(90);
        StartAtRight(t,n - 1,step);
        t.goForward(step);
        t.turnRight(90);
        StartAtLeft(t,n - 1,step);
        t.goForward(step);
        StartAtLeft(t,n - 1,step);
        t.turnRight(90);
        t.goForward(step);
        StartAtRight(t,n - 1,step);
        t.turnLeft(90);
    }

    public static void StartAtRight(Turtle t,int n,double step){
        if(n == 0){
            return;
        }
        t.turnRight(90);
        StartAtLeft(t,n - 1,step);
        t.goForward(step);
        t.turnLeft(90);
        StartAtRight(t,n - 1,step);
        t.goForward(step);
        StartAtRight(t,n - 1,step);
        t.turnLeft(90);
        t.goForward(step);
        StartAtLeft(t,n - 1,step);
        t.turnRight(90);
    }
}
