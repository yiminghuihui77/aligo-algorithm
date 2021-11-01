package com.huihui.aligo.system;

import java.util.Arrays;

/**
 * 归并排序延伸问题2：biggerThanTwice
 * 无序数组arr[]，对于任一元素arr[i]，求其右边元素，满足arr[i] > arr[j] * 2,的元素个数（j > i），返回总个数
 * @author minghui.y
 * @create 2021-10-28 3:57 下午
 **/
public class SystemAlgorithm_9 {

    public static void main( String[] args ) {
        int tryTimes = 10000;
        int maxLength = 50;
        int maxValue = 200;

        for (int i = 0;i < tryTimes;i++) {
            int[] arr=  randomArr(maxLength, maxValue);
            int[] copyArr = copyArr( arr );
            printArr( arr );
            int count1 = mergeSort4BiggerThanTwice( arr );
            int count2 = getCount( copyArr );
            System.out.println("归并排序计算count:" + count1);
            System.out.println("迭代方式计算count:" + count2);
            if (count1 != count2) {
                throw new RuntimeException("计算结果不一致！");
            }

        }


    }


    public static int[] randomArr(int maxLength, int maxValue) {
        int length = (int) (Math.random() * maxLength) + 1;
        int[] arr = new int[length];
        for (int i = 0;i < length;i++) {
            arr[i] = randomValue(maxValue);
        }

        return arr;
    }

    public static int randomValue(int maxVallue) {
        return ((int)(Math.random() * maxVallue) + 1)/* - ((int)(Math.random() * maxVallue) + 1)*/;
    }

    public static void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream( arr ).forEach( o -> sb.append( o ).append( " " ) );
        System.out.println(sb.toString());
    }

    public static int[] copyArr(int[] arr) {
        int[] copyArr = new int[arr.length];
        for (int i = 0;i < arr.length;i++) {
            copyArr[i] = arr[i];
        }
        return copyArr;
    }

    /**
     * 常规方式
     * O(N^2)
     * @param arr
     * @return
     */
    public static int getCount(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int ans = 0;
        for (int i = 0;i < arr.length ;i++) {
            //由于数组无序没有单调特性，一次遇到右边第一个不满足条件的元素后，j也要继续右移寻找其他满足条件的元素
            for (int j = i + 1;j < arr.length;j++) {
                if (arr[i] >  arr[j] << 1) {
                    ans++;
                }
            }
        }
        return ans;
    }


    public static int mergeSort4BiggerThanTwice(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int l = 0;
        int r = arr.length - 1;
        return process(arr, l, r);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);

        return process( arr, l, mid ) + process( arr, mid + 1, r ) + merge(arr, l, mid, r);

    }

    public static int merge(int[] arr, int l, int mid, int r) {
        int ans = 0;

        // 目前囊括进来的数，是从[M+1, windowR)
        int windowR = mid + 1;
        //计算目标值
        for (int i = l;i <= mid;i++) {
            while (windowR <= r && (arr[i] > arr[windowR] << 1)) {
               windowR++;
           }
            //注意这个范围：[M+1, windowR) 内的元素都满足条件
           ans += (windowR - mid - 1);
        }

        //合并两个有序数组
        int[] copyArr = new int[r - l + 1];
        int index = 0;
        int pl = l;
        int pr = mid + 1;

        while (pl <= mid && pr <= r) {
            copyArr[index++] = arr[pl] < arr[pr] ? arr[pl++] : arr[pr++];
        }

        while (pl <= mid) {
            copyArr[index++] = arr[pl++];
        }

        while (pr <= r) {
            copyArr[index++] = arr[pr++];
        }

        index = l;
        for (int i = 0;i < copyArr.length;i++) {
            arr[index++] = copyArr[i];
        }

        return ans;
    }

}
