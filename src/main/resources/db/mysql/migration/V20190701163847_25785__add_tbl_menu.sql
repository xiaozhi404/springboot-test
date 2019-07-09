CREATE TABLE `tbl_menu` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '菜单名称',
  `url` varchar(255) NOT NULL COMMENT '菜单资源路径',
  `parent_id` int UNSIGNED NOT NULL,
  `created_at` int UNSIGNED NOT NULL COMMENT '创建时间',
  `updated_at` int UNSIGNED NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint UNSIGNED NOT NULL default 0 COMMENT '是否删除，0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '资源菜单表';