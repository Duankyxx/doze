# 快速开始
## doze-api-module(后台api模块)
    * 运行target目录下的.jar文件
    * java -jar 文件名及目录
## doze-client(用户模块)
    * 在doze-client目录下打开cmd
    * yarn run serve
## doze-merchant(商家模块)
    * 在doze-merchant目录下打开cmd
    * yarn run serve
## 数据库搭建mysql
    依次运行
    
    create table user
    (
        id       int auto_increment
        primary key,
        username varchar(20)          not null comment '用户名',
        password varchar(20)          not null comment '密码',
        level    tinyint(1) default 0 null comment '是否管理员',
        enable   tinyint(1) default 1 null comment '是否启用',
        constraint user_id_uindex
        unique (id)
    );

    create table tbbar
    (
        id       int auto_increment
        primary key,
        menuName varchar(20)          not null comment '导航名称',
        icon     text                 not null comment '图标',
        level    tinyint(1) default 1 not null comment '是否管理员',
        menuLink varchar(50)          not null comment '导航链接',
        constraint tbBar_id_uindex
        unique (id),
        constraint tbBar_menuLink_uindex
        unique (menuLink),
        constraint tbBar_menuName_uindex
        unique (menuName)
    );

    create table tbcookbook
    (
        id               int auto_increment
        primary key,
        uid              int                  not null comment '用户id',
        cookName         varchar(20)          not null comment '名称',
        cookPicture      text                 null comment '图片',
        price            double               not null comment '价钱',
        isGood           tinyint(1) default 0 null comment '是否本店推荐',
        isSell           tinyint(1) default 1 null comment '是否启用',
        cookLabel        text                 null comment '商品标签',
        purchaseQuantity int        default 0 null comment '购买人数',
        constraint tbCookBook_id_uindex
        unique (id),
        constraint uid
        foreign key (uid) references user (id)
        on delete cascade
    );

    create table `order`
    (
        id         int auto_increment comment '订单流水号'
        primary key,
        seatNumber int         not null comment '餐桌号',
        time       varchar(20) not null comment '订单时间',
        remarks    text        null comment '备注',
        payment    double      not null comment '收款',
        uid        int         not null comment '商家id',
        constraint order_id_uindex
        unique (id),
        constraint uid___fk
        foreign key (uid) references user (id)
    );

    create table receipt
    (
        id           int auto_increment
        primary key,
        CookName     varchar(20) not null comment '名称',
        quantity     int         not null comment '数量',
        price        double      not null comment '单价',
        serialNumber int         not null comment '流水单号',
        constraint receipt_id_uindex
        unique (id),
        constraint serialNumber
        foreign key (serialNumber) references `order` (id)
        on delete cascade
    );

    create table userdetailed
    (
        id         int auto_increment
        primary key,
        userId     int         null,
        shopName   varchar(20) null comment '餐馆名称',
        isNewOrder tinyint(1)  null comment '是否有新的订单',
        constraint userDetailed_id_uindex
        unique (id),
        constraint userId
        foreign key (userId) references user (id)
        on delete cascade
    );
  
****  
# 技术栈
## 后端
* java
* springboot
* mybatis
##前端
* vue3-cli
* scss
* typescript
* axios
* element-plus
## 数据库
* mysql

****
# 更新及说明
* 因为项目是一个人开发，而且周期比较短15天，大部分功能都无法使用，只实现了核心功能,并且由于是前端出身（现大1），所以后端代码比较凌乱和不堪,并没有接触编程多久;