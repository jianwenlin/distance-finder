package com.fitch.solutions.search.proximity.search;

import java.util.List;

public class SearchUtility {

    public static int[] toIntArray(List<Integer> list) {

        int[] ret = new int[list.size()];
        int i = 0;
        for (Integer e : list)
            ret[i++] = e.intValue();
        return ret;
    }

    public static boolean isBst(final TreeNode node) {
        return isBinarySearchTree(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isBinarySearchTree(final TreeNode node, final int min, final int max) {
        if (node.data < min || node.data > max) {
            return false;
        }

        boolean leftIsBst = false;
        if (node.left != null) {
            if (node.left.data < node.data) {
                leftIsBst = isBinarySearchTree(node.left, min, node.data);
            }
            else {
                leftIsBst = false;
            }
        }
        else {
            leftIsBst = true;
        }

        boolean rightIsBst = false;
        if (node.right != null) {
            if (node.right.data >= node.data) {
                rightIsBst = isBinarySearchTree(node.right, node.data + 1, max);
            }
            else {
                rightIsBst = false;
            }
        }
        else {
            rightIsBst = true;
        }
        return (leftIsBst && rightIsBst);
    }
}
