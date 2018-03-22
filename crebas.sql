/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/3/22 星期四 23:52:01                       */
/*==============================================================*/


drop table if exists cart;

drop table if exists commodit;

drop table if exists transaction;

drop index Index_username on user;

drop table if exists user;

/*==============================================================*/
/* Table: cart                                                  */
/*==============================================================*/
create table cart
(
   user_id              bigint not null,
   commodit_id          bigint not null,
   volumn               int not null,
   add_time             timestamp not null,
   primary key (user_id, commodit_id)
);

alter table cart comment '购物车表';

/*==============================================================*/
/* Table: commodit                                              */
/*==============================================================*/
create table commodit
(
   id                   bigint not null,
   name                 varchar(40) not null,
   owner_id             bigint not null,
   volumn               int not null default 0,
   price                decimal(10,2) not null default 0,
   primary key (id)
);

alter table commodit comment '商品信息表';

/*==============================================================*/
/* Table: transaction                                           */
/*==============================================================*/
create table transaction
(
   id                   bigint not null,
   buyer_id             bigint not null,
   seller_id            bigint not null,
   commodit_id          bigint not null,
   volumn               int not null,
   price                decimal(10,2) not null,
   produce_time         timestamp not null,
   primary key (id)
);

alter table transaction comment '订单表';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   bigint not null auto_increment,
   username             varchar(20) not null,
   password             varchar(20) not null,
   register_time        timestamp not null,
   active               smallint not null default 1 comment '0用户被禁用',
   balance              decimal(17,2) default 0,
   primary key (id),
   key AK_Key_username (username)
);

alter table user comment '用户表';

/*==============================================================*/
/* Index: Index_username                                        */
/*==============================================================*/
create unique index Index_username on user
(
   username
);

alter table cart add constraint FK_Reference_cart_commodit_id foreign key (commodit_id)
      references commodit (id) on delete restrict on update restrict;

alter table cart add constraint FK_Reference_cart_user_id foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table commodit add constraint FK_Reference_commit_owner_id foreign key (owner_id)
      references user (id) on delete restrict on update restrict;

alter table transaction add constraint FK_Reference_transaction_buyer_id foreign key (buyer_id)
      references user (id) on delete restrict on update restrict;

alter table transaction add constraint FK_Reference_transaction_commodit_id foreign key (commodit_id)
      references commodit (id) on delete restrict on update restrict;

alter table transaction add constraint FK_Reference_transaction_seller_id foreign key (seller_id)
      references user (id) on delete restrict on update restrict;

