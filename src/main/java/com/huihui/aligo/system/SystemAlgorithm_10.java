package com.huihui.aligo.system;

/**
 * https://leetcode.com/problems/count-of-range-sum/
 * 归并排序衍生题目：区间和的个数
 * 关于本题的一些转换：
 *  1、使用累加和数组协助
 *  2、arr[i]~arr[j]的累加和记为sum(i,j)，求sum(i,j)落在[lower, upper]范围内，等价于sum(0,j)-sum(0,i-1)落在[lower, upper]范围内
 *  3、以右组的j为基准，定出范围上下限，求左组落在上下限范围的个数
 *
 *
 * 对于数组arr[]，求有多少子数组的累加和，位于指定区间[lower, upper]范围内
 *
 * 加上数组arr在0~i范围累加和是X，求必须以i位置结尾的子数组中，有多少在[L, UP]范围上
 * 等价于：求i之前的所有前缀和中，有多少个前缀和在[X-UP, X-L]范围上
 * @author minghui.y
 * @create 2021-11-02 3:25 下午
 **/
public class SystemAlgorithm_10 {

    public static void main( String[] args ) {

        int tryTimes = 10000;
        int maxLength = 50;
        int maxValue = 200;
        int lower= 10;
        int upper = 50;

        for (int i = 0;i < tryTimes;i++) {
            System.out.println("----------------------------");
            int[] arr = randomArr( maxLength, maxValue );
            int[] copyArr = copyArr( arr );
            System.out.println("原始数组：");
            printArr( arr );
            //常规方式求
            int count1 = getCount4Range( arr, lower, upper );
            int count2 = mergeSort4RangeCount( copyArr, lower, upper );
            System.out.println("常规方式的count: " + count1);
            System.out.println("归并排序方式的count: " + count2);
            System.out.println("----------------------------");
            if (count1 != count2) {
                throw new RuntimeException("结果不一致");
            }
        }



    }


    public static int[] randomArr(int maxLength, int maxValue) {
        int length = (int)(Math.random() * maxLength) + 1;
        int[] arr = new int[length];

        for (int i = 0;i < length;i++) {
            arr[i] = randomValue( maxValue );
        }

        return arr;
    }

    public static int randomValue(int maxValue) {
        return ((int)(Math.random() * maxValue) + 1) - ((int)(Math.random() * maxValue) + 1) ;
    }


    public static int[] copyArr(int[] arr) {
        int[] copy = new int[arr.length];
        for (int i = 0;i < arr.length;i++) {
            copy[i] = arr[i];
        }
        return copy;
    }


    /**
     * 归并排序方式求子数组个数
     * @param arr
     * @param lower
     * @param upper
     * @return
     */
    public static int mergeSort4RangeCount(int[] arr, int lower, int upper) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        //求数组的类加和数组：累加和数组必须用long类型，否则存在边界问题
        long[] sumArr = getSumArr( arr );


        return process( sumArr, lower, upper, 0, sumArr.length-1 );
    }

    public static int process(long[] sumArr, int lower, int upper, int l, int r) {
        if (l == r) {
            return judgeBetween( sumArr[l], lower, upper ) ? 1 : 0;
        }

        int mid = l + ((r - l) >> 2);

        return process( sumArr, lower, upper, l, mid )
                + process( sumArr, lower, upper, mid + 1, r )
                + merge( sumArr, lower, upper, l, mid, r );
    }

    public static int merge(long[] sumArr, int lower, int upper, int l, int mid, int r) {
        int windowL = l;
        int windowR = l;

        int ans = 0;
        for (int i = mid + 1;i <= r;i++) {
            long min = sumArr[i] - upper;
            long max = sumArr[i] - lower;

            //由于单调性，windowL和windowR不会回退
            while (windowL <= mid && sumArr[windowL] < min) {
                windowL++;
            }

            while (windowR <= mid && sumArr[windowR] <= max) {
                windowR++;
            }

            ans += windowR - windowL;
        }

        //合并数组
        long[] help = new long[r - l + 1];
        int x = l;
        int y = mid + 1;
        int index = 0;

        while (x <= mid && y <= r) {
            help[index++] = sumArr[x] <= sumArr[y] ? sumArr[x++] : sumArr[y++];
        }

        while (x <= mid) {
            help[index++] = sumArr[x++];
        }
        while (y <= r) {
            help[index++] = sumArr[y++];
        }

        for (int i = 0;i < help.length;i++) {
            sumArr[l + i] = help[i];
        }

        return ans;
    }



    /**
     * 常规方式求符合条件的子数组个数
     * 时间复杂度O(N^2)
     * 额外空间复杂度O(N)
     * @param arr
     * @param lower
     * @param upper
     * @return
     */
    public static int getCount4Range(int[] arr, int lower, int upper) {
        if (arr == null) {
            return 0;
        }
        if (arr.length < 2) {
            if (judgeBetween( arr[0], lower, upper )) {
                return 1;
            } else {
                return 0;
            }
        }

        //求数组的类加和数组
        long[] sumArr = getSumArr( arr );
        int ans = 0;
        for (int i = 0;i < arr.length;i++) {
            for (int j = i;j < arr.length;j++) {
                long subSum = subSum( sumArr, i, j );
                if (judgeBetween( subSum, lower, upper )) {
                    ans++;
                }
            }
        }

        return ans;
    }

    /**
     * 根据类加个数组，求原数组在下标[l, r]范围的累加和
     * @param sumArr
     * @param l
     * @param r
     * @return
     */
    public static long subSum(long[] sumArr, int l, int r) {
        //数组下标检测
        if (l < 0 || r > sumArr.length-1) {
            throw new RuntimeException("参数错误");
        }

        if (l == 0) {
            return sumArr[r];
        } else {
            return sumArr[r] - sumArr[l-1];
        }

    }

    /**
     * 获取数组的累加和数组
     * @param arr
     * @return
     */
    public static long[] getSumArr(int[] arr) {
        long[] sumArr = new long[arr.length];

        for (int i = 0;i < arr.length;i++) {
            if (i == 0) {
                sumArr[i] = arr[i];
            } else {
                sumArr[i] = sumArr[i-1] + arr[i];
            }
        }
        return sumArr;
    }




    public static boolean judgeBetween(long target, int lower, int upper) {
        if (target >= lower && target <= upper) {
            return true;
        }
        return false;
    }


    public static void printArr(int[] arr) {
        StringBuilder builder = new StringBuilder();
        for (int i : arr) {
            builder.append( i + " " );
        }
        System.out.println(builder.toString());
    }


}
