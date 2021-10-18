package com.huihui.aligo.base;

/**
 * 单向链表的反转
 *
 * @author minghui.y
 * @create 2021-10-18 7:49 下午
 **/
public class BaseAlgorithm_14 {

    public static void main( String[] args ) {

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        n1.next = n2;
        Node n3 = new Node(3);
        n2.next = n3;
        Node n4 = new Node(4);
        n3.next = n4;

        printLinkList( n1 );

        System.out.println("=====================");

        Node head = reverseLinkList( n1 );

        printLinkList( head );


    }


    /**
     * 反转一个单向链表
     * @param head
     * @return 返回链表的head
     */
    public static Node reverseLinkList(Node head) {
        if (head == null) {
            return null;
        }
        Node pre = null;
        Node next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;

    }

    public static void printLinkList(Node head) {
        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }
    }



    public static class Node {

        private Integer data;
        private Node next;

        public Node(Integer data) {
            this.data = data;
        }
        public Node() {

        }
        public Node(Integer data, Node next ) {
            this.data = data;
            this.next = next;
        }


        /**
         * Getter method for property data.
         *
         * @return property value of data
         */
        public Integer getData() {
            return data;
        }

        /**
         * Setter method for property data.
         *
         * @param data value to be assigned to property data
         */
        public void setData( Integer data ) {
            this.data = data;
        }

        /**
         * Getter method for property next.
         *
         * @return property value of next
         */
        public Node getNext() {
            return next;
        }

        /**
         * Setter method for property next.
         *
         * @param next value to be assigned to property next
         */
        public void setNext( Node next ) {
            this.next = next;
        }
    }
}
