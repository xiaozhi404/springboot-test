package com.example.springboottest.rocketmq.quickStart;

import com.example.springboottest.config.RocketMQProp;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class Producer {

    @Autowired
    private RocketMQProp rocketMQProp;

    //@PostConstruct
    public void defaultMQProducer() {

        //生产者的组名
        DefaultMQProducer producer = new DefaultMQProducer(rocketMQProp.getProducerGroup());

        //指定NameServer地址，多个地址以 ; 隔开
        producer.setNamesrvAddr(rocketMQProp.getNamesrvAddr());

        //若发送给mq失败，则重试3次
        producer.setRetryTimesWhenSendFailed(3);

        try {

            /**
             * Producer对象在使用之前必须要调用start初始化，初始化一次即可
             * 注意：切记不可以在每次发送消息时，都调用start方法
             */
            producer.start();

            for (int i = 0; i < 100; i++) {

                String messageBody = "我是消息内容:" + i;

                String message = new String(messageBody.getBytes(), "utf-8");

                //构建消息
                Message msg = new Message("PushTopic" /* PushTopic */, "push"/* Tag  */, "key_" + i /* Keys */, message.getBytes());

                //同步发送消息
                SendResult result = producer.send(msg);
                System.out.println("发送响应：MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus());

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }

    }
}

