package com.huihui.aligo.base;

/**
 * 位图:存储某个数值
 * 思路：一个long类型整数有64位，从低位到高位，能表示0 ~ 63范围的数值是否出现过
 *      使用两个long就能表示0~127范围的数值，以此类推：数值N需要(N+64)/64个long进行存储
 *
 * 位运算：<< 、>> 、& 、| 、~ 、^ 速度快于算术原始10倍以上
 * @author minghui.y
 * @create 2021-10-21 4:52 下午
 **/
public class BaseAlgorithm_22 {

    public static void main( String[] args ) {
       /* System.out.println(170 / 64);
        System.out.println(170 >> 6);

        System.out.println(170 % 64);
        System.out.println(170 & 63);
        //可见，N % Y = N & (Y-1)不通用
        System.out.println(170 % 66);
        System.out.println(170 & 65);*/

        System.out.println(170 % 64);
        BitMap bitMap = new BitMap( 200 );
        bitMap.add( 170 );
//        bitMap.add( 2 );

        printAll( bitMap.bits );

     /*   System.out.println("bitMap中是否包含170：" + bitMap.contains( 170 ));
        System.out.println("bitMap中是否包含2：" + bitMap.contains( 2 ));

        bitMap.delete( 170 );
        bitMap.delete( 2 );
        System.out.println("bitMap中是否包含170：" + bitMap.contains( 170 ));
        System.out.println("bitMap中是否包含2：" + bitMap.contains( 2 ));*/

    }

    public static class BitMap {

        private long[] bits;

        public long[] getBits() {
            return bits;
        }

        public BitMap(int max) {
            //存储的最大数值，评估需要几个long去存储
            bits = new long[(max + 64) / 64];
        }

        public void add(int num) {
            //1、判断当前数落到哪个下标 num / 64 = num >> 6
            //2、指定下标的long的哪一位表示当前数值  num % 64 = num & 63
            //注意不是1左移而是1L左移，否则32位的int类型的1左移超出范围会出问题
            bits[num >> 6] |= (1L << (num & 63));
            print( bits[num >> 6] );
        }

        public void delete(int num) {
            bits[num >> 6] &= ~(1L << (num & 63));
            print( bits[num >> 6] );
        }

        /**
         * 判断一个数是否被包含
         * @param num
         * @return
         */
        public boolean contains(int num) {
            return (bits[num >> 6] & (1L << (num & 63))) != 0;
        }



    }
    public static void print(long num) {
        int count = 0;
        for (int i = 63;i >=0; i--) {
            //必须跟0比较
            //注意1L而不是1
            System.out.print((num & (1L << i)) == 0 ? "0" : "1");
            if (++count == 8) {
                count = 0;
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static void printAll(long[] bits) {
        System.out.println("------------------------------");
        for (int i = 0;i < bits.length;i++) {
            print( bits[i] );
            System.out.println();
        }
        System.out.println("------------------------------");

    }
}
