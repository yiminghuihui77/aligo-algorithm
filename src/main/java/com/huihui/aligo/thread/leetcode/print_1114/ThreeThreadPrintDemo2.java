package com.huihui.aligo.thread.leetcode.print_1114;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.LockSupport;

/**
 * leetcode题：三个线程按顺序打印
 * 描述：线程A打印first，线程B打印second，线程C打印third
 *
 * 方案：LockSupport + 标志位
 *
 * 性能：
 *     执行耗时:13 ms,击败了55.02% 的Java用户
 * 	   内存消耗:37.8 MB,击败了69.51% 的Java用户
 * @author minghui.y
 * @create 2021-09-05 12:38 下午
 **/
public class ThreeThreadPrintDemo2 {

    public static void main( String[] args ) {

        ThreeThreadPrintDemo1.PrintTask printTask = new ThreeThreadPrintDemo1.PrintTask();

        new Thread(printTask::printFirst).start();
        new Thread(printTask::printSecond).start();
        new Thread(printTask::printThird).start();

    }



    public static class PrintTask{

        private int flag = 1;
        private Map<String, Thread> threadMap = new ConcurrentHashMap<>(3);


        public void printFirst() {
            threadMap.put( "A", Thread.currentThread() );
            if (flag != 1) {
                LockSupport.park();
            }
            System.out.print("first");
            flag = 2;
            LockSupport.unpark( threadMap.get( "B" ) );
        }


        public void printSecond() {
            threadMap.put( "B" , Thread.currentThread());
            if (flag != 2) {
                LockSupport.park();
            }
            System.out.print("second");
            flag = 3;
            LockSupport.unpark( threadMap.get( "C" ) );
        }


        public void printThird() {
            threadMap.put( "C", Thread.currentThread() );
            if (flag != 3) {
                LockSupport.park();
            }
            System.out.print("third");
            flag = 1;
            LockSupport.unpark( threadMap.get( "A" ) );
        }

    }
}
