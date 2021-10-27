package cn.albertowang.datastructure.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/5 14:08
 * @description
 **/

public class TraverseTree {

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * 非递归前序遍历
     *
     * @param root
     * @return
     */
    public static ArrayList<Integer> preTraverse(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                res.add(curr.val);
                stack.push(curr.right);
                curr = curr.left;
            } else {
                curr = stack.pop();
            }
        }
        return res;
    }

    /**
     * 非递归中序遍历
     *
     * @param root
     * @return
     */
    public static ArrayList<Integer> midTraverse(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                res.add(curr.val);
                curr = curr.right;
            }
        }
        return res;
    }

    /**
     * 非递归后序遍历
     *
     * @param root
     * @return
     */
    public static ArrayList<Integer> postTraverse(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                res.add(curr.val);
                stack.add(curr.left);
                curr = curr.right;
            } else {
                curr = stack.pop();
            }
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n3.left = n5;
        n4.left = n6;
        System.out.println(TraverseTree.preTraverse(n1));
        System.out.println(TraverseTree.midTraverse(n1));
        System.out.println(TraverseTree.postTraverse(n1));
    }
}
