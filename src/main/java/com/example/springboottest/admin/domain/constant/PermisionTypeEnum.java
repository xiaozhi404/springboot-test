package com.example.springboottest.admin.domain.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum PermisionTypeEnum {

    MENU(1, "菜单类型"),  BUTTON(2, "按钮类型");

    PermisionTypeEnum(int permissionType, String descp) {
        this.permissionType = permissionType;
        this.descp = descp;
    }

    @EnumValue
    private final int permissionType;

    private final String descp;


    public int getPermissionType() {
        return permissionType;
    }
}