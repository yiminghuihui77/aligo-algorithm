package com.huihui.aligo.thread.leetcode.forks_1226;

/**
 *
 * leetcode题目：（1226题）
 *   描述：5位哲学家围绕而坐，每位哲学家左右两边各有一把叉子，同时拿起左右两把叉子才能吃意面，每把叉子同一时刻只能被一位哲学家拿起
 *   输入n，表示每位哲学家需要吃n次意面
 *   输出[a, b. c]：
 *        - a 哲学家编号。
 *        - b 指定叉子：{1 : 左边, 2 : 右边}.
 *        - c 指定行为：{1 : 拿起, 2 : 放下, 3 : 吃面}
 *
 *
 *    方案：Thread.yield() + 标志位
 *
 * @author minghui.y
 * @create 2021-09-17 5:00 下午
 **/
public class DiningPhilosophersDemo2 {

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

        /**
         * 叉子的状态，true表示空闲，false表示被占用
         */
        private boolean[] forkStatus = {true, true, true, true, true};


        /**
         * 哲学家编码0~4
         * @param philosopher
         */
        public void wantsToEat(int philosopher) {

            for (int i = 1;i<= eatTimes;i++) {

                //计算当前哲学家的叉子编号
                int leftFork = philosopher;
                int rightFork = (philosopher + 1) % 5;

                //尝试获取左叉子
                while (!forkStatus[leftFork]) {
                    //被占用则一直让出时间片
                    Thread.yield();
                }
                //这一步没法控制与上一步的原子性！有可能被两位哲学家同时拿起
                forkStatus[leftFork] = false;

                //尝试获取右叉子
                while (!forkStatus[rightFork]) {
                    //被占用则一直让出时间片
                    Thread.yield();
                }
                //这一步没法控制与上一步的原子性！有可能被两位哲学家同时拿起
                forkStatus[rightFork] = false;

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

                System.out.println();

                //释放叉子
                forkStatus[leftFork] = true;
                forkStatus[rightFork] = true;
            }



        }
    }
}
