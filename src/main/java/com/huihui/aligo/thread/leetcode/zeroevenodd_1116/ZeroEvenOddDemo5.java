package com.huihui.aligo.thread.leetcode.zeroevenodd_1116;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.LockSupport;

/**
 *
 * leetcode题目：三个线程，每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 *    输入：n = 2
 *    输出："0102"
 *    说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用od
 *
 *    方案：LockSupport
 *
 *    性能：执行耗时:9 ms,击败了37.68% 的Java用户 内存消耗:37.4 MB,击败了87.88% 的Java用户
 *
 * @author minghui.y
 * @create 2021-09-13 11:42 上午
 **/
public class ZeroEvenOddDemo5 {

    public static void main( String[] args ) {
        int endNum = 9;
        ZeroEvenOddDemo4.PrintTask task = new ZeroEvenOddDemo4.PrintTask( endNum );

        new Thread(task::zero).start();
        new Thread(task::even).start();
        new Thread(task::odd).start();
    }

    public static class PrintTask {

        private int input;
        public PrintTask(int input) {
            this.input = input;
        }

        private int flag = 0;
        private Map<String, Thread> threadMap = new ConcurrentHashMap<>(3);


        public void zero() {
            threadMap.put( "threadZero", Thread.currentThread() );
            for (int i = 1; i<= input;i++) {
                if (flag != 0) {
                    LockSupport.park();
                }
                System.out.print(0);
                if (i % 2 == 0) {
                    flag = 2;
                    LockSupport.unpark( threadMap.get( "threadEven" ) );
                } else {
                    flag = 1;
                    LockSupport.unpark( threadMap.get( "threadOdd" ) );
                }
            }
        }

        public void even() {
            threadMap.put( "threadEven", Thread.currentThread() );
            for (int i = 2;i <= input;i+= 2) {
                if (flag != 2) {
                    LockSupport.park();
                }
                System.out.print(i);
                flag = 0;
                LockSupport.unpark( threadMap.get( "threadZero" ) );
            }
        }

        public void odd() {
            threadMap.put( "threadOdd", Thread.currentThread() );
            for (int i = 1;i <= input;i+= 2) {
                if (flag != 1) {
                    LockSupport.park();
                }
                System.out.print(i);
                flag = 0;
                LockSupport.unpark( threadMap.get( "threadZero" ) );
            }
        }
    }

}
