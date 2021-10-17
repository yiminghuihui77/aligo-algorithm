package com.huihui.aligo.base;

/**
 * 给定一个数组，求数组下标L到R的元素之和
 * 假设存在数组：arr[]，长度为N
 *
 * 思路：
 * 1、最直接的方式是每次遍历数组，从下标L到R累加元素获取结果值
 * 2、对数组进行预处理
 *    方式一：二维数组存储  h[L][R]表示arr[L]~arr[R]的累加结果
 *    方式二：一位数组存储，h[i]表示arr[0]~arr[i]的累加结果，arr[L]~arr[R]的累加结果 = h[R] - h[L-1]
 *
 * @author minghui.y
 * @create 2021-10-17 12:36 上午
 **/
public class BaseAlgorithm_6 {

    private static int[] h;


    public static void main( String[] args ) {

        int[] arr = {2,1,3,7,5,4};

        System.out.println(subSum( arr, 1, 3 ));
        System.out.println(subSum( arr, 0, 3 ));
        System.out.println(subSum( arr, 2, 3 ));
    }

    public static int subSum(int[] arr, int l, int r) {
        //数组下标检测
        if (arr == null || arr.length == 0 || l < 0 || r > arr.length-1) {
            throw new RuntimeException("参数错误");
        }

        fillArr( arr );

        if (l == 0) {
            return h[r];
        } else {
            return h[r] - h[l-1];
        }

    }

    /**
     * 填充一维数组
     * @param arr
     */
    public static void fillArr(int[] arr) {
        h = new int[arr.length];
        for (int i = 0;i < arr.length;i++) {
            if (i == 0) {
                h[i] = arr[i];
            } else {
                h[i] = h[i-1] + arr[i];
            }
        }
    }


}
