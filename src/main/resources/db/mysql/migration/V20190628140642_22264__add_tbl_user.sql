CREATE TABLE `tbl_user` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role_id` bigint(20) UNSIGNED NOT NULL,
  `last_password_change` int(4) UNSIGNED NOT NULL COMMENT '最后一次密码修改时间',
  `enable` tinyint(4) UNSIGNED NOT NULL COMMENT '是否启用该账号，可以用来做黑名单',
  `created_at` int(4) UNSIGNED NOT NULL COMMENT '创建时间',
  `updated_at` int(4) UNSIGNED NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(4) UNSIGNED NOT NULL COMMENT '是否删除，0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '考试表';