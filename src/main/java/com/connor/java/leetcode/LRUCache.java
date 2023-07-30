package com.connor.java.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    static class Node {
        public Node next;
        public Node pre;
        public int key;
        public int value;

        public Node(Node next, Node pre, int key, int value) {
            this.next = next;
            this.pre = pre;
            this.key = key;
            this.value = value;
        }
    }

    // 主存
    private Map<Integer, Node> data;
    // 容量
    private int cap;
    private int size;
    private Node header;
    private Node tail;

    public LRUCache(int capacity) {
        if (capacity <= 0 || capacity > 3000) {
            capacity = 3000;
        }
        data = new HashMap<>(capacity);
        cap = capacity;
        size = 0;
    }

    public int get(int key) {
        Node node = data.get(key);
        if (node == null) {
            return -1;
        }
        addToHeader(node, true);
        return data.get(key).value;
    }

    public void put(int key, int value) {
        // 加入data
        Node node = data.get(key);
        if (node == null) {
            node = new Node(null, null, key, value);
            data.put(key, node);
            size++;
            addToHeader(node, false);
        } else {
            node.value = value;
            addToHeader(node, true);
        }
    }

    private void addToHeader(Node node, boolean exist) {
        if (header == null) {
            header = node;
            tail = node;
        }

        if (header == node) {
            return;
        }
        if (tail == node) {
            Node temp = tail;
            tail = tail.pre;
            tail.next = null;

            doAddToHeader(temp);
            return;
        }

        if (node.pre != null) {
            Node pre = node.pre;
            Node next = node.next;
            pre.next = next;
            next.pre = pre;

            node.pre = null;
            node.next = null;
        }

        doAddToHeader(node);
        if (size > cap) {
            delTail(tail);
        }
    }

    private void delTail(Node n) {
        Node temp = n;
        tail = n.pre;

        if (tail == null) {
            tail = header;
        } else {
            tail.next = null;
            temp.pre = null;
        }
        data.remove(temp.key);
        size--;
    }

    private void doAddToHeader(Node node) {
        node.pre = null;

        Node curHead = header;

        node.next = curHead;
        curHead.pre = node;

        header = node;
    }

    public static void main(String[] args) {
        LRUCache c =new LRUCache(3);
        c.put(1,1);
        c.put(2,2);
        c.put(3,3);
        c.put(4,4);
        System.out.printf(c.get(4) + "");
        System.out.printf(c.get(3) + "");
        System.out.printf(c.get(2) + "");
        System.out.printf(c.get(1) + "");
        c.put(5,5);
        System.out.printf(c.get(1) + "");
        System.out.printf(c.get(2) + "");
        System.out.printf(c.get(3) + "");
        System.out.printf(c.get(4) + "");
    }
}
