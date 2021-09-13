package com.huihui.aligo.thread.leetcode.h2o_1117;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * leetcode题：H2O生成，1117
 * 题目描述：现在有两种线程，氧 oxygen 和氢 hydrogen，你的目标是组织这两种线程来产生水分子。（注意：两种线程，而不是两个线程！！！）
 *          输入H和O组成的字符串：
 *          输入字符串的总长将会是 3n, 1 ≤ n ≤ 50
 *          输入字符串中的 “H” 总数将会是 2n
 *          输入字符串中的 “O” 总数将会是 n
 *
 * 方案：用两个阻塞队列 + 计数
 *
 * @author minghui.y
 * @create 2021-09-08 11:00 上午
 **/
public class H2ODemo5 {

    public static void main( String[] args ) {
        String input = "OOOHHHHHH";
        H2OTask task = new H2OTask( input );
        new Thread(task::hydrogen).start();
        new Thread(task::oxygen).start();
    }


    public static class H2OTask {
        private int count = 0;
        private BlockingQueue<String> queueH = new LinkedBlockingDeque<>(2);
        private BlockingQueue<String> queueO = new LinkedBlockingDeque<>(1);

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
                try {
                    queueH.put( "H" );
                    System.out.print("H");
                    count++;
                    if (count >= 2) {
                        count = 0;
                        queueO.put( "O" );
                    }
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
                    queueO.take();
                    System.out.print("O");
                    queueH.clear();
                    queueO.clear();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }


    }


}
