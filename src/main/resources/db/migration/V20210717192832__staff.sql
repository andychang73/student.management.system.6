CREATE TABLE IF NOT EXISTS staff(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `password` VARCHAR(45) NOT NULL COMMENT '密碼',
    `username` VARCHAR(45) NOT NULL COMMENT '使用者名稱',
    `birthday`DATETIME NULL COMMENT '生日',
    `email` VARCHAR(64) NULL COMMENT 'Email',
    `phone` VARCHAR(10) NULL COMMENT'電話',
    `address` VARCHAR(64) NULL COMMENT '地址',
    `report_to` INT NOT NULL COMMENT '職別',
    `status` TINYINT(1) DEFAULT 1 COMMENT '0 帳號凍結 / 1 正常',
    `first_time_login` TINYINT(1) DEFAULT 0 COMMENT '0 未登入 / 1 已登入',
    `creator` VARCHAR(45) NOT NULL COMMENT '創建人員',
    `create_time` DATETIME NOT NULL COMMENT '創建時間',
    `update_time` DATETIME NOT NULL COMMENT '更新時間',
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='職員資訊';