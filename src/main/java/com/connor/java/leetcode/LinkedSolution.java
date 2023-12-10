package com.connor.java.leetcode;

import com.connor.java.common.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LinkedSolution {


    // 判断链表是否有环
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null){
            return null;
        }

        // 使用Hash法
        HashSet<ListNode> set = new HashSet<>();
        while (headA != null){
            set.add(headA);
            headA = headA.next;
        }

        while (headB != null){
            if (set.contains(headB)){
                return headB;
            }
            headB = headB.next;
        }

        return null;
    }
}
