package com.huihui.aligo.thread.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.LockSupport;

/**
 * leetcode题：交替打印FooBar
 * 方案：LockSupport.park()阻塞当前线程、LockSupport.unPark(Thread t)唤醒指定线程 + flag标志位控制谁先执行
 * 注意：若t线程在执行LockSupport.park()之前，其他线程执行过LockSupport.unPark(t)
 *       则t线程在执行LockSupport.park()时并不会阻塞！
 * @author minghui.y
 * @create 2021-09-04 4:14 下午
 **/
public class FooBarDemo6 {

    public static void main( String[] args ) {

        PrintTask task = new PrintTask( 4 );

        new Thread(task::printFoo).start();
        new Thread(task::printBar).start();


    }





    public static class PrintTask{

        private boolean flag = false;
        /**
         *
         */
        private Map<String, Thread> threadMap = new ConcurrentHashMap<>();

        private int count;
        public PrintTask(int count) {
            this.count = count;
        }

        public void printFoo() {
            threadMap.put( "foo", Thread.currentThread() );
            for (int i = 0;i < count;i++) {

                if (flag) {
                    //阻塞当前线程
                    LockSupport.park();
                }
                System.out.print("foo");
                flag = true;
                LockSupport.unpark( threadMap.get( "bar" ) );
            }
        }

        public void printBar() {
            threadMap.put( "bar", Thread.currentThread() );
            for (int i = 0;i < count;i++) {

                if (!flag) {
                    //阻塞当前线程
                    LockSupport.park();
                }
                System.out.print("bar");
                flag = false;
                LockSupport.unpark( threadMap.get( "foo" ) );
            }
        }


    }

}
