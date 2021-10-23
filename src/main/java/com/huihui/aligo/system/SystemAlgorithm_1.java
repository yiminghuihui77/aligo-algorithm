package com.huihui.aligo.system;

/**
 * 异或的常见用法
 *
 * @author minghui.y
 * @create 2021-10-23 9:28 下午
 **/
public class SystemAlgorithm_1 {

    public static void main( String[] args ) {

//        testExchange();

//        testFindOddNum();

//        testMostRightOne();

        testFindTwoOddNum();
    }


    /**
     * 不使用第三个变量，交换a和b
     * 前提是a和b是两个独立的变量，而不是同一块内存地址，否则刷成0
     */
    public static void testExchange() {
        int a = 17;
        int b = 13;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a:" + a);
        System.out.println("b:" + b);
    }


    /**
     * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
     */
    public static void testFindOddNum() {
        int ans = 0;
        int[] arr = {1,2,2,1,4,3,5,3,5};

        for (int i = 0;i < arr.length;i++) {
            ans ^= arr[i];
        }
        System.out.println("出现奇数次的数是：" + ans);
    }


    public static void testMostRightOne() {
        int a = 8;
        System.out.println("a的二进制：");
        print( a );

        System.out.println("-a的二进制：");
        print( -a );

        int ans = a & -a;
        System.out.println("a左右侧1：");
        print( ans );
    }


    /**
     * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
     * 假设a和b出现了奇数次
     */
    public static void testFindTwoOddNum() {
        //6和8出现了奇数次
        int[] arr = {2,1,1,6,2,8,10,10,8,8};

        int ans = 0;
        for (int i = 0;i < arr.length;i++) {
            //由于a和b出现了奇数次，其他数出现偶数次,则ans最后的结果是a^b
            ans ^= arr[i];
        }

        //在ans中随便找出一位不为1的，说明a和b在该位不一致,现提取ans最后一位不为1的数，假设为n
        int erp = ans & -ans;

        //将arr数组分为两类：第n为为1的数、第n位不为1的数，则a和b肯定在不同的两类中
        //假设a在第n为为1的数中
        int ans2 = 0;
        for (int i = 0;i < arr.length;i++) {
            if ((arr[i] & erp) != 0) {
                //对数组中第n为1的数进行异或，结果就是a
                ans2 ^= arr[i];
            }
        }

        System.out.println("出现奇数次的数分别是：" + ans2 + "和" + (ans2 ^ ans));
    }










    /**
     * 打印二进制
     * @param num
     */
    public static void print(int num) {
        for (int i = 31;i >=0; i--) {
            //必须跟0比较
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

}
