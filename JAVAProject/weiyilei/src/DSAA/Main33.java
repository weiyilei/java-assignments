package DSAA;

import java.io.*;
import java.util.*;

public class Main33 {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int t = in.nextInt();
        int n,i,j,sum1,sum2,k,min,temp;

        for(i = 1;i <= t;i++){
            n = in.nextInt();
            int[] array1 = new int[n];
            int[] array2 = new int[n];
            for(j = 0;j < n;j++){
                array1[j] = in.nextInt();
                array2[j] = array1[j];
            }
            sum1 = 0;
            sum2 = 0;
            //Selection Sort
            for(j = 0;j < n - 1;j++){
                min = j;
                for(k = j + 1;k < n;k++){
                    sum1 = sum1 + 1;
                    if(array1[k] < array1[min]){
                        min = k;
                    }
                }
                temp = array1[j];
                array1[j] = array1[min];
                array1[min] = temp;
                sum1 = sum1 + 1;
            }
            for(j = 0;j < n - 1;j++){
                out.print(array1[j] + " ");
            }
            out.println(array1[n - 1]);
            //Insertion Sort
            for(j = 1;j < n;j++){
                sum2 = sum2 + 1;
                for(k = j;k > 0;k--){
                    if(array2[k - 1] > array2[k]){
                        temp = array2[k - 1];
                        array2[k - 1] = array2[k];
                        array2[k] = temp;
                        sum2 = sum2 + 2;
                    }
                }
            }
            /*Bubble Sort
            for(j = 0;j < n - 1;j++){
                for(k = 0;k < n - 1 - j;k++){
                    if(array1[j] > array1[j + 1]){
                        temp = array1[j];
                        array1[j] = array1[j + 1];
                        array1[j + 1] = temp;
                    }
                }
            }*/
            if(sum2 > sum1){
                out.println("Selection Sort wins!");
            }
            else{
                out.println("Insertion Sort wins!");
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