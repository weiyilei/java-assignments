/*import java.io.*;
import java.util.*;

class node{
    public int coe;
    public int exp;
    public node next;
    node(int a,int b){
        this.coe = a;
        this.exp = b;
    }
}

public class Main43 {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int t = in.nextInt();
        int i,len1,len2,j,coe1,exp1;
        node tmp;

        for(i = 1;i <= t;i++){
            len1 = in.nextInt();
            node head = new node(-1,-1);
            node now = head;
            for(j = 1;j <= len1;j++){
                now.next = new node(in.nextInt(),in.nextInt());
                now = now.next;
            }
            node last = new node(-1,1000000001);
            now.next = last;
            now = head;
            len2 = in.nextInt();
            for(j = 1;j <= len2;j++){
                coe1 = in.nextInt();
                exp1 = in.nextInt();
                while(now.next.exp <= exp1){
                    now = now.next;
                }
                if(now.exp == exp1){
                    now.coe = now.coe + coe1;
                }
                else{
                    tmp = now.next;
                    now.next = new node(coe1,exp1);
                    now.next.next = tmp;
                    now = now.next;
                }
            }
            now = head.next;
            while(now.coe == 0){
                now = now.next;
            }
            if(now == last){
                out.println("0");
            }
            else{
                if(now.coe == 1){
                    if(now.exp == 0){
                        out.print("1");
                    }
                    else if(now.exp == 1){
                        out.print("x");
                    }
                    else{
                        out.print("x^" + now.exp);
                    }
                }
                else if(now.coe == -1){
                    if(now.exp == 0){
                        out.print("-1");
                    }
                    else if(now.exp == 1){
                        out.print("-x");
                    }
                    else{
                        out.print("-x^" + now.exp);
                    }
                }
                else if(now.coe < -1){
                    if(now.exp == 0){
                        out.print(now.coe);
                    }
                    else if(now.exp == 1){
                        out.print(now.coe + "x");
                    }
                    else{
                        out.print(now.coe + "x^" + now.exp);
                    }
                }
                else if(now.coe > 1){
                    if(now.exp == 0){
                        out.print(now.coe);
                    }
                    else if(now.exp == 1){
                        out.print(now.coe + "x");
                    }
                    else{
                        out.print(now.coe + "x^" + now.exp);
                    }
                }
                now = now.next;
                while(now != last){
                    if(now.coe == 1){
                        if(now.exp == 1){
                            out.print("+x");
                        }
                        else if(now.exp > 1){
                            out.print("+x^" + now.exp);
                        }
                    }
                    else if(now.coe == -1){
                        if(now.exp == 1){
                            out.print("-x");
                        }
                        else if(now.exp > 1){
                            out.print("-x^" + now.exp);
                        }
                    }
                    else if(now.coe < -1){
                        if(now.exp == 1){
                            out.print(now.coe + "x");
                        }
                        else if(now.exp > 1){
                            out.print(now.coe + "x^" + now.exp);
                        }
                    }
                    else if(now.coe > 1){
                        if(now.exp == 1){
                            out.print("+" + now.coe + "x");
                        }
                        else if(now.exp > 1){
                            out.print("+" + now.coe + "x^" + now.exp);
                        }
                    }
                    now = now.next;
                }
                out.println();
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
}*/