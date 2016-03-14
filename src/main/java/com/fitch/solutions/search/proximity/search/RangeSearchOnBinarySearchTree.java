package com.fitch.solutions.search.proximity.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FQM832 -
 * @since Mar 13, 2016
 */
public class RangeSearchOnBinarySearchTree {

    public int rangeSearch(final TreeNode root, final int lower, final int upper) {

        if (lower > upper || null == root) {
            return 0;
        }

        List<Integer> result = new ArrayList<Integer>();
        rangeSearchTree(root, lower, upper, result);
        return result.size();
    }

    private void rangeSearchTree(final TreeNode root, final int lower, final int upper, final List<Integer> result) {

        if (null == root) {
            return;
        }
        if (root.data > lower) {
            rangeSearchTree(root.left, lower, upper, result);
        }

        if (root.data >= lower && root.data <= upper) {
            result.add(root.data);
        }

        if (root.data < upper) {
            rangeSearchTree(root.right, lower, upper, result);
        }

    }

}
