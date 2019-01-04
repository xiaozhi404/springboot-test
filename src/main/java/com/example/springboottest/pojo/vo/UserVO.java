package com.example.springboottest.pojo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户VO信息")
public class UserVO {

    @ApiModelProperty("用户名称")
    @JsonProperty("user_name")
    private String userName;

    @ApiModelProperty("密码")
    @JsonProperty("password")
    private String password;
}
