package DSAA;

import java.io.*;
import java.util.*;
import java.util.Arrays;

public class Main35 {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();
        int i,m;
        long max;
        long sum = 0,he = 0,total = 0;
        long[] hp = new long[n];
        long[] atk = new long[n];
        long[] cha = new long[n];

        if(p == 0 && q == 0){
            for(i = 0;i < n;i++){
                hp[i] = in.nextInt();
                atk[i] = in.nextInt();
                sum = sum + atk[i];
            }
            out.println(sum);
        }
        else if(p == 0 && q != 0){
            for(i = 0;i < n;i++){
                hp[i] = in.nextInt();
                atk[i] = in.nextInt();
                sum = sum + atk[i];
                cha[i] = hp[i] - atk[i];
            }
            Arrays.sort(cha);
            m = n - 1;
            while(q > 0 && m >= 0){
                if(cha[m] > 0){
                    sum = sum + cha[m];
                    m = m - 1;
                    q = q - 1;
                }
                else{
                    break;
                }
            }
            out.println(sum);
        }
        else if(p != 0 && q == 0){
            for(i = 0;i < n;i++){
                hp[i] = in.nextInt();
                atk[i] = in.nextInt();
                sum = sum + atk[i];
            }
            out.println(sum);
        }
        else{
            long[] fb = new long[n];
            for(i = 0;i < n;i++){
                hp[i] = in.nextInt();
                atk[i] = in.nextInt();
                sum = sum + atk[i];
                cha[i] = hp[i] - atk[i];
                fb[i] = cha[i];
            }
            max = sum;
            Arrays.sort(fb);
            m = n - 1;
            if(q > n){
                q = n;
            }
            if(q > 1){
                while(m >= n - q + 1){
                    if(fb[m] > 0){
                        he = he + fb[m];
                        m = m - 1;
                    }
                    else{
                        break;
                    }
                }
                if(m != n - 1){
                    for(i = 0;i < n;i++){
                        if(hp[i] * mul(p) - atk[i] > 0){
                            if(cha[i] >= fb[m + 1]){
                                if(fb[m] > 0){
                                    total = sum + he - cha[i] + hp[i] * mul(p) - atk[i] + fb[m];
                                }
                                else{
                                    total = sum + he - cha[i] + hp[i] * mul(p) - atk[i];
                                }
                            }
                            else{
                                total = sum + he + hp[i] * mul(p) - atk[i];
                            }
                        }
                        if(total > max){
                            max = total;
                        }
                    }
                }
                else{
                    for(i = 0;i < n;i++){
                        if(hp[i] * mul(p) - atk[i] > 0){
                            total = sum + hp[i] * mul(p) - atk[i];
                        }
                        if(total > max){
                            max = total;
                        }
                    }
                }
            }
            else{
                for(i = 0;i < n;i++){
                    if(hp[i] * mul(p) - atk[i] > 0) {
                        total = sum + hp[i] * mul(p) - atk[i];
                    }
                    if(total > max){
                        max = total;
                    }
                }
            }
            out.println(max);
        }
        out.close();
    }

    public static long mul(int a){
        long s = 1;
        for(int k = 1;k <= a;k++){
            s = s * 2;
        }
        return s;
    }

    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

}