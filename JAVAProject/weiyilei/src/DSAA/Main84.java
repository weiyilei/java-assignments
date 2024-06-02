package DSAA;

import java.io.*;
import java.util.*;

public class Main84 {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int t = in.nextInt();
        int k = in.nextInt();
        long ans = in.nextLong();
        long[] array = new long[k + 1];
        int i,ind;
        long b,c,temp;
        for(i = 1;i <= k;i++){
            b = i + ans;
            c = 0;
            while(b != 0){
                c = c + b%10;
                b = b/10;
            }
            array[i] = i + ans + c;
            ind = i;
            while(ind/2 != 0 && array[ind/2] > array[ind]){
                temp = array[ind];
                array[ind] = array[ind/2];
                array[ind/2] = temp;
                ind = ind/2;
            }
            if(i%100 == 0){
                out.println(array[1]);
                ans = array[1];
            }
        }
        for(i = k + 1;i <= t;i++){
            b = i + ans;
            c = 0;
            while(b != 0){
                c = c + b%10;
                b = b/10;
            }
            if(array[1] < i + ans + c){
                array[1] = i + ans + c;
                ind = 1;
                while(array[2*ind] < array[ind] || array[2*ind + 1] < array[ind]){
                    if(array[2*ind] < array[2*ind + 1]){
                        temp = array[2*ind];
                        array[2*ind] = array[ind];
                        array[ind] = temp;
                        ind = 2*ind;
                    }
                    else{
                        temp = array[2*ind + 1];
                        array[2*ind + 1] = array[ind];
                        array[ind] = temp;
                        ind = 2*ind + 1;
                    }
                    if(2*ind > k){
                        break;
                    }
                    else if(2*ind == k){
                        if(array[2*ind] < array[ind]){
                            temp = array[2*ind];
                            array[2*ind] = array[ind];
                            array[ind] = temp;
                        }
                        break;
                    }
                    else if(2*ind + 1 == k){
                        if(array[2*ind] < array[ind] || array[2*ind + 1] < array[ind]){
                            if(array[2*ind] < array[2*ind + 1]){
                                temp = array[2*ind];
                                array[2*ind] = array[ind];
                                array[ind] = temp;
                            }
                            else {
                                temp = array[2 * ind + 1];
                                array[2 * ind + 1] = array[ind];
                                array[ind] = temp;
                            }
                        }
                        break;
                    }
                }
            }
            if(i%100 == 0){
                out.println(array[1]);
                ans = array[1];
            }
        }
        out.close();
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