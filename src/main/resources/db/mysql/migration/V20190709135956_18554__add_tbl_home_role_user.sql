CREATE TABLE `tbl_home_role_user` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `home_role_id` bigint UNSIGNED NOT NULL,
  `home_user_id` bigint UNSIGNED NOT NULL,
  `is_deleted` tinyint UNSIGNED NOT NULL default 0 COMMENT '是否删除，0未删除，1已删除',
  UNIQUE INDEX `uni_role_id_user_id`(`home_role_id`, `home_user_id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '前台角色-用户关联表';