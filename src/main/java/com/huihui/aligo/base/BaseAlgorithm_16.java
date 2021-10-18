package com.huihui.aligo.base;

/**
 * 单向链表实现队列
 *
 * @author minghui.y
 * @create 2021-10-18 10:59 下午
 **/
public class BaseAlgorithm_16 {

    public static void main( String[] args ) {
        Queue<Integer> queue = new Queue<>();

        queue.offer( 1 );
        queue.offer( 2 );
        queue.offer( 3 );
        queue.offer( 4 );
        queue.offer( 5 );

        System.out.println(queue.pull());
        System.out.println(queue.pull());
        System.out.println(queue.pull());
        System.out.println(queue.pull());
        System.out.println(queue.pull());
        //多出队一个
        System.out.println(queue.pull());

    }





    public static class Queue<T> {

        private Node<T> head;
        private Node<T> tail;
        private int size;

        /**
         * 从队尾入队
         * @param data
         */
        public void offer(T data) {
            //构建一个新节点
            Node<T> newNode = new Node<>( data );
            if (head == null || tail == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
            size++;
        }

        /**
         * 获取队首元素值
         * @return
         */
        public T peak() {
            return head != null ? head.getData() : null;
        }

        /**
         * 从队首出队
         * @return
         */
        public T pull() {
           T data = null;
           if (head != null) {
               data = head.getData();
               head = head.next;
               size--;
           }
           if (head == null) {
               //出队到最后，head将越过tail,此时设置tail也为null，让出队的节点能被GC回收
               tail = null;
           }
           return data;
        }


        public int size() {
            return size;
        }

    }






    /**
     * 节点类
     * @param <T>
     */
    public static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData( T data ) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext( Node<T> next ) {
            this.next = next;
        }

    }
}
