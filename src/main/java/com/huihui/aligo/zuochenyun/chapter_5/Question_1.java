package com.huihui.aligo.zuochenyun.chapter_5;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 判断两个字符串是否互为变形词
 *
 * 变形词：两个字符串str1、str2中出现的字符种类一样并且每种字符出现的次数一样，则互为变形词
 * @author minghui.y
 * @create 2021-10-14 5:31 下午
 **/
public class Question_1 {


    public static void main( String[] args ) {
        System.out.println(isDeformation1( "abcda", "cbaad" ));
        System.out.println(isDeformation2( "abcde", "cbaaf" ));
    }

    /**
     * 我的实现：使用Map
     * 使用一个map就够了
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isDeformation1(String str1, String str2) {
        if (StringUtils.isAnyBlank( str1, str2 ) || str1.length() != str2.length() ) {
            return false;
        }

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (int i = 0;i < str1.length();i++) {
            char temp = str1.charAt( i );
            if (map1.get( temp ) == null) {
                map1.put( temp, 1 );
            } else {
                map1.put( temp, map1.get( temp ) + 1 );
            }
        }

        for (int i = 0;i < str2.length();i++) {
            char temp = str2.charAt( i );
            if (map2.get( temp ) == null) {
                map2.put( temp, 1 );
            } else {
                map2.put( temp, map2.get( temp ) + 1 );
            }
        }

        //字符种类数不一致
        if (map1.keySet().size() != map2.keySet().size()) {
            return false;
        }

        //字符种类数一致，可能str1和str2各自有自己特有的字符，因此不关以谁为基准都能判断出
        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            char temp = entry.getKey();
            if (!map2.containsKey( temp )) {
                return false;
            }
            if (entry.getValue() != map2.get( temp )) {
                return false;
            }
        }

        return true;
    }

    /**
     * 书上的方案  字符的编码在0~256内，用数组map[a] = b，a表示字符的编码值，b是出现次数
     * 相比于第一种做法，不需要对两个字符串都作词频统计表，因为数字下标范围涵盖了所有字符
     *
     * 性能：如果字符种类为M，两个字符串长度为N，则时间复杂度为O(N)，空间复杂度为O(M)
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isDeformation2(String str1, String str2) {
        if (StringUtils.isAnyBlank( str1, str2 ) || str1.length() != str2.length() ) {
            return false;
        }

        int[] charArr = new int[256];

        for (int i = 0;i < str1.length();i++) {
            charArr[str1.charAt( i )]++;
        }

        for (int i = 0;i < str2.length(); i++) {
            if ((--charArr[str2.charAt( i )]) < 0) {
                return false;
            }
        }

        return true;
    }
}
