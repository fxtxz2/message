drop table if exists msg_group;

drop table if exists msg_info;

drop table if exists msg_rece;

drop table if exists sys_info;

drop index uq_userid_sourcesys on user_info;

drop table if exists user_info;

/*==============================================================*/
/* Table: msg_group                                             */
/*==============================================================*/
create table msg_group
(
   id                   varchar(32) not null comment '编号',
   sys_no               varchar(50) not null comment '来源系统',
   type                 int not null comment '类型[10系统、20个人]',
   name                 varchar(50) not null comment '名称',
   create_time          datetime not null comment '创建时间',
   primary key (id)
);

alter table msg_group comment '消息分组表';

/*==============================================================*/
/* Table: msg_info                                              */
/*==============================================================*/
create table msg_info
(
   id                   varchar(32) not null comment '编号',
   group_id             varchar(32) not null comment '消息分组编号',
   title                varchar(255) not null comment '标题',
   content              text comment '内容',
   create_time          datetime not null comment '创建时间',
   send_user_id         varchar(32) not null comment '发送人',
   status               int not null comment '状态[10待发送、20已发送]',
   send_time            datetime not null comment '发送时间',
   primary key (id)
);

alter table msg_info comment '消息表';

/*==============================================================*/
/* Table: msg_rece                                              */
/*==============================================================*/
create table msg_rece
(
   id                   varchar(32) not null comment '编号',
   msg_id               varchar(32) not null comment '消息编号',
   rece_user_id         varchar(32) not null comment '接收人',
   rece_time            datetime not null comment '接收时间',
   is_read              int not null comment '是否阅读',
   read_time            datetime comment '阅读时间',
   primary key (id)
);

alter table msg_rece comment '消息接收表';

/*==============================================================*/
/* Table: sys_info                                              */
/*==============================================================*/
create table sys_info
(
   sys_no               varchar(50) not null comment '系统编码',
   name                 varchar(150) not null comment '系统名称',
   create_time          datetime not null comment '创建时间',
   primary key (sys_no)
);

alter table sys_info comment '来源系统表';

/*==============================================================*/
/* Table: user_info                                             */
/*==============================================================*/
create table user_info
(
   id                   varchar(32) not null comment '编号',
   sys_no               varchar(50) not null comment '来源系统',
   user_id              varchar(32) not null comment '用户编号',
   create_time          datetime not null comment '创建时间',
   phone                varchar(255) comment '手机号[多个;分隔]',
   email                varchar(255) comment '邮箱[多个;分隔]',
   primary key (id)
);

alter table user_info comment '用户表';

/*==============================================================*/
/* Index: uq_userid_sourcesys                                   */
/*==============================================================*/
create unique index uq_userid_sourcesys on user_info
(
   sys_no,
   user_id
);
