package com.huihui.aligo.thread.leetcode.h2o_1117;

import java.util.concurrent.CyclicBarrier;

/**
 *   leetcode题：H2O生成，1117
 *   题目描述：输入H和O组成的字符串：
 *            输入字符串的总长将会是 3n, 1 ≤ n ≤ 50
 *            输入字符串中的 “H” 总数将会是 2n
 *            输入字符串中的 “O” 总数将会是 n
 *
 *  方案：CyclicBarrier + 计数
 *       线程H的任务时输出两个H，完成任务后在屏障前等待
 *       线程O的任务时输出一个O，完成任务后在屏障前等待
 *
 *  结果：一个水分子的两个H和O的输出顺序，按理说是不一定的，取决于线程的调度
 *  注意：在leetcode中解答失败
 *
 * @author minghui.y
 * @create 2021-09-06 3:03 下午
 **/
public class H2ODemo2 {


    public static void main( String[] args ) {
        //输入，输出：HHO-OHH-HHO-
        String input = "OOOHHHHHH";

        H2OTask task = new H2OTask( input );

        new Thread(task::hydrogen).start();
        new Thread(task::oxygen).start();
    }



    public static class H2OTask {

        private int count = 0;
        private CyclicBarrier barrier = new CyclicBarrier( 2, () -> {
            System.out.print("-");
        } );
        private String input;
        public H2OTask(String input) {
            this.input = input;
        }

        public void hydrogen() {
            for (int i = 0;i < input.length();i++) {
                char charAt = input.charAt( i );
                if ('O' == charAt) {
                    continue;
                }
                System.out.print("H");
                count++;
                if (count >= 2) {
                    count = 0;
                    try {
                        //遇到两个H则在屏障前等待
                        barrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        public void oxygen() {
            for (int i = 0;i < input.length();i++) {
                char charAt = input.charAt( i );
                if ('H' == charAt) {
                    continue;
                }
                //遇到一个O输出，并在屏障前等待
                System.out.print("O");
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }




    }

}
