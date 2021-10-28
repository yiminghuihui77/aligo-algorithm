package com.huihui.aligo.system;

import java.util.Arrays;

/**
 * 1、归并排序(递归思想)
 * 满足T(N) = 2 * T(N/2) + O(N)， a = 2,b = 2,d = 1
 * 符合master公式，因此归并排序时间复杂度：O(N * logN)
 *
 *
 * @author minghui.y
 * @create 2021-10-26 3:49 下午
 **/
public class SystemAlgorithm_7 {


    public static void main( String[] args ) {
        int tryTimes = 10000;
        int maxValue = 200;
        int maxLength = 50;

        System.out.println("对数器开始工作...");
        for (int i = 0;i < tryTimes;i++) {
            int[] arr =randomArr( maxLength, maxValue );
            int[] copyArr = copyArr( arr );
            int[] copyArr2 = copyArr( arr );

            System.out.println("排序前：");
            printArr( arr );

            //arr使用归并排序
            mergeSort( arr );
            //归并排序（迭代写法）
            mergeSort2( copyArr2 );
            //copyArr使用插入排序
            insertedSort(copyArr);
            //比较两种排序结果是否一致
            compareArr( arr, copyArr );
            compareArr( copyArr2, copyArr );

            System.out.println("排序后：");
            printArr( arr );

        }
        System.out.println("对数器结束工作...");

    }

    /**
     * 比较两个数组每个元素是否一致
     * @param arr
     * @param copyArr
     */
    public static void compareArr(int[] arr, int[] copyArr) {
        if (arr.length != copyArr.length) {
            printArr( arr );
            printArr( copyArr );
            throw new RuntimeException("排序结果不一致！！");
        }
        for (int i = 0;i < arr.length;i++) {
            if (arr[i] != copyArr[i]) {
                printArr( arr );
                printArr( copyArr );
                throw new RuntimeException("排序结果不一致！！");
            }

        }
    }


    public static int[] copyArr(int[] arr) {
        int[] copyArr = new int[arr.length];
        for (int i = 0;i < arr.length;i++) {
            copyArr[i] = arr[i];
        }
        return copyArr;
    }

    /**
     * 随机返回一个数组
     * @param maxLength
     * @param maxValue
     * @return
     */
    public static int[] randomArr(int maxLength, int maxValue) {
        //随机的数组长度
        int length = (int)(Math.random() * maxLength) + 1;
        int[] arr = new int[length];
        for (int i = 0;i < length;i++) {
            arr[i] = randomValue( maxValue );
        }
        return arr;
    }

    /**
     * 随机返回一个指定范围的数
     * -maxValue ~ maxValue
     * @param maxValue
     * @return
     */
    public static int randomValue(int maxValue) {
        return ((int)(Math.random() * maxValue) + 1) - ((int)(Math.random() * maxValue) + 1);
    }

    /**
     * 归并排序（地推写法）
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        process( arr, 0, arr.length - 1 );

    }


    /**
     * 归并排序（迭代写法）
     * @param arr
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 步长
        int mergeSize = 1;
        while (mergeSize < N) { // log N
            // 当前左组的，第一个位置
            int L = 0;
            while (L < N) {
                if (mergeSize >= N - L) {
                    break;
                }
                int M = L + mergeSize - 1;
                int R = M + Math.min(mergeSize, N - M - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            // 防止溢出
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    /**
     * 实现对arr数组从l到r有序
     * 思路：将l~r范围分两半分别排序，并将两个排好序的进行合并
     * @param arr
     * @param l
     * @param r
     */
    public static void process(int[] arr,int l, int r) {
        //递归的基准条件
        if (l == r) {
            return;
        }
        int mid = l + (r - l) / 2;

        process( arr, l, mid );
        process( arr,mid + 1, r );
        merge( arr, l, mid, r );
    }

    /**
     * l ~ mid范围已排好序
     * mid + 1 ~ r 范围已排好序
     * 本方法实现合并两笔排好序的数组
     * 假设 l ~ r有N个元素，时间复杂度O(N)
     *
     * 相比于O(N^2)的排序算法，省略了大量的无用比较
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    public static void merge(int[] arr, int l, int mid, int r) {
        //准备一个数组
        int[] copyArr = new int[r - l + 1];
        int index = 0;
        //左半边数组开始的小标，范围：l ~ mid
        int pl = l;
        //左半边数组开始的小标，范围：mid + 1 ~ r
        int pr = mid + 1;

        //两边都不越界的场景
        while (pl <= mid && pr <= r) {
            if (arr[pl] <= arr[pr]) {
                copyArr[index++] = arr[pl];
                pl++;
            } else {
                copyArr[index++] = arr[pr];
                pr++;
            }
        }
        //剩下的元素继续拷贝
        while (pl <= mid) {
            copyArr[index++] = arr[pl];
            pl++;
        }

        while (pr <= r) {
            copyArr[index++] = arr[pr];
            pr++;
        }

        //copyArr数组的元素恢复到arr[l]~arr[r]
        index = l;
        for (int i = 0;i < copyArr.length;i++) {
            arr[index++] = copyArr[i];
        }

    }

    /**
     * 插入排序
     * 思路：类似于将手中的扑克牌进行排序，最开始只有一张扑克牌，
     * 捡起第二张扑克从右手边开始比较是否小于左边相邻的扑克牌，小于则不断与左边的扑克牌交互位置
     * 后面捡起的牌，插入之前，手上的扑克牌都是有序的，只要从右边不断与左边相邻的牌比较即可
     * @param arr
     */
    public static void insertedSort(int[] arr) {

        for (int i = 0;i < arr.length;i++) {
            //第i轮
            for (int j = i;j > 0;j--) {
                while (arr[j] < arr[j - 1]) {
                    swap( arr, j, j - 1 );
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

    public static void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream( arr ).forEach( o -> sb.append( o ).append( " " ) );
        System.out.println(sb.toString());
    }


}
