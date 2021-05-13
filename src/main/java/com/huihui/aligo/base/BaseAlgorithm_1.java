package com.huihui.aligo.base;

/**
 * 打印int整数(32位)的二进制(补码)
 * 位运算符：&、<<
 * 思路：32位的整数，通过1左移31~0与num作&运算，从最高位依次向最低位取数；
 * @author minghui.y
 * @create 2021-05-12 10:33 下午
 **/
public class BaseAlgorithm_1 {

    public static void main( String[] args ) {

        // -7 的补码：11111111111111111111111111111001

        print( -7 );
    }

    public static void print(int num) {
        for (int i = 31;i >=0; i--) {
            //必须跟0比较
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
    }

}
