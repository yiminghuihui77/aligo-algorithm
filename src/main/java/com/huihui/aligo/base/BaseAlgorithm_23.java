package com.huihui.aligo.base;

/**
 * 位运算实现加、减、乘、除
 *
 * @author minghui.y
 * @create 2021-10-21 11:46 下午
 **/
public class BaseAlgorithm_23 {

    public static void main( String[] args ) {
        System.out.println("---------------加法--------------");
        System.out.println(46 + 20);
        System.out.println(addByBit( 46, 20 ));

        System.out.println("---------------加法--------------");
        System.out.println(46 - 20);
        System.out.println(subByBit( 46, 20 ));

        System.out.println("---------------乘法--------------");
        System.out.println(19 * 13);
        System.out.println(multiByBit(19, 13 ));

        System.out.println("---------------除法--------------");
        System.out.println(15 / 4);
        System.out.println(divisionByBit( 15, 4 ));

        System.out.println(Integer.MIN_VALUE / 23);
        System.out.println(divisionByBitPlus( Integer.MIN_VALUE, 23 ));
    }

    /**
     * a + b 等价于 (a ^ b) + (a & b)<<1
     * ^：(异或：不同为1相同为0)，又称无进位相加
     * a + b 实际上等于a和b无进位相加，再加上进位
     * @param a
     * @param b
     * @return
     */
    public static int addByBit(int a, int b) {
        //一开始sum = a ，如果b是0则直接返回a
       int sum = a;
       while (b != 0) {
           //每一轮

           //这种写法有问题，第一行就修改了a，导致第二行a已经不是原值
           /*a = a ^ b;
           b = (a & b) << 1;
           sum = a;*/

           sum = a ^ b;
           //b重新赋值为进位
           b = (a & b) << 1;
           a = sum;
       }

        return sum;
    }

    /**
     * a - b = a + (-b)
     * @param a
     * @param b
     * @return
     */
    public static int subByBit(int a, int b) {
        b = opposite( b );
        return addByBit( a, b );
    }

    /**
     * 获取a的相反数：取反+1
     * @param a
     * @return
     */
    public static int opposite(int a) {
        return addByBit( ~a, 1 );
    }


    /**
     * a * b = c
     * 如果b = 0110，那么c = a * 2^1 + a * 2^2 = a << 1 + a << 2
     *
     * 支持负数相乘
     * @param a
     * @param b
     * @return
     */
    public static int multiByBit(int a, int b) {
        int ans = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                ans = addByBit( ans, a );
            }
            //无符号右移
            b = b >>> 1;
            a = a << 1;
        }
        return ans;
    }


    /**
     * a / b = c
     * 如果c = 0110，那么a = (b * 2^1) + (b * 2^2)，
     * 漏洞：不适合a或b是Integer.MIN_VALUE的情况！！
     * 思路：转成两个正数相除得结果ans，若a和b符号不一致，ans补个负号
     * 自动向下取整
     * @param a
     * @param b
     * @return
     */
    public static int divisionByBit(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("除数不能为0");
        }
        int ans = 0;

        //转成非负数（这里有个漏洞，即Integer.MIN_VALUE的相反数还是它自己）
        int x = isNegativeNum( a ) ? opposite( a ) : a;
        int y = isNegativeNum( b ) ? opposite( b ) : b;

        //
        for (int i = 30;i >= 0;i = subByBit( i, 1 )) {
            //让x最大程度到最小程度右移去找i，而不是让y从最小程度到最大程度左移去找i，目的是防止y左移超出符号位
            //x 右移过程中无法满足>=y，实际上相当于向下取整了
            if (x >> i >= y) {
               //结果的i位一定是1
                ans |= 1 << i;
                x = subByBit( x, y << i );
            }
        }

        return isNegativeNum( a ) ^ isNegativeNum( b ) ? opposite( ans ) : ans;
    }

    public static boolean isNegativeNum(int a) {
        return a < 0;
    }


    /**
     * a / b 完善的实现
     * @param a
     * @param b
     * @return
     */
    public static int divisionByBitPlus(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            if (b == opposite( 1 )) {
                //人为约定：b为-1时，直接返回最大值
                return Integer.MAX_VALUE;
            }
            //特殊处理
            // a / b
            // (a+1) / b = ans
            // a - (ans * b) = sub
            // 最终的结果 = ans + sub/b
        /*    int temp = addByBit( a, 1 );
            int ans = divisionByBit( temp, b );
            int t = multiByBit( ans, b );
            int sub = subByBit( a, t );
            ans = addByBit( ans, divisionByBit(sub, b) );*/

            int ans = divisionByBit( addByBit( a, 1 ), b );
            ans = addByBit( ans, divisionByBit(  subByBit( a, multiByBit( ans, b ) ), b ) );


            return ans;
        } else {
            return divisionByBit( a, b );
        }
    }



}
