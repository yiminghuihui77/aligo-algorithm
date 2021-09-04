package com.huihui.aligo.base;

import org.apache.commons.lang3.time.StopWatch;

import java.util.Arrays;

/**
 * 插入排序
 * 模拟：手里拿着已经拍好序的牌，摸一张新牌插入
 * @author minghui.y
 * @create 2021-05-16 11:02 下午
 **/
public class BaseAlgorithm_5 {

    public static void main( String[] args ) {
        //从小到达排序
        //第一轮：下标0~0范围内有序
        //第二轮：下标0~1范围内有序
        //第三轮：下标0~2范围内有序
        //第四轮：下标0~3范围内有序
        //第N轮：下标0~N-1范围内有序


       /* int arr[] = {2,1,5,4,6,3,-1};
        sortArr( arr);*/

        //性能测试   8万条数据：4616
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
     * 插入排序
     * @param arr
     */
    public static void sortArr(int[] arr) {

        for (int i = 0;i < arr.length;i++) {
            //第i轮
            for (int j = i;j > 0;j--) {
//                if (arr[j] < arr[j - 1]) {
//                    swap( arr, j, j - 1 );
//                } else {
//                    //0 ~ j-1是从小到达排好序的，一旦arr[j]不小于arr[j-1]，则没必要继续
//                    break;
//                }

                while (arr[j] < arr[j - 1]) {
                    swap( arr, j, j - 1 );
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
