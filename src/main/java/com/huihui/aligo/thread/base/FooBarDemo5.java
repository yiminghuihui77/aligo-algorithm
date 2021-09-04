package com.huihui.aligo.thread.base;

import java.util.concurrent.Semaphore;

/**
 * leetcode题：交替打印FooBar
 * 方案：两个Semaphore控制谁首次执行和顺序执行
 * @author minghui.y
 * @create 2021-09-04 3:51 下午
 **/
public class FooBarDemo5 {

    public static void main( String[] args ) {
        FooBarDemo1.PrintTask task = new FooBarDemo1.PrintTask( 4 );

        new Thread(task::printFoo).start();
        new Thread(task::printBoo).start();

    }

    public static class PrintTask{

        private int count;

        public PrintTask(int count) {
            this.count = count;
        }

        /**
         * 信号量控制顺序执行
         * fooSemaphore初识为1，保证printFoo首先执行
         * barSemaphore初始为0，保证printBar首次不能被执行
         */
        private Semaphore fooSemaphore = new Semaphore( 1 );
        private Semaphore barSemaphore = new Semaphore( 0 );

        public void printFoo() {
            for (int i = 0;i < count;i++) {
                try {
                    //首次就能执行
                    fooSemaphore.acquire();
                    System.out.print("foo");
                    //这行执行后，printBar才能执行
                    barSemaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void printBar() {
            for (int i = 0;i < count;i++) {
                try {
                    //首次获取不到
                    barSemaphore.acquire();
                    System.out.print("bar");
                    //这行执行后，printFoo才能继续执行
                    fooSemaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }



    }
}
