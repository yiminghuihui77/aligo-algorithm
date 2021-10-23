package com.huihui.aligo.system;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 一个数组中有一种数出现K次，其他数都出现了M次，
 * M > 1,  K < M
 * 请超出出现次数为k的数
 *
 * 手写对数器比较难
 * @author minghui.y
 * @create 2021-10-23 11:59 下午
 **/
public class SystemAlgorithm_2 {

    public static void main( String[] args ) {
        int tryTimes = 100000;
        int range = 200;
        int max = 9;

        System.out.println("开始测试...");
        for (int i = 0;i < tryTimes;i++) {
            //随机计算k和m
            //1~9
            int a = (int)(Math.random() * max) + 1;
            int b = (int)(Math.random() * max) + 1;
            //确保k < m
            int k = Math.min( a, b );
            int m = Math.max( a, b );
            if (k == m) {
                ++m;
            }

            //获取随机数组
            int[] arr = randomArr( range, k, m );

            //普通方法计算的结果

            int ans1 = test( arr, k, m );
            int ans2 = getFindKFromM( arr, k, m );
            if (ans1 != ans2) {
//                System.out.println("测试结果不一致！ans1:" + ans1 + ", ans2:" + ans2);
                throw new RuntimeException("\"测试结果不一致！ans1:\" + ans1 + \", ans2:\" + ans");
            }

        }
        System.out.println("结束测试...");
        
    }

    /**
     * 元素值范围在-range ~ range
     * 某个元素出现k次，其余元素均出现m次
     * @param range
     * @param k
     * @param m
     * @return
     */
    public static int[] randomArr(int range, int k, int m) {
        int targetK = getRandomNum( range );
        //数组中元素的种类最大值
        int maxNumKinds = 9;
        //随机元素种类，至少两种
        int numKinds = (int)(Math.random() * maxNumKinds) + 2;
        //数组长度
        int[] arr = new int[k + (numKinds - 1) * m];

        //定义出现次数为k的元素值
        int kTimesNum = getRandomNum( range );

        //开始填充数组
        int index = 0;
        for (;index < k;index++) {
            //先填充出现次数为k的元素
            arr[index] = kTimesNum;
        }

        //填充出现次数为m其他元素
        Set<Integer> set = new HashSet<>();
        set.add( kTimesNum );
        numKinds--;

        while (numKinds != 0) {
            int curNum = 0;
            do {
                curNum = getRandomNum( range );
            } while (set.contains( curNum ));
            set.add( curNum );
            numKinds--;
            //找到了未出现过的元素curNum,则塞到数组中m次
            int count = m;
            while (count > 0) {
                arr[index++] = curNum;
                count--;
            }
        }

        //arr数组已经填充完毕，随机乱序处理
        for (int i = 0;i < arr.length;i++) {
            //i位置的元素和随机j位置的元素交换位置
            int j = (int)(Math.random() * arr.length);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        return arr;
    }

    /**
     * 返回范围在-range ~ range的随机数
     * @param range
     * @return
     */
    public static int getRandomNum(int range) {
        return  ((int)(Math.random() * range) + 1) - ((int)(Math.random() * range) + 1);
    }




    /**
     * 一个数组中有一种数出现K次，其他数都出现了M次，
     *  M > 1,  K < M
     *  找到，出现了K次的数，
     *  要求，额外空间复杂度O(1)，时间复杂度O(N)
     * @param arr
     * @param k
     * @param m
     * @return 出现k次的数
     */
    public static int getFindKFromM(int[] arr, int k, int m) {
        //count[0]表示在0位出现1的次数 
        //count[1]表示在1位出现1的次数
        int[] count = new int[32];

        for (int num : arr) {
            for (int i = 0;i < 32;i++) {
                //count[i] += (num >> i) & 1
                if (((num >> i) & 1) != 0) {
                    //num在第i位上为1
                    count[i]++;
                }
            }
        }

        //目标结果
        int ans = 0;
        for (int i = 0;i < count.length;i++) {
            //说明第i位对m除不尽，则ans第i位是1
            if (count[i] % m != 0) {
                //说明ans在第i位一定是1
                ans |= (1 << i);
            }
        }
        return ans;
    }


    /**
     * 最普通的方式获取出现k次的数
     * @param arr
     * @param k
     * @param m
     * @return
     */
    public static int test(int[] arr, int k, int m) {
        //Map<数值，次数>
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.get( num ) == null) {
                map.put( num, 1 );
            } else {
                int count = map.get( num ) + 1;
                map.put( num, count );
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == k) {
                return entry.getKey();
            }
        }
        return -1;
    }
    
    
}
