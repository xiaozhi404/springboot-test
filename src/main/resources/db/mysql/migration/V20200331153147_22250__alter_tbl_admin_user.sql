
delete from `tbl_admin_user` where `is_deleted` = 1;

alter table `tbl_admin_user` add column `email` VARCHAR(100) NOT NULL COMMENT '企业邮箱';

alter table `tbl_admin_user` add column `internal_account_id` bigint NOT NULL COMMENT '内部账号id';

alter table `tbl_admin_user` drop column `password`;
alter table `tbl_admin_user` drop column `pwd_salt`;
alter table `tbl_admin_user` drop column `account`;
alter table `tbl_admin_user` drop column `is_deleted`;

