package Algorithm_Design;

import java.io.*;
import java.util.*;

public class Main11 {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int i,t,n,j,rst,max,max1,max2,index,shengyu,jige,temp = 0,shifou;
        t = in.nextInt();
        for(i = 1;i <= t;i++){
            n = in.nextInt();
            ArrayList<Integer> array = new ArrayList<>();
            for(j = 0;j < n;j++){
                shifou = in.nextInt();
                if(!array.contains(shifou)){
                    array.add(shifou);
                }
            }
            Collections.sort(array);
            Collections.reverse(array);
            max = array.get(0);
            index = 1;
            max1 = max;
            max2 = 0;
            shengyu = array.size();
            jige = 2;
            while(shengyu > 1){
                if(max % array.get(index) == 0){
                    index++;
                    shengyu--;
                }
                else{
                    max1 = max1 + array.get(index);
                    temp = array.get(index);
                    jige--;
                    index++;
                    shengyu--;
                    break;
                }
            }
            while(shengyu > 1 && jige > 0){
                if(max % array.get(index) != 0 && temp % array.get(index) != 0){
                    max1 = max1 + array.get(index);
                    break;
                }
                else{
                    index++;
                    shengyu--;
                }
            }
            if(max % 30 == 0){
                if(array.contains(max/2) && array.contains(max/3) && array.contains(max/5)){
                    max2 = max / 30 * 31;
                }
            }
            rst = Math.max(max1,max2);
            out.println(rst);
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