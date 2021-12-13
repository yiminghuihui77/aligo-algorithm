package com.huihui.aligo.bug.log4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author minghui.y
 * @create 2021-12-12 2:50 下午
 **/
public class SocketServerDemo {

    private static int PORT = 1234;

    public static void main( String[] args ) throws IOException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(PORT);

        //服务端接收一个连接
        Socket client = serverSocket.accept();
        System.out.println("Connected to client at " + client.getInetAddress());
        byte[] buff = new byte[1024];

        ObjectInputStream ois = new ObjectInputStream( client.getInputStream() );
         Object o = ois.readObject();
        System.out.println(o);

    }
}
