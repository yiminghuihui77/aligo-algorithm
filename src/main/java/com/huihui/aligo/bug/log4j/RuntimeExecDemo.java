package com.huihui.aligo.bug.log4j;

import java.io.IOException;

/**
 * @author minghui.y
 * @create 2021-12-12 3:11 下午
 **/
public class RuntimeExecDemo {


    public static void main( String[] args ) throws IOException {

        final Process exec = Runtime.getRuntime().exec( "open /System/Applications/Calculator.app" );

    }
}
