package com.fitch.solutions.search.proximity.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author FQM832 -
 * @since Mar 13, 2016
 */
public class SortedArrayToBinarySearchTreeServiceTest {

    int[] sortedArrayOdd;

    int[] sortedArrayEven;

    private SortedArrayToBinarySearchTreeService subject = new SortedArrayToBinarySearchTreeService();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        sortedArrayOdd = new int[] {1, 3, 5, 7, 9};
        sortedArrayEven = new int[] {1, 3, 5, 7, 9, 10, 12, 13};
    }

    @After
    public void tearDown() throws Exception {
        sortedArrayOdd = null;
        sortedArrayEven = null;
    }

    @Test
    public void testBuildBSTFromSortedArrayOddInputLength() {
        TreeNode treeNode = subject.sortedArrayToBST(sortedArrayOdd);
        assertEquals(5, treeNode.data);
        assertEquals(7, treeNode.right.data);
        assertEquals(9, treeNode.right.right.data);
        assertEquals(3, treeNode.left.right.data);
        assertEquals(1, treeNode.left.data);
        assertTrue(SearchUtility.isBst(treeNode));
    }

    @Test
    public void testBuildBSTFromSortedArrayEvenInputLength() {
        TreeNode treeNode = subject.sortedArrayToBST(sortedArrayEven);
        assertEquals(7, treeNode.data);
        assertEquals(10, treeNode.right.data);
        assertEquals(9, treeNode.right.left.data);
        assertEquals(12, treeNode.right.right.data);
        assertEquals(13, treeNode.right.right.right.data);
        assertEquals(3, treeNode.left.data);
        assertEquals(5, treeNode.left.right.data);
        assertEquals(1, treeNode.left.left.data);

        assertTrue(SearchUtility.isBst(treeNode));
    }

}
