package com.huihui.aligo.thread.leetcode.h2o_1117;

import java.util.concurrent.Semaphore;

/**
 * leetcode题：H2O生成，1117
 * 题目描述：现在有两种线程，氧 oxygen 和氢 hydrogen，你的目标是组织这两种线程来产生水分子。（注意：两种线程，而不是两个线程！！！）
 *          输入H和O组成的字符串：
 *          输入字符串的总长将会是 3n, 1 ≤ n ≤ 50
 *          输入字符串中的 “H” 总数将会是 2n
 *          输入字符串中的 “O” 总数将会是 n
 *
 *  方案：两个Semaphore控制H和O的输出
 *  线程H：每遇到一个H就会尝试获取一个H的许可，获得许可后输出H并释放一个O的许可
 *  线程O：每遇到一个O就会尝试获取两个O的许可,获得许可后输出O并释放两个H的许可
 *
 *  结果：这种方式会导致一定会先输出两个H再输出一个O
 *
 * @author minghui.y
 * @create 2021-09-06 2:19 下午
 **/
public class H2ODemo1 {

    public static void main( String[] args ) {
        //输入，输出：HHOHHOHHO
        String input = "OOOHHHHHH";

        H2OTask task = new H2OTask( input );

        //两个线程实现，与LeetCode实现大概率不一致
        new Thread(task::hydrogen).start();
        new Thread(task::oxygen).start();
    }







    public static class H2OTask {

        private Semaphore semaphoreH = new Semaphore( 2 );
        private Semaphore semaphoreO=  new Semaphore( 0 );

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
                    //
                    semaphoreH.acquire(1);
                    System.out.print("H");
                    semaphoreO.release(1);
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
                    //阻塞，直到已经输出2个H
                    semaphoreO.acquire(2);
                    System.out.print("O");
                    //释放两个H的许可，让H线程能够继续执行
                    semaphoreH.release(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
