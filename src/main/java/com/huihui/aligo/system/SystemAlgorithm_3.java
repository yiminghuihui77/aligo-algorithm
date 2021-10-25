package com.huihui.aligo.system;

/**
 * 单向链表删除某个值的节点
 *
 * @author minghui.y
 * @create 2021-10-25 11:59 下午
 **/
public class SystemAlgorithm_3 {

    public static void main( String[] args ) {
        Node a = new Node( 1 );
        Node b = new Node( 4 );
        Node c = new Node( 2 );
        Node d = new Node( 4 );
        Node e = new Node( 3 );

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        printLinkList( a );

        Node head = removeTargetNode( a, 4 );

        printLinkList( head );


    }


    /**
     * 删除链表中制定值的所有节点
     * @param head
     * @param num
     * @return
     */
    public static Node removeTargetNode(Node head, int num) {
        //head先跳过为num的节点
        while (head != null) {
            if (head.data != num) {
                break;
            }
            head = head.next;
        }

        Node pre = head;
        Node cur = head.next;
        while (cur != null) {
            if (cur.data == num) {
                pre.next = cur.next;
                cur = cur.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }

    public static class Node {
        Integer data;
        Node next;
        public Node(Integer data) {
            this.data = data;
        }
    }

    /**
     * 注意：打印链表的方法，不能改变head原本的指向！！！
     * @param head
     */
    public static void printLinkList( Node head) {
        Node point = head;
        while (point != null) {
            System.out.print(point.data + " ");
            point = point.next;
        }
        System.out.println();
    }

}
