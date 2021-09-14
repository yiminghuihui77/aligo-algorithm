package com.huihui.aligo.thread.leetcode.fizzbuzz_1195;

/**
 * leetcode题目：
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 *
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 *
 * 方案：Thread.yield() + 标志位
 *
 * 性能：执行耗时:8 ms,击败了61.81% 的Java用户 内存消耗:38.6 MB,击败了44.83% 的Java用户
 *
 * 细节&心得：每个线程只专注打印自己关注的数字(i的值)，然后通过标志位传递信息(打印的时机)
 *
 * @author minghui.y
 * @create 2021-09-14 10:00 上午
 **/
public class FizzBuzzDemo1 {

    public static void main( String[] args ) {
        int input  = 10;
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
        /**
         * 0: 打印数字
         * 1：打印fizz
         * 2：打印buzz
         * 3：打印fizzbuzz
         */
        private int flag = 0;


        public void fizz() {
            for (int i = 3;i <= input;i+= 3) {
                if (i % 15 == 0) {
                    continue;
                }
                while (flag != 1){
                    Thread.yield();
                }
                System.out.print("fizz, ");
                flag = 0;
            }
        }

        public void buzz() {
            for (int i = 5;i <= input;i+= 5) {
                if (i % 15 == 0) {
                    continue;
                }
                while (flag != 2){
                    Thread.yield();
                }
                System.out.print("buzz, ");
                flag = 0;
            }
        }

        public void fizzBuzz() {
            for (int i = 15;i <= input;i+= 15) {
                while (flag != 3 ){
                    Thread.yield();
                }
                System.out.print("fizzbuzz, ");
                flag = 0;
            }
        }

        public void number() {
            for (int i = 1;i <= input;i++) {
                while (flag != 0){
                    Thread.yield();
                }
                if (i % 3 != 0 && i % 5 != 0) {
                    System.out.print(i + ", ");
                } else {
                    if (i % 15 ==0) {
                        flag = 3;
                    } else if (i % 3 == 0) {
                        flag = 1;
                    } else {
                        flag = 2;
                    }
                }
            }
        }


    }



}
