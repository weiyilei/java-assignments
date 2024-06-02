import java.io.*;
import java.util.*;
import java.util.LinkedList;
import java.util.Queue;


/*class treeNode{
    int key;
    treeNode left;
    treeNode right;
    treeNode parent;
    treeNode(int a){this.key = a;}
}*/

/*public class Algorithm_Design.Main81 {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int t = in.nextInt();
        int i,n,j,a,b,rt;
        boolean jud;
        for(i = 1;i <= t;i++){
            jud = true;
            n = in.nextInt();
            int[] judge = new int[2];
            int[] child = new int[n];
            treeNode[] tree = new treeNode[n];
            for(j = 0;j < n;j++){
                tree[j] = new treeNode(in.nextInt());
            }
            for(j = 0;j < n - 1;j++){
                a = in.nextInt();
                b = in.nextInt();
                child[b - 1] = 1;
                if (tree[a - 1].left == null)
                    tree[a - 1].left = tree[ b - 1];
                else if (tree[a -1 ].right == null){
                    tree[a - 1].right = tree[b - 1];
                }
                else {
                    jud = false;
                    out.println("Case #" + i + ": NO");
                    break;
                }
                if (tree[a - 1].key > tree[b - 1].key){
                    judge[0] += 1;
                }
                else if (tree[a - 1].key < tree[b - 1].key){
                    judge[1] += 1;
                }
            }
            if(!jud){
                continue;
            }
            if (judge[0] != 0 && judge[1] != 0) {
                out.println("Case #" + i + ": NO");
                continue;
            }
            rt = 0;
            for (j = 0; j < n; j++) {
                if (child[j] == 0)
                    rt = j + 1;
            }
            if (judge(tree[rt - 1])){
                out.println("Case #" + i + ": YES");
            }
            else{
                out.println("Case #" + i + ": NO");
            }
            /*a = in.nextInt();
            b = in.nextInt();
            tree[a].left = tree[b];
            if(tree[a].key < tree[b].key){
                for(j = 2;j < n;j++){
                    a = in.nextInt();
                    b = in.nextInt();
                    if (tree[a].left == null) {
                        if(tree[a].key > tree[b].key){
                            judge = false;
                            break;
                        }
                        tree[a].left = tree[b];
                    }
                    else if (tree[a].right == null) {
                        if(tree[a].key > tree[b].key){
                            judge = false;
                            break;
                        }
                        tree[a].right = tree[b];
                    }
                    else{
                        judge = false;
                        break;
                    }
                }
            }
            else{
                for(j = 2;j < n;j++){
                    a = in.nextInt();
                    b = in.nextInt();
                    if (tree[a].left == null) {
                        if(tree[a].key < tree[b].key){
                            judge = false;
                            break;
                        }
                        tree[a].left = tree[b];
                    }
                    else if (tree[a].right == null) {
                        if(tree[a].key < tree[b].key){
                            judge = false;
                            break;
                        }
                        tree[a].right = tree[b];
                    }
                    else{
                        judge = false;
                        break;
                    }
                }
            }*/
       /* }
        out.close();
    }*/

/*public static boolean judge(treeNode rt){
    Queue<treeNode> queue = new LinkedList<treeNode>();
    boolean judge1 = false;
    treeNode left;
    treeNode right;
    queue.add(rt);
    while (!queue.isEmpty()) {
        rt = queue.poll();
        left = rt.left;
        right = rt.right;
        if (left==null && right!=null){
            return false;
        }
        if ( (judge1 && (left != null || right != null) ) ){
            return false;
        }
        if (left != null){
            queue.add(left);
        }
        if (right != null) {
            queue.add(right);
        }
        else{
            judge1 = true;
        }
    }
    return true;
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