package com.example.springboottest.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 系统角色-用户关联表
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-08
 */
@TableName("tbl_sys_role_user")
@ApiModel(value="SysRoleUser对象", description="系统角色-用户关联表")
public class SysRoleUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Long sysRoleId;

    private Long sysUserId;

    @ApiModelProperty(value = "是否删除，0未删除，1已删除")
    private Integer isDeleted;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "SysRoleUser{" +
        "id=" + id +
        ", sysRoleId=" + sysRoleId +
        ", sysUserId=" + sysUserId +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
