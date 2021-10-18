package com.huihui.aligo.base;

/**
 * 双向链表的反转
 * @author minghui.y
 * @create 2021-10-18 10:38 下午
 **/
public class BaseAlgorithm_15 {

    public static void main( String[] args ) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.next = n2;
        n2.last = n1;
        n2.next = n3;
        n3.last = n2;
        n3.next = n4;
        n4.last = n3;

        printDoubleLinkList( n1 );
        System.out.println("======================");
        Node head = reverseDoubleLinkList( n1 );
        printDoubleLinkList( head );

    }

    public static void printDoubleLinkList(Node head) {
        while (head!= null) {
            System.out.println(head.getData());
            head = head.next;
        }
    }

    public static Node reverseDoubleLinkList(Node head) {
        if (head == null) {
            return null;
        }

        Node pre = null;
        Node next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }

        return pre;
    }





    public static class Node {
        private Integer data;
        private Node last;
        private Node next;

        public Node() {

        }
        public Node(Integer data) {
            this.data = data;
        }

        public Integer getData() {
            return data;
        }

        public void setData( Integer data ) {
            this.data = data;
        }

        public Node getLast() {
            return last;
        }

        public void setLast( Node last ) {
            this.last = last;
        }

        public Node getNext() {
            return next;
        }

        public void setNext( Node next ) {
            this.next = next;
        }
    }



}
