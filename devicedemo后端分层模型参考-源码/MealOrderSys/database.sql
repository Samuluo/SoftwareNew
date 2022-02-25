
use zrgj2;
create table `user`
(
    `id`       int auto_increment primary key,
    `name`     varchar(32) null,
    `password` varchar(512) null,
    `status`   int         null,
    --0为管理员，1为后厨，2为前台服务员
    `avatar`   varchar(256) null,
    `phone`    char(11)    null
);

create table `meal`
(
    `id`     int auto_increment primary key,
    `name`    varchar(32) null,
    `type`    varchar(32) null,
    `detail`  varchar(512)  null,
    `status`  int null,
    `isGood`  int null,
    `img`     varchar(256) null,
    `price`   float null,
    --库存
    `stock`   int   null
);

create table `order`
(
    `id`      int auto_increment primary key,
    `table`   int null,
    `list_id` int null,
    `time`    datetime null,
    `isCompleted` int null,
    `writer_id`   int null
);

create table `list`
(
    `id`      int auto_increment primary key,
    `order_id` int null,
    `meal_id` int null,
    `meal_number` int null,
    `meal_name` varchar(32) null
);

create table `notice`
(
    `id`      int auto_increment primary key,
    `name`    varchar(32) null,
    `detail`  varchar(255) null,
    `userlist_id` int null,
    birthdate datetime null,
    writer_id int null
);

create table `notice_list`
(
    `id`      int auto_increment primary key,
    `notice_id`  int null,
    `user_id`    int null
)
