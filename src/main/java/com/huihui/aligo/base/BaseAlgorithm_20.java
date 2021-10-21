package com.huihui.aligo.base;

/**
 * 两个链表相加
 *
 * 思路：
 * 1、找出长链表：l和短链表:s
 * 2、第一阶段：l有节点，s有节点
 * 3、第二阶段  l有节点，s无节点
 * 4、第四节点  l无节点，s无节点 （考虑进位）
 * 
 * @author minghui.y
 * @create 2021-10-20 9:45 下午
 **/
public class BaseAlgorithm_20 {

    public static void main( String[] args ) {
        Node h1 = new Node( 1 );
        Node b = new Node( 2 );
        Node c = new Node( 9 );
        Node d = new Node( 9 );
        h1.next = b;
        b.next = c;
        c.next = d;



        Node h2 = new Node( 9 );
        Node e = new Node( 2 );
        Node f = new Node( 1 );
        h2.next = e;
        e.next = f;


        Node result = addLinkedList( h1, h2 );

        printLinkedList( h1 );
        printLinkedList( h2 );
        printLinkedList( result );


    }


    /**
     * 实现两个链表相加
     * 注意：链表头部位置表示低位，尾部表示高位
     * @param h1
     * @param h2
     * @return 返回一个新的链表的头部节点
     */
    public static Node addLinkedList(Node h1, Node h2) {

        int len1 = getLinkedListLength( h1 );
        int len2 = getLinkedListLength( h2 );

        Node l = len1 >= len2 ? h1 : h2;
        Node s = len1 < len2 ? h1 : h2;
        //指向新链表产生的新节点
        Node curHead = null;
        Node curTail = null;
        //表示是否有进位
        int extra = 0;

        //第一阶段：l有节点，s有节点
        while (s != null) {
            int curData = (l.data + s.data + extra) % 10;
            extra = (l.data + s.data + extra) / 10;
            Node curNode = new Node( curData );
            if (curHead == null) {
                curHead = curNode;
            } else {
                curTail.next = curNode;
            }
            curTail = curNode;
            //向右移动
            s = s.next;
            l = l.next;
        }

        //第一阶段：l有节点，s无节点
        while (l != null) {
            int curData = (l.data + extra) % 10;
            extra = (l.data + extra) / 10;
            Node curNode = new Node( curData );
            curTail.next = curNode;
            curTail = curNode;

            l = l.next;
        }

        //第三阶段：l和s都无节点，有进位
        if (extra != 0) {
            Node curNode = new Node( 1 );
            curTail.next = curNode;
            curTail = curNode;
        }


        return curHead;
    }
    
    public static int getLinkedListLength(Node head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
    
    
    
    public static class Node{
        private Integer data;
        private Node next;
        public Node(Integer data) {
            this.data = data;
        }
    }


    public static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }
    
    
    
}
