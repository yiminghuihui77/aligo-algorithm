package com.huihui.aligo.base;

/**
 * leetcode原题：https://leetcode.com/problems/reverse-nodes-in-k-group/  难度：hard
 * 单向链表的K个节点组内逆序调整
 * @author minghui.y
 * @create 2021-10-19 11:04 下午
 **/
public class BaseAlgorithm_19 {

    public static void main( String[] args ) {

        Node a = new Node( 1 );
        Node b = new Node( 2 );
        Node c = new Node( 3 );
        Node d = new Node( 4 );
        Node e = new Node( 5 );
        Node f = new Node( 6 );
        Node g = new Node( 7 );
        Node h = new Node( 8 );
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        g.next = h;

        printLinkList( a );

        //k个节点组内逆序调整
        Node head = reverseKGroup( a, 3 );

        printLinkList( head );


    }


    /**
     * 实现对一个单向链表K个节点组内逆序
     * @param head
     * @param k
     * @return
     */
    public static Node reverseKGroup(Node head, int k) {
        Node start = head;
        Node end = getGroupEnd4K( head, k );
        if (end == null) {
            //第一组都凑不齐
            return head;
        }
        //能凑齐第一组，则第一组末尾节点是新链表的head
        head = end;
        //对第一组进行翻转
        // 注意局部翻转对start和end指针的变化:start.next和end都指向了第二组的第一个节点
        localReverse( start, end );

        //引入新的指针：表示上一组翻转后的末尾节点
        Node lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getGroupEnd4K( start, k );
            if (end == null) {
                //凑不齐的组不再翻转
                return head;
            }
            //继续翻转
            localReverse( start, end );
            //上一组翻转后的末尾节点重新指向当前组翻转后的第一个节点？？
            lastEnd.next = end;
            lastEnd = start;
        }

        //链表节点数刚好是K的整数倍，才会走到这一行
        return head;
    }



    /**
     * 返回链表方向第K个节点
     * @param head
     * @param k 大于0的整数
     * @return
     */
    public static Node getGroupEnd4K(Node head, int k) {
        if (head == null) {
            return null;
        }
        Node point = head;
        //考虑到链表长度可能不足k，因此加锁point != null
        while (--k > 0 && point != null) {
            point = point.next;
        }

        //不足k个时，返回的实际是null
        return point;
    }

    /**
     * 反转局部链表: k = 3
     * a -> b -> c -> d
     * c -> b -> a -> d
     *
     * a -> b -> c -> d -> e -> f -> g
     * c -> b -> a -> f -> e -> d -> g
     * @param start
     * @param end
     */
    public static void localReverse(Node start, Node end) {
        if (start == null || end == null) {
            return;
        }

        //end向后移动，可能为空
        end = end.next;
        Node pre = null;
        Node cur = start;
        Node next = null;

        while (cur != end) {
            next = cur.next;
            //当前节点向左指
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;



    }


    public static class Node {
        private Integer data;
        private Node next;

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
