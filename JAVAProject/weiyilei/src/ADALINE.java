public class ADALINE {
    public static void main(String[] args){
        double[] x = {-1,2.5,1.6};
        double[] w = {-1,0.4,2,2};
        double rate = 0.2;
        double desire = 3;
        double y;
        double e;
        double[] improvement = {0,0,0};
        double lon = 0;
        for(int i = 0;i < 3;i++){
            lon = lon + x[i] * x[i];
        }

        y = x[0] * w[0] + x[1] * w[1] + x[2] * w[2];
        System.out.println("y: " + y);
        e = desire - y;
        System.out.println("e: " + e);
        for(int i = 0;i < 4;i++){
            for(int j = 0;j < 3;j++){
                improvement[j] = rate * e * x[j] / lon;
                System.out.print(improvement[j] + " ");
            }
            System.out.println();
            for(int j = 0;j < 3;j++){
                w[j] = w[j] + improvement[j];
                System.out.println(w[j] + " ");
            }
            System.out.println();
            y = x[0] * w[0] + x[1] * w[1] + x[2] * w[2];
            e = desire - y;
            for(int j = 0;j < 3;j++){
                System.out.println(w[j]);
            }
        }

    }
}
