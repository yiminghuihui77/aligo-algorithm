package com.huihui.aligo.base;

import java.util.Arrays;

/**
 * 有序数组中，查找指定数值
 * 二分查找
 * @author minghui.y
 * @create 2021-10-17 4:48 下午
 **/
public class BaseAlgorithm_11 {

    public static void main( String[] args ) {
        int target = 5;
  /*      int[] arr = randomArr( 7, 10 );
        printArr( arr );
        //插入排序
        insertSorted( arr );
        printArr( arr );

        System.out.println("线性遍历判断:" + target + "是否在数组中：" + isExist( arr, target ));
        System.out.println("二分法遍历判断:" + target + "是否在数组中：" + binarySearch( arr, target ));*/

        int tryTimes = 100;
        for (int i = 0;i < tryTimes;i++) {
            int[] arr = randomArr( 7, 10 );
            //插入排序
            insertSorted( arr );
            printArr( arr );
            if (isExist( arr, target ) != binarySearch( arr, target )) {
                throw new RuntimeException("对数异常");
            }
            System.out.println("线性遍历判断:" + target + "是否在数组中：" + isExist( arr, target ));
            System.out.println("二分法遍历判断:" + target + "是否在数组中：" + binarySearch( arr, target ));

        }
        System.out.println();


    }


    /**
     * 判断有序数组中是否存在num
     * @param arr 有序数组(升序)
     * @param num
     * @return
     */
    public static boolean binarySearch(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        int L = 0;
        int R = arr.length - 1;

        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] == num){
                return true;
            } else if (arr[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        return false;
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

    /**
     * 判断num是否在数组中
     * 线性遍历
     * @param arr
     * @param num
     * @return
     */
    public static boolean isExist(int[] arr, int num) {

        for (int i = 0;i < arr.length;i++) {
            if (arr[i] == num) {
                return true;
            }
        }
        return false;
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
