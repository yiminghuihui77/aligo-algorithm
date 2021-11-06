package com.huihui.aligo.system;

/**
 * 快速排序之荷兰国旗
 *
 * @author minghui.y
 * @create 2021-11-05 7:40 下午
 **/
public class SystemAlgorithm_11 {


    public static void main( String[] args ) {

        //左右两个区间
//        int[] arr = {8,1,2,4,7,5,6,3,4};
//        int partition = partition( arr, 0, arr.length-1 );
//        printArr( arr, partition );

        //左中右三个区间
 /*       int[] arr = {8,1,2,4,7,5,6,3,4};
        int[] partitions = netherlandsFlag( arr, 0, arr.length - 1 );
        printArr2( arr, partitions[0], partitions[1] );*/


        //快排1.0
     /*   int[] arr = {8,1,2,4,7,5,6,3,4};
        quickSort1( arr );
        printArr( arr, -1 );*/


        //快排2.0
        /*int[] arr = {8,1,2,4,7,5,6,3,4};
        quickSort2( arr );
        printArr( arr, -1 );*/


        //快排3.0
        int[] arr = {8,1,2,4,7,5,6,3,4};
        quickSort3( arr );
        printArr( arr, -1 );

    }


    /**
     * 对于给定数组arr，以arr[R]为参照值
     * 小于等于arr[R]的元素放数组的左边，大于arr[R]的数放右边 (左右两区间不要求有序)
     * 返回左右两边的分界线下标
     *
     * 在快排1.0中，相当于每次确定一个数的位置
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }

        int index = L;
        int less = L - 1;

        while (index < R) {
            if (arr[index] <= arr[R]) {
                //交换index和less+1位置的元素
                //less++
                //index++
                /*swap( arr, index, less + 1 );
                less++;
                index++;*/

                swap( arr, index++, ++less );

            }else if (arr[index] > arr[R]) {
                index++;
            }
        }
        //最后交换R和less+1位置的元素
        swap( arr, ++less, R );

        return less;
    }


    /**
     * 荷兰国旗问题
     * 给定数组arr[], 以arr[R]为参照值
     * 小于arr[R]的元素在[L,less - 1]范围
     * 等于arr[R]的元素在[less, more]范围
     * 大于arr[R]的元素在[more + 1, R]范围
     *
     * 时间复杂度：O(N)
     * 额外空间复杂度：O(1)
     *
     * 在快排2.0中，相当于每次确定一组数的位置
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }

        int index = L;
        int less = L - 1;
        int more = R;

        while (index < more) {
            if (arr[index] < arr[R]) {
                //小于
              /*
                swap( arr, index, less + 1 );
                less++;
                index++;
                */
                swap( arr, index++, ++less );

            } else if (arr[index] == arr[R]) {
                //等于
                index++;
            } else {
                //大于
                //注意：这里index不递进，因为交换过来的元素值是未知的
                swap( arr, index, --more );
            }
        }

        //最后，R处元素放到more处,more++
        swap( arr, R, more );

        return new int[]{less + 1, more};
    }


    /**
     * 交换数组两元素
     * @param arr
     * @param l
     * @param r
     */
    public static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }


    /**
     * 打印数组，边界处特殊处理
     * @param arr
     * @param partition
     */
    public static void printArr(int[] arr, int partition) {
        StringBuilder builder = new StringBuilder();
        builder.append( "[" );
        for (int i= 0;i < arr.length;i++) {
            builder.append( " " + arr[i] );
            if (i == partition) {
                builder.append( "|" );
            }
        }
        builder.append( "]");
        System.out.println(builder.toString());
    }

    public static void printArr2(int[] arr, int partitionL, int partitionR) {
        StringBuilder builder = new StringBuilder();
        builder.append( "[" );
        for (int i= 0;i < arr.length;i++) {
            builder.append( " " + arr[i] );
            if (i == partitionL || i == partitionR - 1) {
                builder.append( " |" );
            }
        }
        builder.append( "]");
        System.out.println(builder.toString());
    }

    /***************************************快速排序***********************************************/


    /**
     * 快排1.0
     * 每次确定一个数的位置
     * @param arr
     */
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length< 2) {
            return;
        }
        process1( arr, 0, arr.length - 1 );
    }

    public static void process1(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int partition = partition( arr, l, r );
        process1( arr, l, partition - 1 );
        process1( arr, partition + 1, r );
    }


    /**
     * 快速排序2.0
     * 每次确定一组位置
     *
     * 时间复杂度 O(N^2)
     * @param arr
     */
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length< 2) {
            return;
        }
        process2( arr, 0, arr.length - 1 );
    }

    public static void process2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] partitions = netherlandsFlag( arr, l, r );
        process2( arr, l, partitions[0] - 1 );
        process2( arr, partitions[1] + 1, r );
    }


    /**
     * 快排3.0 (随机快排)
     *
     * 快排2.0是以R为基准，快排3.0随机取一个元素和arr[R]交换
     * 引入概率，使得时间复杂度为O(logN * N)
     * @param arr
     */
    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length< 2) {
            return;
        }

        process3( arr, 0, arr.length - 1 );
    }

    public static void process3(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        swap( arr, l + (int)(Math.random() * (r - l + 1)), r );
        int[] partitions = netherlandsFlag( arr, l, r );
        process3( arr, l, partitions[0] - 1 );
        process3( arr, partitions[1] + 1, r );

    }


}
