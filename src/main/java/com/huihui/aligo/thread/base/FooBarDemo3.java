package com.huihui.aligo.thread.base;

/**
 * leetcode题：交替打印FooBar
 * 方案：cas自旋锁 + flag
 * @author minghui.y
 * @create 2021-09-04 2:37 下午
 **/
public class FooBarDemo3 {

    public static void main( String[] args ) {

        FooBarDemo1.PrintTask task = new FooBarDemo1.PrintTask( 4 );

        new Thread(task::printFoo).start();
        new Thread(task::printBoo).start();

    }




    public static class PrintTask{

        private boolean flag = false;
        private int count;

        public PrintTask(int count) {
            this.count = count;
        }


        public void printFoo() {
            for (int i = 0;i < count;i++) {
                //cas自旋
                while (flag) {
                    //让不让除时间片，都不影响结果
//                    Thread.yield();
                }
                System.out.print("foo");
            }
        }


        public void printBar() {
            for (int i = 0;i < count;i++) {
                while (!flag) {
                    //让不让除时间片，都不影响结果
//                    Thread.yield();
                }
                System.out.print("bar");
            }
        }


    }
}
