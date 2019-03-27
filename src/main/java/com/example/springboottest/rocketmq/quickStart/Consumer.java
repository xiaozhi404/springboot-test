package com.example.springboottest.rocketmq.quickStart;

import com.example.springboottest.config.RocketMQProp;
import com.example.springboottest.pojo.User;
import com.example.springboottest.pojo.UserVo;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class Consumer {

    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        User user = new User();
        user.setPassword("1234");
        user.setId(1l);
        User user2 = new User();
        user.setPassword("1234");
        user.setId(2l);
        users.add(user);
        users.add(user2);
        ArrayList<User> users2 = new ArrayList<>();
        for (User a: users) {
            new UserVo();
            BeanUtils.copyProperties(users, user2);
        }

        System.out.println(user2);
    }

    public void

    @Autowired
    private RocketMQProp rocketMQProp;

    //在bean初始化并且注入之后执行
    @PostConstruct
    public void defaultMQPushConsumer() {
        //消费者的组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketMQProp.getConsumerGroup());
        //指定NameServer地址，多个地址以 ; 隔开
        consumer.setNamesrvAddr(rocketMQProp.getNamesrvAddr());
        try {
            //订阅PushTopic下Tag为push的消息
            consumer.subscribe("PushTopic", "push");
            //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
            //如果非第一次启动，那么按照上次消费的位置继续消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            //Consumer Push的方式:即设置Listener机制回调，相当于开启了一个线程
            consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
                try {
                    for (MessageExt messageExt : list) {
                        System.out.println("messageExt: " + messageExt);//输出消息内容
                        String messageBody = new String(messageExt.getBody(), "utf-8");
                        System.out.println("消费响应：Msg: " + messageExt.getMsgId() + ",msgBody: " + messageBody);//输出消息内容
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER; //稍后再试
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //消费成功
            });
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

