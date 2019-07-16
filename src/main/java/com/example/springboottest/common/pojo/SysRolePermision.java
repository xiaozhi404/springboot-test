package com.example.springboottest.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 系统角色-权限关联表
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-12
 */
@TableName("tbl_sys_role_permision")
@ApiModel(value="SysRolePermision对象", description="系统角色-权限关联表")
public class SysRolePermision implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer sysRoleId;

    private Integer sysPemisionId;

    @ApiModelProperty(value = "是否删除，0未删除，1已删除")
    private Integer isDeleted;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public Integer getSysPemisionId() {
        return sysPemisionId;
    }

    public void setSysPemisionId(Integer sysPemisionId) {
        this.sysPemisionId = sysPemisionId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "SysRolePermision{" +
        "id=" + id +
        ", sysRoleId=" + sysRoleId +
        ", sysPemisionId=" + sysPemisionId +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
