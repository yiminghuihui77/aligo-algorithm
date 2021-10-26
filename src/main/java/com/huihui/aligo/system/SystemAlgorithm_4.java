package com.huihui.aligo.system;

/**
 * 数组实现队列
 * 数组固定大小，通过下标实现循环数组
 *
 * @author minghui.y
 * @create 2021-10-26 10:01 上午
 **/
public class SystemAlgorithm_4 {


    public static void main( String[] args ) {
        ArrayQueue<Integer> queue = new ArrayQueue<>( 10 );
        //入队
        queue.enQueue( 1 );
        queue.enQueue( 2 );
        queue.enQueue( 3 );
        queue.enQueue( 4 );
        queue.enQueue( 5 );
        queue.enQueue( 6 );
        queue.enQueue( 7 );
        queue.enQueue( 8 );
        queue.enQueue( 9 );
        queue.enQueue( 10 );

        queue.printQueue();

        //出队
        System.out.println("出队元素：" + queue.deQueue());
        System.out.println("出队元素：" + queue.deQueue());
        System.out.println("出队元素：" + queue.deQueue());

        queue.printQueue();

        //入队
        queue.enQueue( 11 );
        queue.enQueue( 12 );
        queue.enQueue( 13 );
        //队列已满
//        queue.enQueue( 14 );

        queue.printQueue();
    }


    public static class ArrayQueue<T> {
        private Object[] arr;
        private int headIndex = 0;
        private int tailIndex = 0;
        private int capacity;
        private int size = 0;

        public ArrayQueue(int capacity) {
            this.capacity = capacity;
            arr = new Object[capacity];
        }

        /**
         * 入队
         * @param el
         */
        public void enQueue(T el) {
             if ((size + 1) > capacity) {
                 throw new RuntimeException("队列已满");
             }
             if (size == 0) {
                 headIndex = 0;
                 tailIndex = 0;
             } else {
                 tailIndex = (tailIndex + 1) % capacity;
             }
             arr[tailIndex % capacity] = el;
             size++;

        }

        /**
         * 出队
         * @return
         */
        public T deQueue() {
            if (size == 0) {
                return null;
            }
            T data = (T) arr[headIndex % capacity];
            headIndex = (headIndex + 1) % capacity;
            size--;
            return data;
        }

        public void printQueue() {
            int index = headIndex;
            while ((index % capacity) != tailIndex) {
                System.out.print(arr[index % capacity] + " ");
                index++;
            }
            System.out.print(arr[index % capacity] + " ");
            System.out.println();
        }

    }





}
