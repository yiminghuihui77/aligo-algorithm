package com.huihui.aligo.base;

/**
 * Math.random()在[0,1)范围内等概率返回一个double的数值
 *
 * @author minghui.y
 * @create 2021-10-17 10:53 上午
 **/
public class BaseAlgorithm_7 {


    public static void main( String[] args ) {

        //测Math.random()在[0,1)范围内等概率返回数值
        testRandom(0.6);

        //测Math.random() * K 将在[0,k)范围内等概率返回一个数值，向下取整后将在[0,k-1]范围内等概率返回一个整数
        testRandomPlus( 9 );

        //
        xtoPower2(0.6);

    }

    /**
     * 测试Math.random()在[0,1)范围内等概率返回一个数值
     * @param k
     */
    public static void testRandom(double k) {
        int tryTimes = 1000000;
        int count = 0;
        for (int i = 0;i< tryTimes;i ++) {
            if (Math.random() < k) {
                count++;
            }
        }
        System.out.println((double)(count) / (double) (tryTimes));
    }

    /**
     * 已知Math.random()在[0,1)范围内等概率返回一个数值
     * 则Math.random() * K 将在[0,k)范围内等概率返回一个数值，向下取整后将在[0,k-1]范围内等概率返回一个整数！
     */
    public static void testRandomPlus(int k) {
        int tryTimes = 1000000;
        //词频计数
        int[] count = new int[k];
        for (int i = 0;i< tryTimes;i ++) {
            int ans = (int) (Math.random() * k);
            count[ans]++;
        }
        //结果是0~8范围内每个整数出现的次数相近，即等概率出现
        for (int i =0;i<k;i++) {
            System.out.println(i + "出现的次数：" + count[i]);
        }
    }


    /**
     * x在[0,1)范围内，连续两次Math.random()落在[0,x)范围内的概率就是x^2
     * Math.random()落在[0,x)的概率是x，连续落在该范围的概率就是x * x
     * @param x
     * @return
     */
    public static void xtoPower2(double x) {

        int tryTimes = 1000000;
        int count = 0;

        for (int i = 0;i < tryTimes;i++) {
            if (Math.max( Math.random(), Math.random() ) < x) {
                count++;
            }
        }
        System.out.println(x + "的平方是：" + ((double) count) / ((double) tryTimes));
        System.out.println(Math.pow( x, 2 ));
    }



}
