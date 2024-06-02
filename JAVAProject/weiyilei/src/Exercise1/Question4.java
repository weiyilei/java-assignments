package Exercise1;

public class Question4 {
    public static void main(String[] args){
        int time = Integer.parseInt(args[0]);                  //Use 15 or 17 to get the best visual perception
        Turtle t = new Turtle(784,324,90);
        double step = Math.pow(Math.sqrt(2),time + 1)/Math.pow(2,time)*400.0;
        String str = "G1";
        StdDraw.setCanvasSize(1960,1080);//Here is your Screen Resolution
        StdDraw.setXscale(0,1960);
        StdDraw.setYscale(0,1080);
        turn(t,str,time,step);
    }

    public static void turn(Turtle t,String str,int time,double step){
        if(time == 0){
            for(int i = 0;i < str.length();i++){
                String temp = String.valueOf(str.charAt(i));
                if(temp.equals("G")){
                    t.goForward(step);
                }
                else if(temp.equals("L")){
                    t.turnLeft(90.0);
                }
                else if(temp.equals("R")){
                    t.turnRight(90.0);
                }
            }
            return;
        }
        for(int i = 0;i < str.length();i++){
            String temp = String.valueOf(str.charAt(i));
            if(temp.equals("G")){
                t.goForward(step);
            }
            else if(temp.equals("L")){
                t.turnLeft(90.0);
            }
            else if(temp.equals("R")){
                t.turnRight(90.0);
            }
            else if(temp.equals("1")){
                turn(t,"1L2G",time - 1,step);
            }
            else if(temp.equals("2")){
                turn(t,"G1R2",time - 1,step);
            }
        }
    }
}
