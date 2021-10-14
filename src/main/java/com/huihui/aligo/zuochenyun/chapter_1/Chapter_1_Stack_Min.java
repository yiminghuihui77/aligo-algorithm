package com.huihui.aligo.zuochenyun.chapter_1;

import java.util.Stack;

/**
 * 第一章：栈和队列
 *  设计一个有getMin功能的栈：返回栈中最小的元素
 *  要求：push、pop、getMin操作的时间复杂度都是O(1)
 *
 *  方案：使用两个栈结构实现：stackData存储原始数据；stackMin存储每次操作的最小值
 *  入栈：当前入栈值 > stackMin栈顶值时，stackMin要不不入栈，要么入栈stackMin栈顶值
 *
 * @author minghui.y
 * @create 2021-10-09 3:35 下午
 **/
public class Chapter_1_Stack_Min {

    public static void main( String[] args ) {

        MyStack stack = new MyStack();

        stack.push( 3 );
        stack.push( 4 );
        stack.push( 5 );
        stack.push( 1 );
        stack.push(2 );
        stack.push( 1 );

        stack.printDataStack();
        stack.printMinStack();


    }


    /**
     * 栈
     */
    private static class MyStack {

        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack() {
            stackData = new Stack<>();
            stackMin = new Stack<>();
        }

        /**
         * 入栈
         * @param t
         */
        public void push(Integer t) {
            //先压入stackData
            stackData.push( t );

            if (stackMin.isEmpty()) {
                //首次
                stackMin.push( t );
            } else {
                //比较当前值与stackMin栈顶的值的大小
                int min = stackMin.peek();
                if (t <= min) {
                    //当前值小于等于最小值，则入栈，否则不入栈
                    stackMin.push( t );
                }
            }
        }

        /**
         * 出栈
         * @return
         */
        public Integer pop() {
            //stockData先出栈，并记录数据
            Integer t = stackData.pop();
            if (t == stackMin.peek()) {
                //当前出栈值是最小值，则在stackMin中出栈
                stackMin.pop();
            }
            return t;
        }

        /**
         * 获取最小元素值
         * @return
         */
        public Integer getMin() {
            return stackMin.peek();
        }



        public void printDataStack() {
            System.out.println("-----stackData栈打印----");
            stackData.forEach(System.out::println);
            System.out.println("-----stackData栈打印----");
        }

        public void printMinStack() {
            System.out.println("-----stackMin栈打印----");
            stackMin.forEach(System.out::println);

            System.out.println("-----stackMin栈打印----");
        }


    }
}
