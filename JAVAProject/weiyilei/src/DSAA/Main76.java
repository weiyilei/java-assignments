package DSAA;

import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class Main76 {

    static class city{
        int num;
        int key;
        ArrayList<city> road = new ArrayList<>();
        public city(int num){
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int t = in.nextInt();
        int i,n,k,j,s1,s2,tmp;
        for(i = 1;i <= t;i++){
            n = in.nextInt();
            k = in.nextInt();
            city[] map1 = new city[n];
            city[] map2 = new city[n];
            for(j = 0;j < n;j++){
                map1[j] = new city(j + 1);
                map2[j] = new city(j + 1);
            }
            int[] loc = new int[k];
            for(j = 0;j < n - 1;j++){
                s1 = in.nextInt();
                s2 = in.nextInt();
                map1[s1-1].road.add(map1[s2-1]);
                map1[s2-1].road.add(map1[s1-1]);
                map2[s1-1].road.add(map2[s2-1]);
                map2[s2-1].road.add(map2[s1-1]);
            }
            for(j = 0;j < k;j++){
                loc[j] = in.nextInt();
            }
            map1[loc[0] - 1].key = 0;
            search(map1[loc[0] - 1]);
            tmp = loc[0] - 1;
            for(j = 0;j < k;j++){
                if(map1[loc[j] - 1].key > map1[tmp].key){
                    tmp = loc[j] - 1;
                }
            }
            map2[tmp].key = 0;
            search(map2[tmp]);
            tmp = loc[0] - 1;
            for(j = 0;j < k;j++){
                if(map2[loc[j] - 1].key > map2[tmp].key){
                    tmp = loc[j] - 1;
                }
            }
            out.println((map2[tmp].key + 1) / 2);
        }
        out.close();
    }

    public static void search(city n) {
        int q;
        if(n.road.size() == 0){
            return;
        }
        else{
            for(q = 0;q < n.road.size();q++) {
                n.road.get(q).key = n.key + 1;
                n.road.get(q).road.remove(n);
                search(n.road.get(q));
            }
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