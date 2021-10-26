package com.huihui.aligo.system;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个队列实现栈
 *
 * 入栈：往pushQueue正常入队
 * 出栈：pushQueue除队尾节点不移动，其他节点从队首开始入队到pollQueue，交换两个队列只，pollQueue出队一个元素(出队后pollQueue为空)
 *
 * @author minghui.y
 * @create 2021-10-26 2:22 下午
 **/
public class SystemAlgorithm_6 {


    public static void main( String[] args ) {
        TwoQueueStack stack = new TwoQueueStack();

        //入栈
        stack.push( 1 );
        stack.push( 2 );
        stack.push( 3 );
        stack.push( 4 );

        //出栈
        System.out.println(stack.pop());
        System.out.println(stack.pop());


    }


    public static class TwoQueueStack {
        //入队的队列
        private Queue<Integer> pushQueue;
        //出队的队列
        private Queue<Integer> pollQueue;

        public TwoQueueStack() {
            pushQueue = new LinkedList<>();
            pollQueue = new LinkedList<>();
        }


        /**
         * 入栈
         * @param data
         */
        public void push(Integer data) {
            pushQueue.add( data );
        }

        /**
         * 出栈
         * 特殊处理：每次出队都交换队列
         * @return
         */
        public Integer pop() {
            if (pushQueue.isEmpty() && pollQueue.isEmpty()) {
                throw new RuntimeException("栈为空");
            }
            exchangeQueue();

            return pollQueue.poll();
        }


        /**
         * 交换机
         */
        public void exchangeQueue() {
            if (pollQueue.isEmpty()) {
                //最后一个元素用于弹出
                while (pushQueue.size() > 1) {
                    pollQueue.add( pushQueue.poll() );
                }
                //队列互换
                Queue<Integer> temp = pushQueue;
                pushQueue = pollQueue;
                pollQueue = temp;

            }
        }




    }
}
