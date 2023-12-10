package com.connor.java.leetcode;

import com.connor.java.common.ListNode;
import com.connor.java.sort.Sort;
import com.connor.java.sort.SortTest;
import org.junit.Test;

import java.util.Arrays;
import java.util.logging.Logger;

import static com.connor.java.leetcode.Solution.pack;
import static org.junit.Assert.*;

public class SolutionTest {
    @Test
    public void testMaxLenth1() {
        int[] weight = {1,3,4};
        int[] value = {15,20,30};
        int bagSize = 4;
        pack(weight,value,bagSize);
    }
    private static final Logger logger = Logger.getLogger(SolutionTest.class.getName());

    @Test
    public void testMaxLenth() {
        int lenght = Solution.lengthOfLongestSubstring("helloh");
        logger.info("和max:" + lenght);

        System.out.println("你好");
        System.out.println("你好");
        System.out.println("你好");
        System.out.println("你好");

    }

    @Test
    public void testReverses() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);

        ListNode re = Solution.reverseList(head);
        logger.info("max:" + re.toString());
    }


    @Test
    public void testTopK() {

        Solution s = new Solution();
//        int kthLargest = s.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4);
        int kthLargest = s.findKthLargest(new int[]{-1, 2, 0}, 2);
        logger.info("max:" + kthLargest);
    }
}