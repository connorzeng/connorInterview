package com.connor.java.leetcode;

import com.connor.java.common.ListNode;

import java.util.HashSet;
import java.util.Set;

import static com.connor.java.sort.Sort.doQuickSort;
import static com.connor.java.sort.Sort.swap;

public class Solution {

    /**
     * 这里是错误的示范
     *
     * @param param
     * @return
     */
    public static int lengthOfLongestSubstringBak(String param) {

        if (param.length() <= 1) {
            return param.length();
        }

        // 记录最长的字符长度
        int maxLength = 0;
        // 记录重复字符
        Set<Character> rp = new HashSet<>();

        // 进行遍历
        int f = 0;
        char[] params = param.toCharArray();
        while (f < params.length) {
            if (rp.contains(params[f])) {
                rp.clear();
                rp.add(params[f]);
            } else {
                rp.add(params[f]);
                if (rp.size() > maxLength) {
                    maxLength = rp.size();
                }
            }
            f++;
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring(String param) {

        if (param.length() <= 1) {
            return param.length();
        }

        // 记录最长的字符长度
        int maxLength = 0;

        // 进行遍历
        int f = 1;
        int l = 0;
        char[] params = param.toCharArray();
        while (f < params.length) {
            int index = l;
            while (index < f) {
                if (params[f] == params[index]) {
                    l = index + 1;
                    break;
                }
                index++;
            }
            if ((f - l) + 1 > maxLength) {
                maxLength = (f - l) + 1;
            }
            f++;
        }
        return maxLength;
    }

    public static ListNode reverseListBak(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head.next;
        ListNode pre = head;
        head.next = null;

        while (cur.next != null) {
            ListNode next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;

        }

        cur.next = pre;
        return cur;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode temp = cur.next;
            // 指针
            cur.next = pre;

            // forward
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    // ---------
    private int size;
    private int cap;

    private Node head;
    private Node tail;

    static class Node {
        public Node pre;
        public Node next;
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public int findKthLargestBak(int[] nums, int k) {
        cap = k;

        for (int i = 0; i < nums.length; i++) {
            addToList(nums[i]);
            delTail();
        }

        return tail.value;
    }

    private void delTail() {
        if (size > cap) {
            size--;

            Node n = tail.pre;
            if (n == null) {
                tail = head;
                return;
            }
            tail = tail.pre;
            tail.next = null;
        }

    }

    private void addToList(int num) {
        size++;
        if (head == null) {
            head = new Node(num);
            tail = head;
            return;
        }

        if (num >= head.value) {
            Node n = new Node(num);
            n.next = head;
            head.pre = n;
            head = n;
            return;
        }
        if (num <= tail.value) {
            Node n = new Node(num);
            n.pre = tail;
            tail.next = n;
            tail = n;
            return;
        }

        Node cur = head.next;
        while (cur != null) {
            Node next = cur.next;
            Node pre = cur.pre;
            if (num >= cur.value) {
                Node n = new Node(num);
                pre.next = n;
                n.pre = pre;
                n.next = cur;
                cur.pre = n;
                break;
            }
            cur = next;
        }
    }

    public int findKthLargest(int[] nums, int k) {


        int i = doQuickSortK(nums, 0, nums.length - 1, k - 1);

        return nums[i];
    }

    private int doQuickSortK(int[] nums, int low, int high, int k) {
        if (low >= high) {
            return low;
        }
        int i = low;
        int j = high;
        int p = nums[low]; //pivot随意设置

        while (i < j) {
            // 右边找到比p大的
            while (i < j && p >= nums[j]) {
                j--;
            }
            // 左边找到比p小的
            while (i < j && p <= nums[i]) {
                i++;
            }
            swap(nums, i, j);
        }

        nums[low] = nums[i];
        nums[i] = p; // 将基准移动到中间。

        if (k > i ) {
            return doQuickSortK(nums, i+1, high, k);
        }  else if (k == i) {
            return k;
        }else {
            return doQuickSortK(nums, low, i - 1, k);
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {


        return null;
    }
}
