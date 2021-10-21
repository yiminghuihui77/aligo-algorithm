package com.huihui.aligo.base;

/**
 * 合并两个有序链表
 * 1 -> 5 -> 6 -> 9
 * 4 -> 7 -> 8
 *
 * 结果：1 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
 * @author minghui.y
 * @create 2021-10-20 11:20 下午
 **/
public class BaseAlgorithm_21 {

    public static void main( String[] args ) {

        Node a = new Node(1);
        Node b = new Node(5);
        Node c = new Node(6);
        Node d = new Node(9);
        a.next = b;
        b.next = c;
        c.next = d;

        Node e = new Node( 4 );
        Node f = new Node( 7 );
        Node g = new Node( 8 );
        e.next = f;
        f.next = g;

        printLinkedList( a );
        printLinkedList( e );

        Node head = mergeOrderedLinkedList( a, e );
        printLinkedList( head );



    }



    public static Node mergeOrderedLinkedList(Node h1, Node h2) {
        if (h1 == null || h2 == null) {
            return h1 == null ? h2 : h1;
        }

        //谁的首节点最小，谁当头
        Node head = h1.data <= h2.data ? h1 : h2;

        Node cur1 = head.next;
        Node cur2 = head == h1 ? h2 : h1;
        Node pre = head;

        //两条链表都没到结尾的场景
        while (cur1 != null && cur2 != null) {
            //谁小，pre.next指向谁
            if (cur1.data <= cur2.data) {
                pre.next = cur1;
                pre = cur1;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                pre = cur2;
                cur2 = cur2.next;
            }
        }

        //有一个链表遍历完，pre最后指向没遍历完的节点即可
        pre.next = cur1 == null ? cur2 : cur1;


        return head;
    }

    public static class Node {
        Integer data;
        private Node next;

        public Node(Integer data) {
            this.data = data;
        }
    }

    public static void printLinkedList( Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

}
