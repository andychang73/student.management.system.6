CREATE TABLE IF NOT EXISTS student(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `password` VARCHAR(64) NOT NULL COMMENT '密碼',
    `username` VARCHAR(45) NOT NULL COMMENT '使用者名稱',
    `birthday` DATE DEFAULT NULL COMMENT '生日',
    `email` VARCHAR(64) DEFAULT NULL COMMENT 'Email',
    `phone` VARCHAR(10) DEFAULT NULL COMMENT'電話',
    `address` VARCHAR(64) DEFAULT NULL COMMENT '地址',
    `status` TINYINT(1) DEFAULT 1 COMMENT '0 帳號凍結 / 1 正常',
    `first_time_login` TINYINT(1) DEFAULT 0 COMMENT '0 未登入 / 1 已登入',
    `creator` VARCHAR(45) NOT NULL COMMENT '創建人員',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='學生資訊';

CREATE TABLE IF NOT EXISTS staff(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `password` VARCHAR(64) NOT NULL COMMENT '密碼',
    `username` VARCHAR(45) NOT NULL COMMENT '使用者名稱',
    `birthday`DATE DEFAULT NULL COMMENT '生日',
    `email` VARCHAR(64) DEFAULT NULL COMMENT 'Email',
    `phone` VARCHAR(10) DEFAULT NULL COMMENT'電話',
    `address` VARCHAR(64) DEFAULT NULL COMMENT '地址',
    `report_to` INT UNSIGNED NOT NULL COMMENT '主管id',
    `status` TINYINT(1) DEFAULT 1 COMMENT '0 帳號凍結 / 1 正常',
    `first_time_login` TINYINT(1) DEFAULT 0 COMMENT '0 未登入 / 1 已登入',
    `creator` VARCHAR(45) NOT NULL COMMENT '創建人員',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='職員資訊';

CREATE TABLE IF NOT EXISTS user_role(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `user_id` INT UNSIGNED NOT NULL COMMENT '使用者id',
    `role_id` INT UNSIGNED NOT NULL COMMENT '角色id',
    `group_id` INT UNSIGNED NOT NULL COMMENT '群組id',
    `status` TINYINT(1) DEFAULT 1 COMMENT '0 停用 / 1 正常',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='使用者角色';

CREATE TABLE IF NOT EXISTS role(
   `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
   `role` VARCHAR(45) NOT NULL COMMENT '角色',
   `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
   `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
   PRIMARY KEY(id),
   UNIQUE KEY `role_unique`(role)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='角色';

CREATE TABLE IF NOT EXISTS role_authority(
     `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
     `role_id` INT UNSIGNED NOT NULL COMMENT '角色id',
     `authority_id` INT UNSIGNED NOT NULL COMMENT '權限id',
     `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
     `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
     PRIMARY KEY(id),
     UNIQUE KEY `index_unique`(role_id, authority_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='角色權限';

CREATE TABLE IF NOT EXISTS authority(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `authority` VARCHAR(45) NOT NULL COMMENT '權限',
    `description` VARCHAR(64) DEFAULT NULL COMMENT '權限說明',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY(id),
    UNIQUE KEY `authority_unique`(authority)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='權限';

CREATE TABLE IF NOT EXISTS college(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `college` VARCHAR(64) NOT NULL COMMENT '學校',
    `status` TINYINT(1) DEFAULT 1 COMMENT '0 停用 / 1 正常',
    `creator` VARCHAR(45) NOT NULL COMMENT '創建人員',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY(id),
    UNIQUE KEY `college_unique`(college)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='學校資訊';

CREATE TABLE IF NOT EXISTS major(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `college_id` INT UNSIGNED NOT NULL COMMENT '學校id',
    `major` VARCHAR(64) NOT NULL COMMENT '學院',
    `status` TINYINT(1) DEFAULT 1 COMMENT '0 停用 / 1 正常',
    `creator` VARCHAR(45) NOT NULL COMMENT '創建人員',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY(id),
    UNIQUE KEY `major_unique`(major)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='學院';

CREATE TABLE IF NOT EXISTS major_course(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `major_id` INT UNSIGNED NOT NULL COMMENT '學院id',
    `course_id` INT UNSIGNED NOT NULL COMMENT '課程id',
    `status` TINYINT(1) DEFAULT 1 COMMENT '0 停用 / 1 正常',
    `creator` VARCHAR(45) NOT NULL COMMENT '創建人員',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY(id),
    UNIQUE KEY `index_unique`(major_id, course_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='學院主修課程';

CREATE TABLE IF NOT EXISTS course(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `course` VARCHAR(64) NOT NULL COMMENT '課程',
    `head_id` INT UNSIGNED NOT NULL COMMENT '主任id',
    `status` TINYINT(1) DEFAULT 1 COMMENT '0 停用 / 1 正常',
    `creator` VARCHAR(45) NOT NULL COMMENT '創建人員',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY(id),
    UNIQUE KEY `course_unique`(course)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='課程';

CREATE TABLE IF NOT EXISTS pre_course(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `course_id` INT UNSIGNED NOT NULL COMMENT '課程id',
    `pre_course_id` INT UNSIGNED NOT NULL COMMENT '先修課程id',
    `status` TINYINT(1) DEFAULT 1 COMMENT '0 停用 / 1 正常',
    `creator` VARCHAR(45) NOT NULL COMMENT '創建人員',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY(id),
    UNIQUE KEY `index_unique`(course_id, pre_course_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='先修課程';

CREATE TABLE IF NOT EXISTS class(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `course_id` INT UNSIGNED NOT NULL COMMENT '課程id',
    `teacher_id` INT UNSIGNED NOT NULL COMMENT '老師id',
    `week_day` INT NOT NULL COMMENT '每週上課時間',
    `start_time` TIME NOT NULL COMMENT '上課開始時間',
    `end_time` TIME NOT NULL COMMENT '上課結束時間',
    `maximum` INT DEFAULT NULL COMMENT '最大上課人數限制',
    `status` TINYINT(1) DEFAULT 1 COMMENT '0 停用 / 1 正常',
    `version` INT DEFAULT 0 COMMENT '課程難度級數',
    `creator` VARCHAR(45) NOT NULL COMMENT '創建人員',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY(id),
    UNIQUE KEY `index_unique`(course_id, teacher_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='班級';

CREATE TABLE IF NOT EXISTS semester_class(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `semaster_id` INT UNSIGNED NOT NULL COMMENT '學期id',
    `class_id` INT UNSIGNED NOT NULL COMMENT '班級id',
    `week_no` INT NOT NULL COMMENT '當前週數',
    `date` DATETIME NOT NULL COMMENT '開課日期',
    `status` TINYINT(1) DEFAULT 1 COMMENT '0 停用 / 1 正常',
    PRIMARY KEY(id),
    UNIQUE KEY `index_unique`(semaster_id, class_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='學期班級';

CREATE TABLE IF NOT EXISTS semester(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `year` INT NOT NULL COMMENT '年度',
    `semester` INT NOT NULL COMMENT '學期',
    `start_time` DATE NOT NULL COMMENT '上課開始時間',
    `end_time` DATE NOT NULL COMMENT '上課結束時間',
    `num_of_weeks` INT NOT NULL COMMENT '上課週數',
    `status` TINYINT(1) DEFAULT 1 COMMENT '0 停用 / 1 正常',
    `creator` VARCHAR(45) NOT NULL COMMENT '創建人員',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY(id),
    UNIQUE KEY `index_unique`(year, semester)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='學期';

CREATE TABLE IF NOT EXISTS assesment(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `semester_id` INT UNSIGNED NOT NULL COMMENT '學期id',
    `staff_id` INT UNSIGNED NOT NULL COMMENT '職員id',
    `performance` FLOAT(3,1) NOT NULL COMMENT '表现',
    `assessed_by` INT NOT NULL COMMENT '評估人員',
    `status` TINYINT(1) DEFAULT 1 COMMENT '0 停用 / 1 正常',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY(id),
    UNIQUE KEY `index_unique`(semester_id, staff_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='學期評估';

CREATE TABLE IF NOT EXISTS student_class(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `student_id` INT UNSIGNED NOT NULL COMMENT '學生id',
    `class_id` INT UNSIGNED NOT NULL COMMENT '班級id',
    `status` TINYINT(1) DEFAULT 1 COMMENT '0 停用 / 1 正常',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY(id),
    UNIQUE KEY `index_unique`(student_id, class_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='學生班級';

CREATE TABLE IF NOT EXISTS student_major(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `student_id` INT UNSIGNED NOT NULL COMMENT '學生id',
    `major_id` INT UNSIGNED NOT NULL COMMENT '學院id',
    `status` TINYINT(1) DEFAULT 1 COMMENT '0 停用 / 1 正常',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY(id),
    UNIQUE KEY `index_unique`(student_id, major_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='學院學生';

CREATE TABLE IF NOT EXISTS student_course(
     `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
     `student_id` INT UNSIGNED NOT NULL COMMENT '學生id',
     `course_id` INT UNSIGNED NOT NULL COMMENT '課程id',
     `grade` FLOAT(2,0) NOT NULL COMMENT '成績',
     `attendance` FLOAT(4,1) NOT NULL COMMENT '出席率',
     `semester_id` INT UNSIGNED NOT NULL COMMENT '學期id',
     `status` TINYINT(1) DEFAULT 1 COMMENT '0 停用 / 1 正常',
     `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
     `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
     PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='學生課程';

CREATE TABLE IF NOT EXISTS attendance(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `semester_class_id` INT UNSIGNED NOT NULL COMMENT '學期班級id',
    `student_id` INT UNSIGNED NOT NULL COMMENT '學生id',
    `status` TINYINT(1) DEFAULT 1 COMMENT '0 未出席 / 1 出席',
    `taken_by` VARCHAR(45) NOT NULL COMMENT '更新人員',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY(id),
    UNIQUE KEY `index_unique`(semester_class_id, student_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='出席率';

CREATE TABLE IF NOT EXISTS homework(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `semester_class_id` INT UNSIGNED NOT NULL COMMENT '學期班級id',
    `homework` JSON NOT NULL COMMENT '作業',
    `deadline` DATETIME NOT NULL COMMENT '繳交期限',
    `status` TINYINT(1) DEFAULT 1 COMMENT '0 停用 / 1 正常',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY(id),
    UNIQUE KEY `index_unique`(semester_class_id, id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='作業';

CREATE TABLE IF NOT EXISTS student_homework(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `homework_id` INT UNSIGNED NOT NULL COMMENT '作業id',
    `student_id` INT UNSIGNED NOT NULL COMMENT '學生id',
    `answers` JSON NOT NULL COMMENT '作業答案',
    `grade` FLOAT(2,0) NOT NULL COMMENT '成績',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    `update_time` DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY(id),
    UNIQUE KEY `index_unique`(homework_id, student_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT ='學生作業';