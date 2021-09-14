package com.huihui.aligo.thread.leetcode.fizzbuzz_1195;

import java.util.concurrent.Semaphore;

/**
 * leetcode题目：
 *  编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 *
 *  如果这个数字可以被 3 整除，输出 "fizz"。
 *  如果这个数字可以被 5 整除，输出 "buzz"。
 *  如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 *  例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 *
 *  方案：四个线程四个Semaphore
 *
 *  性能：执行耗时:7 ms,击败了99.91% 的Java用户 内存消耗:38.6 MB,击败了33.79% 的Java用户
 *
 * @author minghui.y
 * @create 2021-09-14 11:20 上午
 **/
public class FizzBuzzDemo2 {

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

        private Semaphore semaphoreNum = new Semaphore( 1 );
        private Semaphore semaphoreFizz = new Semaphore( 0 );
        private Semaphore semaphoreBuzz = new Semaphore( 0 );
        private Semaphore semaphoreFizzBuzz = new Semaphore( 0 );


        public void fizz() {
            for (int i = 3;i <= input;i+= 3) {
                //leetcode中没有这判断执行超时
                if (i % 15 ==0) {
                    continue;
                }
                try {
                    semaphoreFizz.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("fizz, ");
                semaphoreNum.release();
            }
        }

        public void buzz() {
            for (int i = 5;i <= input;i+= 5) {
                //leetcode中没有这判断执行超时
                if (i % 15 ==0) {
                    continue;
                }
                try {
                    semaphoreBuzz.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("buzz, ");
                semaphoreNum.release();
            }
        }

        public void fizzBuzz() {
            for (int i = 15;i<= input;i+= 15) {
                try {
                    semaphoreFizzBuzz.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("fizzbuzz, ");
                semaphoreNum.release();
            }
        }

        public void number() {
            for (int i = 1;i<= input;i++) {
                try {
                    semaphoreNum.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i % 3 != 0 && i % 5 != 0) {
                    System.out.print(i + ", ");
                    //注意：释放许可
                    semaphoreNum.release();
                } else {
                    if (i % 15 == 0) {
                        semaphoreFizzBuzz.release();
                    } else if (i % 5 == 0) {
                        semaphoreBuzz.release();
                    } else {
                        semaphoreFizz.release();
                    }
                }
            }
        }

    }

}
