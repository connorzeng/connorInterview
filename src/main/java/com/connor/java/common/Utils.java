package com.connor.java.common;

public class Utils {

    public static ListNode createLinkedList(int... values) {
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;

        for (int val : values) {
            ListNode newNode = new ListNode(val);
            tail.setNext(newNode);
            tail = newNode;
        }

        return dummyHead.getNext();
    }

    public static void printLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.getNext() != null) {
                System.out.print(" -> ");
            }
            current = current.getNext();
        }
        System.out.println();
    }
}
