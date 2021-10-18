package com.huihui.aligo.base;

/**
 * 单向链表实现栈
 *
 * @author minghui.y
 * @create 2021-10-18 11:25 下午
 **/
public class BaseAlgorithm_17 {



    public static class Stack<T> {

        private Node<T> top;
        private int size;

        /**
         * 入栈
         * @param data
         */
        public void push(T data) {
            Node<T> newNode = new Node<>( data );
            if (top == null) {
                top = newNode;
            } else {
                newNode.next = top;
                top = newNode;
            }
            size++;
        }

        /**
         * 出站
         * @return
         */
        public T pop() {
            if (top == null) {
                return null;
            }
            T data = top.getData();
            top = top.next;
            size--;
            return data;
        }

        public int size() {
            return size;
        }

    }

    /**
     * 节点类
     *
     * @param <T>
     */
    public static class Node<T> {
        T data;
        Node<T> next;

        public Node( T data ) {
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


