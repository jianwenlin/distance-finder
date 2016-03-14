package com.fitch.solutions.search.proximity.search;

/**
 * @author FQM832 -
 * @since Mar 12, 2016
 */
public class SortedArrayToBinarySearchTreeService {

    public TreeNode sortedArrayToBST(final int[] numbers) {

        if (numbers.length == 0) {
            return null;
        }
        return sortedArrayToBST(numbers, 0, numbers.length - 1);
    }

    public TreeNode sortedArrayToBST(final int[] numbers, final int start, final int end) {

        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(numbers[mid]);
        root.left = sortedArrayToBST(numbers, start, mid - 1);
        root.right = sortedArrayToBST(numbers, mid + 1, end);

        return root;
    }

}
