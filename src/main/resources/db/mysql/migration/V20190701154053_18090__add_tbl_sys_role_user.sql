CREATE TABLE `tbl_sys_role_user` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `sys_role_id` bigint UNSIGNED NOT NULL,
  `sys_user_id` bigint UNSIGNED NOT NULL,
  `is_deleted` tinyint UNSIGNED NOT NULL default 0 COMMENT '是否删除，0未删除，1已删除',
  UNIQUE INDEX `uni_role_id_user_id`(`sys_role_id`, `sys_user_id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '系统角色-用户关联表';