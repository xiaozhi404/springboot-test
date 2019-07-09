CREATE TABLE `tbl_sys_permission` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `permission_type` int UNSIGNED NOT NULL COMMENT '权限类型，例如菜单权限',
  `resources_id` int UNSIGNED NOT NULL COMMENT '资源Id',
  `created_at` int UNSIGNED NOT NULL COMMENT '创建时间',
  `updated_at` int UNSIGNED NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint UNSIGNED NOT NULL default 0 COMMENT '是否删除，0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '权限表';