package DSAA;

import java.io.*;
import java.util.*;
import java.util.Arrays;

public class Main36_2 {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int n = in.nextInt();
        long xl = in.nextLong();
        long xr = in.nextLong();
        int i;
        long k,b;
        long[] y1 = new long[n];
        long[] y2 = new long[n];
        long[] cha1 = new long[n];
        long[] cha2 = new long[n];
        String s = "NO";

        for(i = 0;i < n;i++){
            k = in.nextLong();
            b = in.nextLong();
            y1[i] = k * xl + b;
            y2[i] = k * xr + b;
            cha1[i] = y2[i] - y1[i];
        }
        MergeSort(y1,0,y1.length - 1);
        MergeSort(y2,0,y2.length - 1);
        MergeSort(cha1,0,cha1.length - 1);
        for(i = 0;i < n;i++){
            cha2[i] = y2[i] - y1[i];
        }
        MergeSort(cha2,0,cha2.length - 1);
        for(i = 0;i < n;i++){
            if(cha1[i] != cha2[i]){
                s = "YES";
                break;
            }
        }
        out.println(s);


        out.close();
    }

    public static void MergeSort(long[] a,int l,int r){
        if(l < r){
            int mid = (l + r)/2;
            MergeSort(a,l,mid);
            MergeSort(a,mid + 1,r);
            Sort(a,l,mid,r);
        }
    }

    public static void Sort(long[] a,int l,int middle,int r){
        long[] temp = new long[r - l + 1];
        int m = l;
        int k = 0;
        int n = middle + 1;
        while(m <= middle && n <= r){
            if(a[m] <= a[n]){
                temp[k++] = a[m++];
            }
            else{
                temp[k++] = a[n++];
            }
        }
        while(m <= middle){
            temp[k++] = a[m++];
        }
        while(n <= r){
            temp[k++] = a[n++];
        }
        for(int q = 0;q <= r - l;q++){
            a[q + l] = temp[q];
        }
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