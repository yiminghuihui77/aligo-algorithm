package com.huihui.aligo.thread.leetcode.forks_1226;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * leetcode题目：（1226题）
 *  描述：5位哲学家围绕而坐，每位哲学家左右两边各有一把叉子，同时拿起左右两把叉子才能吃意面，每把叉子同一时刻只能被一位哲学家拿起
 *  输入n，表示每位哲学家需要吃n次意面
 *  输出[a, b. c]：
 *       - a 哲学家编号。
 *       - b 指定叉子：{1 : 左边, 2 : 右边}.
 *       - c 指定行为：{1 : 拿起, 2 : 放下, 3 : 吃面}
 *
 * 思路：
 *    1、若5个哲学家都拿起一把叉子，那会因为等待别人释放叉子而陷入死锁
 *    2、若想至少有一个哲学家吃到意面，最多只能让四个人拿到叉子
 *
 *
 *
 * @author minghui.y
 * @create 2021-09-17 11:26 上午
 **/
public class DiningPhilosophersDemo1 {


    public static void main( String[] args ) {
        int eatTimes = 3;
        DiningPhilosophersTask task = new DiningPhilosophersTask( eatTimes );

        new Thread(() -> task.wantsToEat( 0 )).start();
        new Thread(() -> task.wantsToEat( 1 )).start();
        new Thread(() -> task.wantsToEat( 2 )).start();
        new Thread(() -> task.wantsToEat( 3 )).start();
        new Thread(() -> task.wantsToEat( 4 )).start();
    }


    public static class DiningPhilosophersTask {

        /**
         * 每个人吃面次数
         */
        private int eatTimes;
        public DiningPhilosophersTask(int eatTimes) {
            this.eatTimes = eatTimes;
        }

        //5把锁，表示5把叉子存在资源竞争的情况
        private Lock[] locks = {new ReentrantLock(),
                new ReentrantLock(),
                new ReentrantLock(),
                new ReentrantLock(),
                new ReentrantLock()};

        /**
         * 5个人每人都持有一把叉子，可能存在死锁的情况，因此限制最多4个人能拿叉子
         */
        private Semaphore semaphore = new Semaphore( 4 );

        /**
         * 哲学家编码0~4
         * @param philosopher
         */
        public void wantsToEat(int philosopher) {
           for (int i = 1;i<= eatTimes;i++) {
               //尝试获取资源
               try {
                   semaphore.acquire();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               //计算当前哲学家左右两把叉子的编号
               int leftFork = philosopher;
               int rightFork = (philosopher + 1) % 5;

               //尝试获取两把叉子的锁
               locks[leftFork].lock();
               locks[rightFork].lock();

               System.out.println("哲学家"+philosopher + "拿起左叉子" + leftFork);
               System.out.println("哲学家"+philosopher + "拿起右叉子" + rightFork);
               System.out.println("哲学家" + philosopher + "开始吃第" + i + "碗意面");
               try {
                   Thread.sleep( 2000 );
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("哲学家"+philosopher + "放下左叉子" + leftFork);
               System.out.println("哲学家"+philosopher + "放下右叉子" + rightFork);

               //释放两把叉子
               locks[leftFork].unlock();
               locks[rightFork].unlock();

               semaphore.release();
           }
        }



    }



}
