package com.huihui.aligo.thread.base;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * leetcode题：交替打印FooBar
 * 方案：利用阻塞队列天然的阻塞性：put和take动作，都是阻塞的
 * @author minghui.y
 * @create 2021-09-04 3:27 下午
 **/
public class FooBarDemo4 {

    public static void main( String[] args ) {

        FooBarDemo1.PrintTask task = new FooBarDemo1.PrintTask( 4 );

        new Thread(task::printFoo).start();
        new Thread(task::printBoo).start();

    }


    public static class PrintTask{


        private int count;
        /**
         * 阻塞队列的put和take动作，都是阻塞的
         */
        private BlockingQueue<Integer> fooQueue = new LinkedBlockingDeque<>(1);
        private BlockingQueue<Integer> barQueue = new LinkedBlockingDeque<>(1);

        public PrintTask(int count) {
            this.count = count;
        }


        public void printFoo() {
            for (int i = 0;i < count;i++) {

                try {
                    //往fooQueue中插入，满了则阻塞
                    fooQueue.put( 1 );
                    System.out.print("foo");
                    //这行执行之后，printBar才能执行！！！
                   barQueue.put( 1 );

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        public void printBar() {
            for (int i = 0;i < count;i++) {

                try {
                    barQueue.take();
                    System.out.print("bar");
                    //这行执行之后，printFoo才能继续执行
                    fooQueue.take();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
