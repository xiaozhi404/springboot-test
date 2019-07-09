package com.example.springboottest.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 前台角色-用户关联表
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-09
 */
@TableName("tbl_home_role_user")
@ApiModel(value="HomeRoleUser对象", description="前台角色-用户关联表")
public class HomeRoleUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Long homeRoleId;

    private Long homeUserId;

    @ApiModelProperty(value = "是否删除，0未删除，1已删除")
    private Integer isDeleted;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getHomeRoleId() {
        return homeRoleId;
    }

    public void setHomeRoleId(Long homeRoleId) {
        this.homeRoleId = homeRoleId;
    }

    public Long getHomeUserId() {
        return homeUserId;
    }

    public void setHomeUserId(Long homeUserId) {
        this.homeUserId = homeUserId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "HomeRoleUser{" +
        "id=" + id +
        ", homeRoleId=" + homeRoleId +
        ", homeUserId=" + homeUserId +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
