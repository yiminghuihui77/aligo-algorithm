package com.huihui.aligo.base;

import java.util.Arrays;

/**
 * 对数器
 *
 * @author minghui.y
 * @create 2021-10-17 2:36 下午
 **/
public class BaseAlgorithm_10 {

    public static void main( String[] args ) {

        int[] arr = randomArr( 7, 10 );
        printArr( arr );
        int[] temp = copyArr( arr );
        //对arr进行【选择排序】
//        selectSorted( arr );
//        bubbleSorted( arr );
        insertSorted( arr );
        System.out.println("arr数组是否有序：" + isSorted( arr ));
        printArr( arr );



    }


    /**
     * 随机返回一个长度在[1,maxLength]范围，值在[0, maxValue-1]范围的数组
     * @param maxLength
     * @param maxValue
     * @return
     */
    public static int[] randomArr(int maxLength, int maxValue) {

        int length = (int)(Math.random() * maxLength) + 1;
        int[] arr = new int[length];

        for (int i = 0;i < length;i++) {
            arr[i] = (int)(Math.random() * maxValue);
        }

        return arr;

    }

    /**
     * 数组拷贝
     * @param arr
     * @return
     */
    public static int[] copyArr(int[] arr) {
        int[] temp = new int[arr.length];
        for (int i = 0;i < arr.length;i++) {
            temp[i] = arr[i];
        }
        return temp;
    }

    /**
     * 对比两个数组是否一致
     * @param arr1
     * @param arr2
     * @return
     */
    public static boolean compareArr(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0;i < arr1.length;i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;
    }


    /**
     * 判断数组是否有序（升序）
     * @param arr
     * @return
     */
    public static boolean isSorted(int[] arr ) {
        if (arr.length < 2) {
            return true;
        }
        int min = arr[0];
        for (int i = 1;i < arr.length;i++) {
            if (arr[i] < min) {
                return false;
            }
            min = arr[i];
        }

        return true;
    }






    /********************************  以下是选择、冒泡、插入 三种排序  ***************************************************/
    /**
     * 选择排序的思路：每一轮选择[当前范围]内最小的数字放到此轮最前位置
     * @param arr
     */
    public static void selectSorted(int[] arr) {

        for (int i = 0;i< arr.length-1;i++) {
            //每轮假设其实下标的元素值最小
            int curMinIndex = i;
            for (int j = i + 1;j< arr.length;j++) {
                if (arr[curMinIndex] > arr[j]) {
                    curMinIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[curMinIndex];
            arr[curMinIndex] = temp;
        }
    }

    /**
     * 冒泡排序
     * 思路：【相邻】的两个元素比较，大的元素不断往后移动
     *      每轮结束，本轮最大的元素将放置在本轮最后的位置
     * @param arr
     */
    public static void bubbleSorted(int[] arr) {
        for (int end = arr.length - 1;end >= 0;end--) {
            for (int j = 0;j < end;j++) {
                if (arr[j] > arr[j+1]) {
                    swap( arr, j, j + 1);
                }
            }
        }

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

    public static void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream( arr ).forEach( o -> sb.append( o ).append( " " ) );
        System.out.println(sb.toString());
    }
}
