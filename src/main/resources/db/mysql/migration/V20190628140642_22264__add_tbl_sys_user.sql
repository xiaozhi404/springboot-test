CREATE TABLE `tbl_sys_user` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `last_password_change` int UNSIGNED NOT NULL COMMENT '最后一次密码修改时间',
  `enable` tinyint UNSIGNED NOT NULL COMMENT '是否启用该账号，可以用来做黑名单',
  `created_at` int UNSIGNED NOT NULL COMMENT '创建时间',
  `updated_at` int UNSIGNED NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint UNSIGNED NOT NULL default 0 COMMENT '是否删除，0未删除，1已删除',
  UNIQUE INDEX `uni_user_name`(`user_name`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '后台系统用户';