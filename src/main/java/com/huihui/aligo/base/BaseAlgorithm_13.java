package com.huihui.aligo.base;

import java.util.Arrays;

/**
 * 返回无序数组中，一个局部最小值的下标
 *
 * 局部最小：一个元素同时小于左右两边元素，则其实一个局部最小元素(边界元素只要小于第一个边界内侧元素也算是局部最小)
 * 数组要求：无序数组，但相邻两元素不能相等
 *
 * 场景枚举：
 * 数组长度为1，则局部最小元素就是arr[0]
 * 数组长度为2，局部最小元素是Math(arr[0], arr[1]);
 *
 * 数组长度从三开始，可以使用二分法，确定局部最小
 *
 * @author minghui.y
 * @create 2021-10-17 6:11 下午
 **/
public class BaseAlgorithm_13 {

    public static void main( String[] args ) {

        int tryTimes = 1000000;
        System.out.println("开始测试....");
        for (int i = 0;i < tryTimes;i++) {
            int[] arr = randomOrderArr( 7, 10 );
            //打印数组
            printArr( arr );
            //二分法获取一个局部最小元素的下标
            int minIndex = getLocalMinIndex( arr );
            //打印一个局部最小值的下标
            System.out.println("arr数组的一个局部最小值下标：" + minIndex);
            if (!checkLocalMinIndex( arr, minIndex )) {
                //不是局部最小元素下标
                throw new RuntimeException("二分法查找的一个局部最小值的下标： " + minIndex + "是错误的！！！");
            }
        }
        System.out.println("结束测试....");

    }


    /**
     * 返回特定数组的一个局部最小元素的下标
     * @param arr 无序但相邻元素不等的数组
     * @return
     */
    public static int getLocalMinIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        //只有一个元素，则默认是局部最小
        if (arr.length == 1) {
            return 0;
        }

        //优先考虑边界的局部最小
        int N= arr.length;
        //左边界
        if (arr[0] < arr[1]) {
            return 0;
        }
        //有边界
        if (arr[N-1] < arr[N-2]) {
            return N-1;
        }

        int L = 0;
        int R = arr.length -1;
        //确保至少有三个元素，且mid-1和mid+1在[L,R]范围内
        while (L < R-1) {
            int mid = (L + R) / 2;
            if (arr[mid] < arr[mid-1] && arr[mid] < arr[mid + 1]) {
                //arr[mid]同时小于左右两边元素，则是一个局部最小
                return mid;
            } else {
                if (arr[mid] > arr[mid-1]) {
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            }
        }
        return arr[L] < arr[R] ? L : R;

    }

    /**
     * 获取一个无序，但相邻元素值不等的数组
     * @param maxLength 数组长度在[1,maxLength]范围
     * @param maxValue  元素值在[0,maxValue-1]范围
     * @return
     */
    public static int[] randomOrderArr(int maxLength, int maxValue) {
        int length = (int) (Math.random() + maxLength) + 1;
        int[] arr = new int[length];
        arr[0] = ((int)(Math.random() * maxValue));
        for (int i = 1;i < length;i++) {
            do {
                arr[i] = ((int)(Math.random() * maxValue));
            } while (arr[i] == arr[i - 1]);
        }
        return arr;
    }


    /**
     * 判断minIndex是否是数组arr的一个局部最小元素的下标
     * @param arr
     * @param minIndex
     * @return
     */
    public static boolean checkLocalMinIndex(int[] arr, int minIndex) {
        if (arr == null || arr.length == 0) {
            return minIndex == -1;
        }
        if (arr.length == 1) {
            return minIndex == 0;
        }
        if (arr.length == 2) {
            return (arr[0] < arr[1] ? 0 : 1) == minIndex;
        }

        boolean lessToLeft = minIndex - 1 <= 0 || arr[minIndex] < arr[minIndex - 1];
        boolean lessToRight = minIndex + 1 >= arr.length || arr[minIndex] < arr[minIndex + 1];

        return lessToLeft && lessToRight;
    }

    public static void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream( arr ).forEach( o -> sb.append( o ).append( " " ) );
        System.out.println(sb.toString());
    }

}
