package com.huihui.aligo.system;

import java.util.Arrays;

/**
 * 小和问题
 * 利用归并排序merge的过程，顺便求出小和
 * @author minghui.y
 * @create 2021-10-27 4:55 下午
 **/
public class SystemAlgorithm_8 {

    public static void main( String[] args ) {

        int tryTimes = 10000;
        int maxValue = 200;
        int maxLength = 50;

        for (int i = 0;i < tryTimes;i++) {
            int[] arr = randomArr( maxLength, maxValue );
            int[] copyArr = copyArr( arr );
            printArr( arr );
            //归并排序计算的小和
            int smallSum1 = mergeSort4SmallSum( arr );
            int smallSum2 = getSmallSum( copyArr );
            System.out.println("归并排序计算小和：" + smallSum1);
            System.out.println("迭代遍历计算小和：" + smallSum2);
            if (smallSum1 != smallSum2) {
                throw new RuntimeException("小和计算错误");
            }

        }


    }

    public static int[] randomArr(int maxLength, int maxValue) {
        int length = (int)(Math.random() + maxLength) + 1;
        int[] arr = new int[length];
        for (int i = 0;i < length;i++) {
            arr[i] = randomValue( maxValue );
        }
        return arr;
    }

    public static int randomValue(int maxValue) {
        return ((int)(Math.random() * maxValue) + 1) - ((int)(Math.random() * maxValue) + 1);
    }


    /**
     * O(N^2)
     * 常规方法计算小和
     * @param arr
     * @return
     */
    public static int getSmallSum(int[] arr) {
        int smallSum = 0;
        if (arr == null || arr.length < 2) {
            return smallSum;
        }
        for (int i = 1;i < arr.length;i++) {
            for (int j = i - 1;j >= 0;j--) {
                if (arr[j] < arr[i]) {
                    smallSum+= arr[j];
                }
            }

        }
        return smallSum;
    }

    /**
     * 利用归并排序计算小和
     * 满足T(N) = 2 * T(N/2) + O(N)， a = 2,b = 2,d = 1
     * 因为log(a,b) == d，因此时间复杂度为O(logN * N)
     * @param arr
     * @return
     */
    public static int mergeSort4SmallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process(arr, 0, arr.length-1);

    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }

        int mid = l + ((r - l) >> 1);

        return process( arr, l, mid ) + process( arr, mid + 1, r ) + merge(arr, l, mid, r);

    }

    /**
     *
     * @param arr
     * @param l
     * @param mid
     * @param r
     * @return
     */
    public static int merge(int[] arr, int l, int mid, int r) {
        int[] copyArr = new int[r - l + 1];
        int index = 0;
        int pl = l;
        int pr = mid + 1;
        int smallSum = 0;

        while (pl <= mid && pr <= r) {
            //右组等于左组是，先拷贝右组
            if (arr[pl] < arr[pr]) {
                //左边小，累加小和
                smallSum += arr[pl] * (r - pr + 1);
                copyArr[index++] = arr[pl++];
            } else {
                //右边小于等于左右，直接拷贝，不累加小和
                copyArr[index++] = arr[pr++];
            }
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

        return smallSum;
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

}
