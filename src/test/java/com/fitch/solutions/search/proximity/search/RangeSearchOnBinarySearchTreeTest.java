package com.fitch.solutions.search.proximity.search;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RangeSearchOnBinarySearchTreeTest {

    private TreeNode root;

    private RangeSearchOnBinarySearchTree subject = new RangeSearchOnBinarySearchTree();

    @Before
    public void setUp() throws Exception {
        root = buidSearchTree();
    }

    @After
    public void tearDown() throws Exception {
        root = null;
    }

    @Test
    public void testWhenRangeStraddleOfTreeRoot7() {
        assertEquals(4, subject.rangeSearch(root, 5, 9));
    }

    @Test
    public void testSearchRoot7OnlyExpect1() {
        assertEquals(1, subject.rangeSearch(root, 7, 7));
    }

    @Test
    public void testSearchStartAtLowerBound() {
        assertEquals(6, subject.rangeSearch(root, 1, 9));
    }

    @Test
    public void testSearchtartAtLessThanLowerBoundExpectSameAsLowerBound() {
        assertEquals(6, subject.rangeSearch(root, -1, 9));
    }

    @Test
    public void testWhenSearchWholeArrayExpectArraySize() {
        assertEquals(8, subject.rangeSearch(root, 1, 17));
    }

    @Test
    public void testSearchUpperBoundExpectTotalNumberIncludeUpperBound() {
        assertEquals(6, subject.rangeSearch(root, 5, 17));
    }

    @Test
    public void testWhenRangeGreaterThanUpperBoundExpectTheSameResultAsUpperBound() {
        assertEquals(6, subject.rangeSearch(root, 5, 19));
    }

    @Test
    public void testWhenRangeLowerAndUpperBothNotAValueInTheArray() {
        assertEquals(5, subject.rangeSearch(root, 4, 15));
    }

    @Test
    public void testLowerGreaterThanUpperExpect0() {
        assertEquals(0, subject.rangeSearch(root, 15, 10));
    }

    @Test
    public void testRootIsNullExpect0() {
        assertEquals(0, subject.rangeSearch(null, 14, 10));
    }

    TreeNode buidSearchTree() {
        SortedArrayToBinarySearchTreeService sortedArrayToBinarySearchTreeService = new SortedArrayToBinarySearchTreeService();

        int[] inputSortedArray = {1, 3, 5, 7, 8, 9, 10, 17};

        return sortedArrayToBinarySearchTreeService.sortedArrayToBST(inputSortedArray);

    }
}
