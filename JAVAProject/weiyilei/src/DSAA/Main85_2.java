package DSAA;/*import java.io.*;
import java.util.*;

public class Main {

    static class treeN{
        int key;
        int left;
        int right;
        treeN lson;
        treeN rson;
        treeN(int key,int left,int right){
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int m = in.nextInt();
        int k = in.nextInt();
        int i,a,b;
        int len = m - k + 1;
        int[] listNode = new int[m];
        int[] loc = new int[len];
        for(i = 0;i < m;i++){
            listNode[i] = in.nextInt();
        }
        for(i = 0;i < len;i++){
            loc[i] = in.nextInt();
        }
        a = listNode[0];
        b = listNode[0];
        for(i = 0;i < m;i++){
            if(a > listNode[i]){
                a = listNode[i];
            }
            if(b < listNode[i]){
                b = listNode[i];
            }
        }
        treeN rt = new treeN(0,a,b);
        build(rt);
        for(i = 0;i < k;i++){
            insert(listNode[i],rt);
        }
        search(loc[0],rt);
        for(i = 1;i < len;i++){
            dlt(listNode[i - 1],rt);
            insert(listNode[k - 1 + i],rt);
            search(loc[i],rt);
        }
        out.close();
    }

    public static void build(treeN rt){
        int left = rt.left;
        int right = rt.right;
        int mid = (left + right) >> 1;
        if(left == right){
            return;
        }
        else if(left == right - 1){
            rt.lson = new treeN(0,left,left);
            rt.rson = new treeN(0,right,right);
            return;
        }
        else{
            rt.lson = new treeN(0,left,mid);
            rt.rson = new treeN(0,mid + 1,right);
            build(rt.lson);
            build(rt.rson);
        }
    }

    public static void insert(int a,treeN rt){
        rt.key = rt.key + 1;
        if(rt.left == rt.right){
            return;
        }
        if(a <= (rt.left + rt.right) >> 1){
            rt = rt.lson;
        }
        else{
            rt = rt.rson;
        }
        insert(a,rt);
    }

    public static void dlt(int a,treeN rt){
        rt.key = rt.key - 1;
        if(rt.left == rt.right){
            return;
        }
        if(a <= (rt.left + rt.right) >> 1){
            rt = rt.lson;
        }
        else{
            rt = rt.rson;
        }
        dlt(a,rt);
    }

    public static void search(int a,treeN rt){
        if(rt.left == rt.right){
            out.println(rt.left);
            return;
        }
        if(a > rt.lson.key){
            search(a - rt.lson.key,rt.rson);
        }
        else{
            search(a,rt.lson);
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
}*/
import java.util.Scanner;

public class Main85_2 {

    static class node {
        int num;
        int left;
        int right;
        node lson;
        node rson;
        public node(int num, int left, int right) {
            this.num = num;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int k = input.nextInt();
        int length = m-k+1;
        int[] list = new int[m];
        int[] pos = new int[length];
        for (int i = 0; i < m; i++)
            list[i] = input.nextInt();
        for (int i = 0; i < length; i++)
            pos[i] = input.nextInt();
        int a = list[0];
        int b = list[0];
        for (int i = 0; i < m; i++) {
            if (a > list[i])
                a = list[i];
            if (b < list[i])
                b = list[i];
        }
//        System.out.println(a+" "+b);
        node root = new node(0, a, b);
        buildTree(root);
//        printt(root);
        for (int i = 0; i < k; i++)
            insert(list[i], root);
        find(pos[0], root);
//        printt(root);
//        System.out.println(root.left+" "+root.right+" "+root.num);
        for (int i = 1; i < length; i++) {
            delete(list[i-1], root);
            insert(list[k-1+i], root);
            find(pos[i], root);
        }
    }

    public static void buildTree(node root) {
        int left = root.left;
        int right = root.right;
        int mid = (left+right)>>1;
        if (left == right)
            return;
        else if (left == right-1) {
            root.lson = new node(0, left, left);
            root.rson = new node(0, right, right);
            return;
        }
        else {
            root.lson = new node(0, left, mid);
            root.rson = new node(0, mid+1, right);
            buildTree(root.lson);
            buildTree(root.rson);
        }
    }

    public static void insert (int need, node root) {
        root.num += 1;
        if (root.left == root.right)
            return;
        if (need <= (root.left+root.right)>>1)
            root = root.lson;
        else
            root = root.rson;
        insert(need, root);
    }

    public static void delete (int need, node root) {
        root.num -= 1;
        if (root.left == root.right)
            return;
        if (need <= (root.left+root.right)>>1)
            root = root.lson;
        else
            root = root.rson;
        delete(need, root);
    }

    public static void find(int need, node root) {
        if (root.left == root.right) {
            System.out.println(root.left);
            return;
        }
        if (need > root.lson.num)
            find(need-root.lson.num, root.rson);
        else
            find(need, root.lson);
    }

    public static void printt(node root) {
        int point1 = 0, point2 = 0;
        node[] l = new node[10000];
        l[0] = root;
        while (point1 != point2+1) {
            if (l[point1].lson == null) {
                point1 += 1;
                continue;
            }
            l[point2+1] = l[point1].lson;
            l[point2+2] = l[point1].rson;
            point2 += 2;
            point1 += 1;
        }
        int nn = 0;
        while (nn != point1) {
            if (l[nn].num != 0)
                System.out.println(l[nn].left + "," + l[nn].right+"   "+l[nn].num);
            nn += 1;
        }
    }
}
/*import java.util.Scanner;

class treeN{
    int key;
    int left;
    int right;
    treeN lson;
    treeN rson;
    treeN(int key,int left,int right){
        this.key = key;
        this.left = left;
        this.right = right;
    }
}

public class Main85 {



    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int k = in.nextInt();
        int i,a,b;
        int len = m - k + 1;
        int[] listNode = new int[m];
        int[] loc = new int[len];
        for(i = 0;i < m;i++){
            listNode[i] = in.nextInt();
        }
        for(i = 0;i < len;i++){
            loc[i] = in.nextInt();
        }
        a = listNode[0];
        b = listNode[0];
        for(i = 0;i < m;i++){
            if(a > listNode[i]){
                a = listNode[i];
            }
            if(b < listNode[i]){
                b = listNode[i];
            }
        }
        treeN rt = new treeN(0,a,b);
        build(rt);
        for(i = 0;i < k;i++){
            insert(listNode[i],rt);
        }
        search(loc[0],rt);
        for(i = 1;i < len;i++){
            dlt(listNode[i - 1],rt);
            insert(listNode[k - 1 + i],rt);
            search(loc[i],rt);
        }
    }

    public static void build(treeN rt){
        int left = rt.left;
        int right = rt.right;
        int mid = (left + right) >> 1;
        if(left == right){
            return;
        }
        else if(left == right - 1){
            rt.lson = new treeN(0,left,left);
            rt.rson = new treeN(0,right,right);
            return;
        }
        else{
            rt.lson = new treeN(0,left,mid);
            rt.rson = new treeN(0,mid + 1,right);
            build(rt.lson);
            build(rt.rson);
        }
    }

    public static void insert(int a,treeN rt){
        rt.key = rt.key + 1;
        if(rt.left == rt.right){
            return;
        }
        if(a <= (rt.left + rt.right) >> 1){
            rt = rt.lson;
        }
        else{
            rt = rt.rson;
        }
        insert(a,rt);
    }

    public static void dlt(int a,treeN rt){
        rt.key = rt.key - 1;
        if(rt.left == rt.right){
            return;
        }
        if(a <= (rt.left + rt.right) >> 1){
            rt = rt.lson;
        }
        else{
            rt = rt.rson;
        }
        dlt(a,rt);
    }

    public static void search(int a,treeN rt) {
        if (rt.left == rt.right) {
            System.out.println(rt.left);
            return;
        }
        if (a > rt.lson.key) {
            search(a - rt.lson.key, rt.rson);
        } else {
            search(a, rt.lson);
        }
    }

    public static void printt(treeN root) {
        int point1 = 0, point2 = 0;
        treeN[] l = new treeN[10000];
        l[0] = root;
        while (point1 != point2+1) {
            if (l[point1].lson == null) {
                point1 += 1;
                continue;
            }
            l[point2+1] = l[point1].lson;
            l[point2+2] = l[point1].rson;
            point2 += 2;
            point1 += 1;
        }
        int nn = 0;
        while (nn != point1) {
            if (l[nn].key != 0)
                System.out.println(l[nn].left + "," + l[nn].right+"   "+l[nn].key);
            nn += 1;
        }
    }
}*/
