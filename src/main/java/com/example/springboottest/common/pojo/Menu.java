package com.example.springboottest.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 资源菜单表
 * </p>
 *
 * @author xiaozhi
 * @since 2019-07-12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tbl_menu")
@ApiModel(value="Menu对象", description="资源菜单表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单资源路径")
    private String url;

    private Integer parentId;

    @ApiModelProperty(value = "创建时间")
    private Integer createdAt;

    @ApiModelProperty(value = "更新时间")
    private Integer updatedAt;

    @ApiModelProperty(value = "是否删除，0未删除，1已删除")
    private Integer isDeleted;

}
