package com.huihui.aligo.thread.base;

/**
 * 练习volatile关键字
 *
 * @author minghui.y
 * @create 2021-09-04 11:50 上午
 **/
public class VolatileDemo {

    /**
     * 共享变量
     */
    private static /**volatile*/  boolean running = true;


    /**
     * 实际结果：running不使用volatile关键字，主线程修改running=false之后，t线程也能感知到
     * 结论：本案例暂时测不出volatile的可见性
     * @param args
     * @throws InterruptedException
     */
    public static void main( String[] args ) throws InterruptedException {

        Thread t = new Thread(() -> {
            int count = 0;
            while (running) {
                System.out.println(Thread.currentThread().getName() + "打印：" + count++);
                try {
                    Thread.sleep( 500 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+ "执行结束");
        } );

        t.start();

        Thread.sleep( 1000 );

        running = false;

    }




}
