package com.huihui.aligo.base;

/**
 * 计算法计算：1! + 2! + 3! + ... + N!
 *
 * @author minghui.y
 * @create 2021-05-16 4:22 下午
 **/
public class BaseAlgorithm_2 {


    public static void main( String[] args ) {


        System.out.println(f1( 5 ));

        System.out.println(f2( 5 ));  //153


    }


    /**
     * 思路：计算1~N每个数的阶乘，然后累加起来
     * @param N
     * @return
     */
    public static long f1(int N) {
        long startTime = System.currentTimeMillis();
        int ans = 0;
        for (int i = 1;i <= N;i++) {
            ans += f1_1( i );
        }
        System.out.println("f1耗时：" + (System.currentTimeMillis() - startTime));
        return ans;
    }

    /**
     * 计算N的阶乘
     * @param N
     * @return
     */
    public static long f1_1(int N) {
        int ans = 1;
        for (int i = 1;i <= N;i++) {
            ans *= i;
        }
        return ans;
    }


    /**
     * 思路：N的阶乘 = N * (N -1)的阶乘
     * 优势：计算N的阶乘时，充分利用了之前N-1阶乘的结果
     * @param N
     * @return
     */
    public static long f2(int N) {
        long startTime = System.currentTimeMillis();
        int ans = 0;
        int cur = 1;
        for (int i = 1;i <= N;i++) {
            cur = cur * i;
            ans = ans + cur;
        }
        System.out.println("f2耗时：" + (System.currentTimeMillis() - startTime));
        return ans;
    }


}
