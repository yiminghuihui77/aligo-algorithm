package com.huihui.aligo.base;

/**
 * 位运算实现加法
 *
 * @author minghui.y
 * @create 2021-10-21 11:46 下午
 **/
public class BaseAlgorithm_23 {

    public static void main( String[] args ) {
        System.out.println(46 + 20);
        System.out.println(addByBit( 46, 20 ));
    }

    public static int addByBit(int a, int b) {
        //一开始sum = a ，如果b是0则直接返回a
       int sum = a;
       while (b != 0) {
           //s每一轮
           sum = a ^ b;
           b = (a & b) << 1;
           a = sum;
       }

        return sum;
    }


}
