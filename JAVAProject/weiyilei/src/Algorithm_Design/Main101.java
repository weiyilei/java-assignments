package Algorithm_Design;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.*;

public class Main101 {
    public static long[] TableLs = new long[62];
    public static long[] TableRs = new long[62];
    public static long[] TableNs = new long[62];

    static class Set{
        long L_number;
        long R_number;
        long N_number;
        public Set(){};
        public Set(long L_number,long R_number,long N_number){
            this.L_number = L_number;
            this.R_number = R_number;
            this.N_number = N_number;
        }
    }

    public static Set getSet(long length){
        if(length == 0) return new Set(0,0,0);
        int index = (int)Math.floor(Math.log(length)/Math.log(2));
        Set set1 = new Set(TableLs[index],TableRs[index],TableNs[index]);
        Set set2 = getSet(length - (long)Math.pow(2,index));
        Set setAns = new Set(set1.L_number + set2.N_number,
                             set1.R_number + set2.L_number,
                             set1.N_number + set2.R_number);
        return setAns;
    }

    public static String getNumbers(Set s){
        return s.L_number + " "
                + s.R_number + " "
                + s.N_number;
    }

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        long[] Ts;
        TableLs[0] = 1;
        TableRs[0] = 0;
        TableNs[0] = 0;
        int t = in.nextInt(),i;
        Ts = new long[t];
        for(i = 1;i <= 61;i++){
            TableLs[i] = TableLs[i - 1] + TableNs[i - 1];
            TableRs[i] = TableRs[i - 1] + TableLs[i - 1];
            TableNs[i] = TableNs[i - 1] + TableRs[i - 1];
        }
        for(i = 0;i < t;i++){
            Ts[i] = in.nextLong();
        }
        for(i = 0;i < t;i++) {
            Set ans = getSet(Ts[i]);
            out.println(getNumbers(ans));
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