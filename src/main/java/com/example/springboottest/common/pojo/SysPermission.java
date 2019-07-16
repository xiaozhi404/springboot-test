package com.example.springboottest.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.springboottest.admin.domain.constant.PermisionTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-12
 */
@Data
@TableName("tbl_sys_permission")
@ApiModel(value="SysPermission对象", description="权限表")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "权限类型，例如菜单权限")
    private PermisionTypeEnum permissionType;

    @ApiModelProperty(value = "资源Id")
    private Integer resourcesId;

    @ApiModelProperty(value = "创建时间")
    private Integer createdAt;

    @ApiModelProperty(value = "更新时间")
    private Integer updatedAt;

    @ApiModelProperty(value = "是否删除，0未删除，1已删除")
    private Integer isDeleted;

}
