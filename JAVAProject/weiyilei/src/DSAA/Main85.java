package DSAA;

import java.util.Scanner;
public class Main85 {

    static treeNode rt;

    static class treeNode {
        int key;
        treeNode left;
        treeNode right;
        int num;

        treeNode(int key, int num) {
            this.key = key;
            this.num = num;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int k = in.nextInt();
        int i, a, b;
        int len = m - k + 1;
        int[] listNode = new int[m];
        int[] loc = new int[len];
        for (i = 0; i < m; i++) {
            listNode[i] = in.nextInt();
        }
        for (i = 0; i < len; i++) {
            loc[i] = in.nextInt();
        }
        for (i = 0; i < k; i++) {
            plus(listNode[i]);
        }
        System.out.println(search1(loc[0]));
        for (i = 1; i < len; i++) {
            dlt(listNode[i - 1]);
            plus(listNode[i - 1 + k]);
            System.out.println(search1(loc[i]));
        }
    }

    public static int size() {
        return size(rt);
    }

    public static int size(treeNode a) {
        if (a == null) {
            return 0;
        } else {
            return a.num;
        }
    }

    public static void plus(int a) {
        rt = plus(rt, a);
    }

    public static treeNode plus(treeNode a, int b) {
        if (a == null) {
            return new treeNode(b, 1);
        }
        if (b < a.key) {
            a.left = plus(a.left, b);
            a.num = a.num + 1;
        } else {
            a.right = plus(a.right, b);
            a.num = a.num + 1;
        }
        return a;
    }

    public static void dlt(int a) {
        rt = dlt(rt, a);
    }

    public static treeNode dlt(treeNode a, int b) {
        if (b < a.key) {
            a.left = dlt(a.left, b);
        } else if (b > a.key) {
            a.right = dlt(a.right, b);
        } else {
            if (a.right == null) {
                return a.left;
            }
            if (a.left == null) {
                return a.right;
            }
            treeNode temp = a;
            a = min(temp.right);
            a.right = dltMin(temp.right);
            a.left = temp.left;
        }
        a.num = size(a.left) + size(a.right) + 1;
        return a;
    }

    public static treeNode dltMin(treeNode a) {
        if (a.left == null) {
            return a.right;
        }
        a.left = dltMin(a.left);
        a.num = size(a.left) + size(a.right) + 1;
        return a;
    }

    public static treeNode min(treeNode a) {
        if (a.left == null) {
            return a;
        }
        return min(a.left);
    }

    public static int search1(int a) {
        return search1(rt, a - 1).key;
    }

    public static treeNode search1(treeNode a, int b) {
        if (a == null) {
            return null;
        }
        int temp = size(a.left);
        if (temp > b) {
            return search1(a.left, b);
        } else if (temp < b) {
            return search1(a.right, b - temp - 1);
        } else {
            return a;
        }
    }

}