package com.huihui.aligo.base;

/**
 * 已知黑盒函数f()能够等概率返回1~5的整数
 * 利用f()，请给出函数h()等够等概率返回1~7的整数
 *
 * 两种手段：
 * 1、do-while遇到某个值则重算，相当于将出现该值得概率平均分配给其他数
 * 2、为了达到目标数值范围，通常做适当运算
 *
 * @author minghui.y
 * @create 2021-10-17 12:29 下午
 **/
public class BaseAlgorithm_8 {

    public static void main( String[] args ) {

        //测试f()方法
//        testF();

        //测试f2()方法
//        testF2();

        //测试f3()方法
//        testF3();

        //测试f4()方法
//        testF4();

        //测试h函数
        testH();
    }

    /**
     * f()能够等概率返回1~5的整数
     * @return
     */
    public static int f() {
        return ((int)(Math.random() * 5)) + 1;
    }

    public static void testF() {
        int tryTimes = 1000000;
        int count = 0;
        for (int i = 0;i < tryTimes;i++) {
            //概率都在0.2左右
            if (f() == 1) {
                count++;
            }
        }
        System.out.println((double)(count) / ((double)(tryTimes)));
    }


    /**
     * 利用f()函数，构造一个等概率返回0和1的函数
     * 即返回0或1的概率都是0.5
     * @return
     */
    public static int f2() {
        int ans = 0;
        do {
            //随机获取1~5
            ans = f();
        } while (ans == 3);

        return ans < 3 ? 0 : 1;
    }

    public static void testF2() {
        int tryTimes = 1000000;
        int count = 0;
        for (int i = 0;i < tryTimes;i++) {
            if (f2() == 0) {
                count++;
            }
        }
        System.out.println((double)(count) / ((double)(tryTimes)));
    }


    /**
     * 利用f2()方法，等概率返回0~7的整数
     * @return
     */
    public static int f3() {
        //三者相加，最小的数是0，最大的数是7
        //每个值返回概率都是 1/2 * 1/2 * 1/2
        return (f2() << 2) +  (f2() << 1) + (f2() << 0);
    }

    public static void testF3() {
        int tryTimes = 1000000;
        int count = 0;
        for (int i = 0;i < tryTimes;i++) {
            if (f3() == 7) {
                count++;
            }
        }
        //每个值返回概率都是 1/2 * 1/2 * 1/2
        System.out.println((double)(count) / ((double)(tryTimes)));
        System.out.println((double) 1 / (double) 8);
    }


    /**
     * 在f3()的基础上，等概率返回0~6的整数：遇7重算
     * @return
     */
    public static int f4() {

        int ans = 0;
        do {
            ans = f3();
        } while (ans == 7);

        return ans;
    }

    public static void testF4() {
        int tryTimes = 1000000;
        int count = 0;
        for (int i = 0;i < tryTimes;i++) {
            if (f4() == 6) {
                count++;
            }
        }
        //0~6之间，每个整数出现概率是1/7
        System.out.println((double)(count) / ((double)(tryTimes)));
        System.out.println((double) 1/(double) 7);
    }


    /**
     * 最终的目标函数：利用f4()，在1~7范围内，等概率返回整数
     * @return
     */
    public static int h() {
        return f4() + 1;
    }

    /**
     * 最终的目标函数：利用f3()，在1~7范围内，等概率返回整数
     * 遇0重算，
     * @return
     */
    public static int h2() {
        int ans = 0;
        do {
            ans = f3();
        } while (ans == 0);
        return ans;
    }

    public static void testH() {
        int tryTimes = 1000000;
        int[] count = new int[8];

        for (int i = 0;i < tryTimes;i++) {
            count[h2()]++;
        }
        //
        for (int i = 0;i < count.length;i++) {
            System.out.println("出现：" + i + "的次数：" + count[i]);
        }
    }






}
