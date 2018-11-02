package com.example.springboottest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTestApplicationTests {

    //spring boot 默认连接池为jdbc连接池
    //spring boot 默认日志实现为logback,他是log4j的增强版
    @Autowired
    DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        System.out.println("连接池为：" + dataSource.getClass());
        System.out.println("连接为：" + dataSource.getConnection());
    }

}
