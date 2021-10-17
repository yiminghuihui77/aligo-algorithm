package com.huihui.aligo.base;

/**
 * 已知函数f()到不等概率(但固定概率)返回0和1，请给出函数h()，以等概率返回0和1
 *
 * 思路：
 * 假设f()函数不等概率（但固定概率）返回[0,1]，假设返回0的概率是P，返回1的概率是1-P
 * 两次算f()，可能的取值和概率分别是：
 * 0 0 : P * P
 * 0 1 : P * (1-P)  √
 * 1 0 : (1-P) * P  √
 * 1 1 : (1-P) * (1-P)
 *
 * 上述可知，两次结果值不一致是等概率事件，否则重算
 * @author minghui.y
 * @create 2021-10-17 2:10 下午
 **/
public class BaseAlgorithm_9 {

    public static void main( String[] args ) {

        //测试f()函数
//        testF();

        //测试h()函数
        testH();

    }

    /**
     * f()函数以不等概率(但固定概率)返回0和1
     * 返回0的概率是0.85，返回1的概率是0.15
     * @return
     */
    public static int f() {
        return Math.random() < 0.85 ? 0 : 1;
    }

    public static void testF() {
        int tryTimes = 1000000;
        int count = 0;
        for (int i = 0;i < tryTimes;i++) {
            if (f() == 0) {
                count++;
            }
        }
        System.out.println("f()返回0的概率是：" + (double) count / (double) tryTimes);
    }

    /**
     * 目标函数h()，以等概率返回0或1
     * @return
     */
    public static int h() {
        int ans = 0;
        do {
            ans = f();
            //第一次结果与第二次结果一致，则一致重算，直到不一致
        } while (ans == f());
        return ans;
    }

    public static void testH() {
        int tryTimes = 1000000;
        int count = 0;
        for (int i = 0;i < tryTimes;i++) {
            if (h() == 0) {
                count++;
            }
        }
        System.out.println("h()返回0的概率是：" + (double) count / (double) tryTimes);
    }



}
