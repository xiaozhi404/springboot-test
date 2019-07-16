package com.example.springboottest.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@Data
@ApiModel(value="SysRoleUser对象", description="系统角色-用户关联表")
public class SysRoleUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer sysRoleId;

    private Integer sysUserId;

    @ApiModelProperty(value = "是否删除，0未删除，1已删除")
    private Integer isDeleted;


}
