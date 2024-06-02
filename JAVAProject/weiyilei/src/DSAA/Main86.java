package DSAA;

import java.util.Scanner;

public class Main86 {

    static node root;
    static long result;

    static class node {
        int val;
        int kind;
        node father;
        node left;
        node right;
        public node(int val, int kind) {
            this.val = val;
            this.kind = kind;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            int kind = input.nextInt();
            int val = input.nextInt();
            if (root == null)
                add(val, kind);
            else {
                if (kind == root.kind)
                    add(val, kind);
                else {
                    int son = searchSon(root, val, null);
                    int father = searchFather(root, val, null);
//                    System.out.println(kind+","+val+"   "+son+","+father);
                    if (father==-1 && son!=-1) {
                        result += Math.abs(val-son);
                        delete(son);
                    }
                    else if (father!=-1 && son==-1) {
                        result += Math.abs(val-father);
                        delete(father);
                    }
//                    else if (father != -1) {
//                        result += Math.abs(val-father);
//                        delete(father);
//                    }
                    else if (father != -1) {
                        int a = Math.abs(val-son);
                        int b = Math.abs(val-father);
                        if (b <= a) {
                            result += b;
                            delete(father);
                        }
                        else {
                            result += a;
                            delete(son);
                        }
                    }
//                    System.out.println(result);
                }
            }
        }
        System.out.println(result);
    }

    public static void add(int val, int kind) { root = add(root, val, kind);}

    public static node add(node x, int val, int kind) {
        if (x == null)
            return new node(val, kind);
        if (val > x.val) {
            x.right = add(x.right, val, kind);
            x.right.father = x;
        }
        else {
            x.left = add(x.left, val, kind);
            x.left.father = x;
        }
        return x;
    }

    public static void delete(int val) {root = delete(root, val);}

    public static node delete(node x, int val) {
        if (x == null)
            return x;
        if (val > x.val)
            x.right = delete(x.right, val);
        else if (val < x.val)
            x.left = delete(x.left, val);
        else {
            if (x.left==null && x.right==null)
                x = null;
            else if (x.left!=null && x.right==null) {
                x.left.father = x.father;
                x = x.left;
            }
            else if (x.left==null && x.right!=null) {
                x.right.father = x.father;
                x = x.right;
            }
            else {
                int son = son(val);
                x.val = son;
                x.right = delete(x.right, son);
            }
        }
        return x;
    }

    public static int son(int val) {
        node position = search(root, val);
        if (position == null)
            return -1;
        return son(position);
        //return position==null ? -1:son(position);
    }

    public static int son(node x) {
        if (x.right != null)
            return findMin(x.right);
        else {
            node current = x;
            node father = x.father;
            while (father!=null && current==father) {
                current = father;
                father = current.father;
            }
            if (father == null)
                return -1;
            return father.val;
        }
    }

    public static int findMin(node x) {
        if (x.left == null)
            return x.val;
        else
            return findMin(x.left);
    }

    public static node search(node x, int val) {
        if (x == null)
            return null;
        if (val > x.val)
            return search(x.right, val);
        else if (val < x.val)
            return search(x.left, val);
        else
            return x;
    }

    public static int searchSon(node tree, int val, node son) {
        if (tree == null) {
            if (son == null)
                return -1;
            else
                return son.val;
        }
        else if (val > tree.val)
            return searchSon(tree.right, val, son);
        else if (val < tree.val) {
            son = tree;
            return searchSon(tree.left, val, son);
        }
        else {
            son = tree;
            return son.val;
        }
    }

    public static int searchFather(node tree, int val, node father) {
        if (tree == null) {
            if (father == null)
                return -1;
            else
                return father.val;
        }
        else if (val > tree.val) {
            father = tree;
            return searchFather(tree.right, val, father);
        }
        else if (val < tree.val)
            return searchFather(tree.left, val, father);
        else {
            father = tree;
            return father.val;
        }
    }
}