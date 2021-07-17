CREATE TABLE IF NOT EXISTS user_role(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `user_id` INT NOT NULL COMMENT '使用者id',
    `role_id` INT NOT NULL COMMENT '角色id',
    `group_id` INT NOT NULL COMMENT '群組id',
    `status` TINYINT(1) DEFAULT 1 COMMENT '0 停用 / 1 正常',
    `create_time` DATETIME NOT NULL COMMENT '創建時間',
    `update_time` DATETIME NOT NULL COMMENT '更新時間',
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='使用者角色';