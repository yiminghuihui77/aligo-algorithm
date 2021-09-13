package com.huihui.aligo.thread.leetcode.h2o_1117;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * leetcode题：H2O生成，1117
 * 题目描述：输入H和O组成的字符串：
 *             输入字符串的总长将会是 3n, 1 ≤ n ≤ 50
 *             输入字符串中的 “H” 总数将会是 2n
 *             输入字符串中的 “O” 总数将会是 n
 *
 * 方案：两个Semaphore + CyclicBarrier
 * 结果：本地运行失败，因为下面那种方式用两个线程执行，会一直阻塞！
 * @author minghui.y
 * @create 2021-09-06 4:31 下午
 **/
public class H2ODemo3 {

    public static void main( String[] args ) {
        String input = "OOOHHHHHH";

        H2OTask task = new H2OTask( input );

        new Thread(task::hydrogen).start();
        new Thread(task::oxygen).start();
    }


    public static class H2OTask {
        private String input;
        public H2OTask(String input) {
            this.input = input;
        }

        private Semaphore semaphoreH = new Semaphore( 2 );
        private Semaphore semaphoreO = new Semaphore( 1 );
        private CyclicBarrier barrier = new CyclicBarrier( 3 );


        public void hydrogen() {
            for (int i = 0;i < input.length();i++) {
                char charAt = input.charAt( i );
                if ('O' == charAt) {
                    continue;
                }
               try {
                   semaphoreH.acquire();
                   //由于是两个线程，H线程会一直阻塞在屏障前(没有第三个线程会到达屏障前)
                   barrier.await();
                   System.out.print("H");
                   semaphoreH.release();
               } catch (Exception e) {
                   e.printStackTrace();
               }
            }
        }


        public void oxygen() {
            for (int i = 0;i < input.length();i++) {
                char charAt = input.charAt( i );
                if ('H' == charAt) {
                    continue;
                }
                try {
                    semaphoreO.acquire();
                    //由于是两个线程，O线程会一直阻塞在屏障前(没有第三个线程会到达屏障前)
                    barrier.await();
                    System.out.print("O");
                    semaphoreO.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }



}
