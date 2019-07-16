package com.example.springboottest.admin.domain.outbound;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@Data
public class MenuOutbound {

    private Integer id;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单资源路径")
    private String url;

    private Integer parentId;

    private List<MenuOutbound> subMenus;


}
