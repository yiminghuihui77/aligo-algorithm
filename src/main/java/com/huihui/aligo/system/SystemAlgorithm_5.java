package com.huihui.aligo.system;

import java.util.Stack;

/**
 * 两个栈实现队列
 *
 * 1、stockPop不为空时，不允许向stackPop倒入数据
 * 2、stackPush向stackPop倒入数据时，必须一次性倒全部
 *
 * 倒数据的时机
 * 两个栈中的数据实际上不会重复，即同一个元素不会同事存在于两个栈
 *
 * @author minghui.y
 * @create 2021-10-26 11:07 上午
 **/
public class SystemAlgorithm_5 {

    public static void main( String[] args ) {
        TwoStackQueue queue = new TwoStackQueue();

        //入队
        //这一步会触发pushToPop
        queue.add( 1 );
        queue.add( 2 );
        queue.add( 3 );
        queue.add( 4 );
        queue.add( 5 );

        //出队
        System.out.println(queue.poll());
        //这一步会触发pushToPop
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

        //这一步会触发pushToPop
        queue.add( 6 );


    }


    public static class TwoStackQueue {
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public TwoStackQueue() {
            stackPush = new Stack<>();
            stackPop = new Stack<>();
        }

        public void add(Integer data) {
            stackPush.push( data );
            pushToPop();
        }

        public Integer poll() {
            if (stackPush.isEmpty() && stackPop.isEmpty()) {
                throw new RuntimeException("队列为空");
            }
            pushToPop();
            return stackPop.pop();
        }

        public Integer peek() {
            if (stackPush.isEmpty() && stackPop.isEmpty()) {
                throw new RuntimeException("队列为空");
            }
            pushToPop();
            return stackPop.peek();
        }

        /**
         * 将stackPush栈数据倒到stackPop栈
         */
        public void pushToPop() {
            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push( stackPush.pop() );
                }
            }
        }


    }
}
