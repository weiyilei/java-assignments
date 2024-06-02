package Algorithm_Design;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.*;

public class Main61 {

    public static int[] pos = new int[5010];
    public static int[] lnk = new int[5010];
    public static item[] d = new item[5010];
    public static long ans;

    static class item{
        int s,t,v,tmp;
        public item(int s,int t,int v){
            this.s = s;
            this.t = t;
            this.v = v;
        }
    }

    public static void sort(int fi,int la){
        if(fi >= la) return;
        int i = fi,j = la,g = d[(i + j) >> 1].tmp;
        item temp;
        while(i <= j){
            while(d[i].tmp < g) i++;
            while(g < d[j].tmp) j--;
            if(i <= j){
                temp = d[i];
                d[i] = d[j];
                d[j] = temp;
                i++;
                j--;
            }
        }
        sort(fi,j);sort(i,la);
    }

    public static boolean find(int x,int cur){
        if(pos[cur] > d[x].t) return false;
        if(lnk[cur] == 0){
            lnk[cur] = x;
            return true;
        }
        if(find(d[x].t < d[lnk[cur]].t ? lnk[cur] : x,cur + 1)){
            lnk[cur] = (d[x].t < d[lnk[cur]].t ? x : lnk[cur]);
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);

        int n = in.nextInt();
        for(int i = 1;i <= n;i++){
            d[i] = new item(in.nextInt(),in.nextInt(),in.nextInt());
        }
        for(int i = 1;i <= n;i++){
            d[i].tmp = d[i].s;
        }
        sort(1,n);
        for(int i = 1,cur = 0;i <= n;i++,cur++){
            cur = Math.max(cur,d[i].s);
            pos[i] = cur;
        }
        for(int i = 1;i <= n;i++){
            d[i].tmp = -d[i].v;
        }
        sort(1,n);
        for(int i = 1;i <= n;i++){
            int cur = 1;
            while(pos[cur] < d[i].s){
                cur++;
            }
            if(find(i,cur)) ans += d[i].v;
        }
        out.println(ans);
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