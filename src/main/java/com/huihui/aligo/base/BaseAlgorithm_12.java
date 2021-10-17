package com.huihui.aligo.base;

import java.util.Arrays;

/**
 * 有序数组中，数值等于num的最左下标(无则返回-1)
 *
 * @author minghui.y
 * @create 2021-10-17 5:25 下午
 **/
public class BaseAlgorithm_12 {

    public static void main( String[] args ) {

        int[] arr = randomArr( 8, 10 );
        insertSorted( arr );
        printArr( arr );

        System.out.println("二分法查找的最小下标：" + mostLeftIndex( arr, 5 ));
        System.out.println("线性查找的最小下标：" + mostLeftIndex( arr, 5 ));

    }


    /**
     * 查找有序数组arr[]中，元素等于num的最小下标
     * 没有则返回-1
     * @param arr 有序数组
     * @param num
     * @return
     */
    public static int mostLeftIndex(int[] arr, int num) {
        int ans = -1;
        if (arr == null || arr.length == 0) {
            return ans;
        }

        int L = 0;
        int R = arr.length - 1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] >= num) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 线性查找有序数组中元素为num的最小下标
     * @param arr
     * @param num
     * @return
     */
    public static int lineCheck(int[] arr, int num) {
        for (int i = 0;i < arr.length;i++) {
            if (arr[i] == num) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 返回[1, maxLength]范围内长度的数组，值在[0，maxValue-1]范围
     * @param maxLength
     * @param maxValue
     * @return
     */
    public static int[] randomArr(int maxLength, int maxValue) {
        int length = (int)(Math.random() * maxLength) + 1;
        int[] arr = new int[length];

        for (int i = 0;i < length;i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream( arr ).forEach( o -> sb.append( o ).append( " " ) );
        System.out.println(sb.toString());
    }

    /**
     * 交换数组两个元素
     * @param arr
     * @param x1
     * @param x2
     */
    public static void swap(int[] arr, int x1, int x2) {
        int temp = arr[x1];
        arr[x1] = arr[x2];
        arr[x2] = temp;
    }

    /**
     * 插入排序
     * @param arr
     */
    public static void insertSorted(int[] arr) {

        for (int i = 0;i < arr.length;i++) {
            //第i轮
            for (int j = i;j > 0;j--) {

                while (arr[j] < arr[j - 1]) {
                    swap( arr, j, j - 1 );
                }
            }
        }
    }

}
