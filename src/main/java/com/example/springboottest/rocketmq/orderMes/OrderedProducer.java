package com.example.springboottest.rocketmq.orderMes;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * 实现：生产者往同个队列顺序发，消费者往同个队列顺序消费
 */
public class OrderedProducer {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("example_group_name");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        String[] tags = new String[] {"createTag", "payTag", "sendTag"};

        //订单消息
        for (int orderId = 0; orderId < 3; orderId++) {

            //每种订单分为：创建订单消息 、支付订单、发货订单
            for (int type = 0; type < 3; type++) {
                Message msg = new Message("orderTopic", tags[type % tags.length], orderId+":"+type,
                        (orderId+":"+type).getBytes());

                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    //选择队列发送，同一个订单的消息发送到同一个队列
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, orderId);

                System.out.println(sendResult);
            }

        }
        producer.shutdown();
        System.out.println("producer 已经发送");
    }
}
