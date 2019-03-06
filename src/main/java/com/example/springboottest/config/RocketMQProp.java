package com.example.springboottest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("apache.rocketmq")
@Data
public class RocketMQProp {

    /**
     * NameServer 地址
     */
    private String namesrvAddr;

    /**
     * 消费者的组名
     */
    private String consumerGroup;

    /**
     * 生产者的组名
     */
    private String producerGroup;
}
