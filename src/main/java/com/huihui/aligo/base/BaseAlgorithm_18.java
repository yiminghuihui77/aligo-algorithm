package com.huihui.aligo.base;

/**
 * 双向链表实现双端队列：首尾都能出队入队
 *
 * @author minghui.y
 * @create 2021-10-18 11:51 下午
 **/
public class BaseAlgorithm_18 {



    public static class DoubleEndedQueue<T> {
        private Node<T> head;
        private Node<T> tail;
        private int size;

        /**
         * 从队首入队
         * @param data
         */
        public void pushHead(T data) {
            Node<T> newNode = new Node<>( data );
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head.pre = newNode;
                head = newNode;
            }
           size++;
        }

        /**
         * 从队尾入队
         * @param data
         */
        public void pushTail(T data) {
            Node<T> newNode = new Node<>( data );
            if (tail == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.pre = tail;
                tail = newNode;
            }
            size++;
        }

        /**
         * 从队首出队
         * @return
         */
        public T pollHead() {
            T data = null;
            if (head == null) {
                return data;
            }
            data = head.getData();
            size--;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.pre = null;
            }
            return data;
        }

        /**
         * 从队尾出队
         * @return
         */
        public T pollTail() {
            T data = null;
            if (tail == null) {
                return data;
            }
            data = tail.getData();
            size--;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.pre;
                tail.next = null;
            }
            return data;
        }

    }



    public static class Node<T> {
        private Node<T> pre;
        private Node<T> next;
        private T data;
        public Node(T data) {
            this.data = data;
        }

        public Node<T> getPre() {
            return pre;
        }

        public void setPre( Node<T> pre ) {
            this.pre = pre;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext( Node<T> next ) {
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData( T data ) {
            this.data = data;
        }
    }
}
