package com.huihui.aligo.base;

import org.apache.commons.lang3.time.StopWatch;

import java.util.Arrays;

/**
 * 选择排序
 * 要求： 从小到大排序数组
 * @author minghui.y
 * @create 2021-05-16 4:46 下午
 **/
public class BaseAlgorithm_3 {

    public static void main( String[] args ) {
//        int arr[] = {2,1,5,4,6,3,-1};
//        sortArr( arr);

        //性能测试   8万条数据：2935
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
     * 选择排序的思路：每一轮选择[当前范围]内最小的数字放到此轮最前位置
     * @param arr
     */
    public static void sortArr(int[] arr) {

        for (int i = 0;i< arr.length;i++) {
            //每轮假设其实下标的元素值最小
            int curMinIndex = i;
            for (int j = i;j< arr.length;j++) {
                if (arr[i] > arr[j]) {
                    curMinIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[curMinIndex];
            arr[curMinIndex] = temp;
        }

        Arrays.stream( arr ).forEach( System.out::println );
        System.out.println();
    }



}
