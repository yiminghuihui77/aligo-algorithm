package com.huihui.aligo.base;

import org.apache.commons.lang3.time.StopWatch;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author minghui.y
 * @create 2021-05-16 10:13 下午
 **/
public class BaseAlgorithm_4 {

    public static void main( String[] args ) {
      /*  int arr[] = {2,1,5,4,6,3,-1};
        sortArr( arr);*/

        //性能测试   8万条数据：11249
        int arrLength = 8 * 10000;
        int arr[] = new int[arrLength];
        for (int i = 0;i< arrLength;i++) {
            arr[i] = (int) (Math.random() * 10000);
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        sortArr( arr );
        stopWatch.stop();
        System.out.println("选择排序总耗时：" + stopWatch.getTime());

    }


    /**
     * 冒泡排序
     * 思路：相邻的两个元素比较，大的元素不断往后移动
     *      每轮结束，本轮最大的元素将放置在本轮最后的位置
     * @param arr
     */
    public static void sortArr(int[] arr) {
        for (int end = arr.length - 1;end >= 0;end--) {
            for (int j = 0;j < end;j++) {
                if (arr[j] > arr[j+1]) {
                    swap( arr, j, j + 1);
                }
            }
        }

        printArr( arr );
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

}
