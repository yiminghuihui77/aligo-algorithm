package com.huihui.aligo.thread.leetcode.fizzbuzz_1195;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * leetcode题目：
 *  编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 *
 *  如果这个数字可以被 3 整除，输出 "fizz"。
 *  如果这个数字可以被 5 整除，输出 "buzz"。
 *  如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 *  例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 *
 *  方案：ReentrantLock + 4个Condition
 *
 *  性能：执行耗时:7 ms,击败了99.91% 的Java用户，内存消耗:38.8 MB,击败了5.21% 的Java用户
 *
 *  结果：LeetCode通过，本地偶尔成功
 *
 * @author minghui.y
 * @create 2021-09-16 4:53 下午
 **/
public class FizzBuzzDemo3 {

    public static void main( String[] args ) {
        int input  = 31;
        PrintTask task = new PrintTask( input );

        new Thread(task::fizz).start();
        new Thread(task::buzz).start();
        new Thread(task::fizzBuzz).start();
        new Thread(task::number).start();
    }


    public static class PrintTask {

        private int input;
        public PrintTask(int input) {
            this.input = input;
        }

        Lock lock = new ReentrantLock(true);
        Condition conditionFizz = lock.newCondition();
        Condition conditionBuzz = lock.newCondition();
        Condition conditionFizzBuzz = lock.newCondition();
        Condition conditionNumber = lock.newCondition();


        public void fizz() {
            for (int i = 3;i<= input;i+= 3) {
                if (i % 15 == 0) {
                    continue;
                }
                lock.lock();
                try {
                    conditionFizz.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("fizz, ");
                conditionNumber.signal();
                lock.unlock();
            }
        }

        public void buzz() {
            for (int i = 5;i<= input;i+= 5) {
                if (i % 15 == 0) {
                    continue;
                }
                lock.lock();
                try {
                    conditionBuzz.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("buzz, ");
                conditionNumber.signal();
                lock.unlock();
            }
        }

        public void fizzBuzz() {
            for (int i = 15;i<= input;i+= 15) {
                lock.lock();
                try {
                    conditionFizzBuzz.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("fizzbuzz, ");
                conditionNumber.signal();
                lock.unlock();
            }
        }

        public void number() {
            for (int i = 1;i<= input;i++) {
                lock.lock();
             if (i % 3 != 0 && i % 5 != 0) {
                 System.out.print(i + ", ");
             } else {
                 if (i % 15 == 0) {
                     conditionFizzBuzz.signal();
                 } else if (i % 5 == 0) {
                     conditionBuzz.signal();
                 } else {
                     conditionFizz.signal();
                 }
                 //非数字，唤醒其他，阻塞自己
                 try {
                     conditionNumber.await();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
                lock.unlock();
            }
        }
    }


}
