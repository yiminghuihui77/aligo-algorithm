package com.huihui.aligo.thread.leetcode.h2o_1117;

import java.util.concurrent.*;

/**
 * leetcode题：H2O生成，1117
 * 题目描述：现在有两种线程，氧 oxygen 和氢 hydrogen，你的目标是组织这两种线程来产生水分子。（注意：两种线程，而不是两个线程！！！）
 *            输入H和O组成的字符串：
 *            输入字符串的总长将会是 3n, 1 ≤ n ≤ 50
 *            输入字符串中的 “H” 总数将会是 2n
 *            输入字符串中的 “O” 总数将会是 n
 *  方案：两个Semaphore + CyclicBarrier  (模拟的方式是用线程池)
 *
 *  结果：
 * @author minghui.y
 * @create 2021-09-06 4:47 下午
 **/
public class H2ODemo4 {

    /*private static Semaphore semaphoreH = new Semaphore( 2 );
    private static Semaphore semaphoreO = new Semaphore( 1 );
    private static CyclicBarrier barrier = new CyclicBarrier( 3 );*/


    public static void main( String[] args ) {

        String input = "OOOHHHHHH";
        Semaphore semaphoreH = new Semaphore( 2 );
        Semaphore semaphoreO = new Semaphore( 1 );
        CyclicBarrier barrier = new CyclicBarrier( 3 );

        ThreadPoolExecutor executor = new ThreadPoolExecutor( 3, 3, 500, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10) );

        for (int i = 0;i < input.length();i++) {
            char element = input.charAt( i );
            executor.execute( new H2OTask( element, semaphoreH, semaphoreO, barrier ) );
        }

    }


    public static class H2OTask implements Runnable {

        private char element;
        private Semaphore semaphoreH;
        private Semaphore semaphoreO;
        private CyclicBarrier barrier;
        public H2OTask(char element, Semaphore semaphoreH, Semaphore semaphoreO, CyclicBarrier barrier) {
            this.element = element;
            this.semaphoreH = semaphoreH;
            this.semaphoreO = semaphoreO;
            this.barrier = barrier;
        }


        public void hydrogen() {
           try {
               semaphoreH.acquire();
               barrier.await();
               System.out.print("H");
               semaphoreH.release();
           } catch (Exception e) {
               e.printStackTrace();
            }
        }

        public void oxygen() {
            try {
                semaphoreO.acquire();
                barrier.await();
                System.out.print("O");
                semaphoreO.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            if ('H' == element) {
                hydrogen();
            } else  if ('O' == element) {
                oxygen();
            }
        }
    }


}
