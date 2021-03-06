CREATE TABLE `tbl_sys_role` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `auth` varchar(255) NOT NULL COMMENT '角色标识',
  `created_at` int UNSIGNED NOT NULL COMMENT '创建时间',
  `updated_at` int UNSIGNED NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint UNSIGNED NOT NULL default 0 COMMENT '是否删除，0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '角色表';

insert into `tbl_sys_role`(`name`,`auth`,`created_at`,`updated_at`)
values ('普通管理员', 'ROLE_ORDINARY_ADMIN', 1200000000, 1200000000);

insert into `tbl_sys_role`(`name`,`auth`,`created_at`,`updated_at`)
values ('超级管理员', 'ROLE_SUPER_ADMIN', 1200000000, 1200000000);

