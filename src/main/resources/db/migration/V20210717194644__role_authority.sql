CREATE TABLE IF NOT EXISTS role_authority(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `role_id` INT NOT NULL COMMENT '角色id',
    `authority_id` INT NOT NULL COMMENT '權限id',
    `create_time` DATETIME NOT NULL COMMENT '創建時間',
    `update_time` DATETIME NOT NULL COMMENT '更新時間',
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='角色權限';