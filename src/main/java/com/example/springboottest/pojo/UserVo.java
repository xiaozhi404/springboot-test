package com.example.springboottest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;

@Data
@ApiModel("用户信息")
public class UserVo {

    @ApiModelProperty("用户id")
    @JsonProperty("id")
    @Id
    private Long id;

    @ApiModelProperty("用户名称")
    @JsonProperty("user_name")
    private String userName;

}
