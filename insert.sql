INSERT INTO `commodit` (`id`, `name`, `owner_id`, `volumn`, `price`) VALUES ('1', '钢笔', '1', '10', '15.50');
INSERT INTO `commodit` (`id`, `name`, `owner_id`, `volumn`, `price`) VALUES ('2', '练习本', '1', '20', '10');

INSERT INTO `user`
(`id`,
`username`,
`password`,
`register_time`,
`active`,
`balance`)
VALUES
(1,
'admin',
'123456',
CURRENT_TIMESTAMP,
1,
0.00);

INSERT INTO `user`
(`id`,
`username`,
`password`,
`register_time`,
`active`,
`balance`)
VALUES
(2,
'test',
'123456',
CURRENT_TIMESTAMP,
1,
0.00);
