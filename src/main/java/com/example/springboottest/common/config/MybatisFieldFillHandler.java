package com.example.springboottest.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * 描述：使用mp进行插入或者更新时，自动添加值
 */
@Component
public class MybatisFieldFillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", System.currentTimeMillis(), metaObject);
        this.setFieldValByName("updateTime", System.currentTimeMillis(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", System.currentTimeMillis(), metaObject);
    }
}