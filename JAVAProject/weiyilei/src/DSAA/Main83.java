package DSAA;

import java.io.*;
import java.util.*;

public class Main83 {

    public static void quickSort(int[] seq,int[] index,int l,int r){
        if(l < r){
            int i = l;
            int j = r;
            int pivot = seq[l];
            int idx = index[l];
            while(i < j){
                while(i < j && seq[j] >= pivot){
                    j--;
                }
                if(i < j){
                    seq[i] = seq[j];
                    index[i++] = index[j];
                }
                while(i < j && seq[i] < pivot){
                    i++;
                }
                if(i < j){
                    seq[j] = seq[i];
                    index[j--] = index[i];
                }
            }
            index[i] = idx;
            seq[i] = pivot;
            quickSort(seq,index,l,i - 1);
            quickSort(seq,index,i + 1,r);
        }
    }

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int t = in.nextInt();
        int i,n,p,j,cur,temp,min,time,rear;
        for(i = 1;i <= t;i++){
            n = in.nextInt();
            time = 0;
            rear = 0;
            int[] pow = new int[n];
            int[] hour = new int[n];
            int[] hp = new int[n];
            long sum = 0;
            for(p = 0;p < n;p++){
                pow[p] = in.nextInt();
            }
            for(p = 0;p < n;p++){
                hour[p] = in.nextInt();
            }
            quickSort(hour,pow,0,n - 1);
            j = 0;
            cur = 0;
            time = hour[j];
            while(j < n){
                time = hour[j];
                while(j < n && hour[j] == time){
                    hp[rear] = pow[j++];
                    cur = rear;
                    while(cur > 0){
                        if(hp[cur] < hp[(cur - 1)/2]){
                            temp = hp[cur];
                            hp[cur] = hp[(cur - 1)/2];
                            hp[(cur - 1)/2] = temp;
                            cur = (cur - 1)/2;
                        }
                        else{
                            break;
                        }
                    }
                    rear++;
                }
                while(rear > time){
                    rear--;
                    hp[0] = hp[rear];
                    cur = 0;
                    while(true){
                        if(2 * cur + 2 >= rear){
                            min = 2 * cur + 1;
                        }
                        else{
                            min = hp[2 * cur + 1] > hp[2 * cur + 2] ? (2 * cur + 2) : (2 * cur + 1);
                        }
                        if(hp[cur] > hp[min]){
                            temp = hp[cur];
                            hp[cur] = hp[min];
                            hp[min] = temp;
                            cur = min;
                        }
                        else{
                            break;
                        }
                        if(2 * cur + 1 >= rear){
                            break;
                        }
                    }
                }
            }
            for(p = 0;p < rear;p++){
                sum += (long)hp[i];
            }
            out.println(sum);

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