CREATE TABLE IF NOT EXISTS authority(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `authority`         VARCHAR(32) NOT NULL,
    `description`       VARCHAR(128),
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE (authority)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS role_authority(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `role_id`           INT UNSIGNED NOT NULL,
    `authority_id`      INT UNSIGNED NOT NULL,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE(role_id, authority_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS role(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `role`              VARCHAR(32) NOT NULL,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE(role)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS user_role(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id`           INT UNSIGNED NOT NULL,
    `role_id`           INT UNSIGNED NOT NULL,
    `group_id`          INT UNSIGNED NOT NULL,
    `status`            TINYINT UNSIGNED NOT NULL DEFAULT 1,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE(user_id, role_id, group_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS student(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `password`          VARCHAR(64) NOT NULL,
    `username`          VARCHAR(64) NOT NULL,
    `birthday`          DATE NOT NULL ,
    `email`             VARCHAR(64) NOT NULL,
    `phone`             VARCHAR(8) NOT NULL,
    `address`           VARCHAR(64) NOT NULL,
    `first_time_login`  TINYINT UNSIGNED NOT NULL DEFAULT 1,
    `status`            TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '0 Account Frozen / 1 Normal',
    `creator`           VARCHAR(64) NOT NULL,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS staff(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `password`          VARCHAR(64) NOT NULL,
    `username`          VARCHAR(64) NOT NULL,
    `birthday`          DATE NOT NULL ,
    `email`             VARCHAR(64) NOT NULL,
    `phone`             VARCHAR(16) NOT NULL,
    `address`           VARCHAR(64) NOT NULL,
    `report_to`         INT UNSIGNED NOT NULL,
    `first_time_login`  TINYINT UNSIGNED NOT NULL DEFAULT 1,
    `status`            TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '0 Account Frozen / 1 Normal',
    `creator`           VARCHAR(64) NOT NULL,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS student_major(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `student_id`        INT UNSIGNED NOT NULL,
    `major_id`          INT UNSIGNED NOT NULL,
    `status`            TINYINT UNSIGNED NOT NULL DEFAULT 1,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE(student_id, major_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS major(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `college_id`        INT UNSIGNED NOT NULL,
    `major`             VARCHAR(64) NOT NULL,
    `status`            TINYINT UNSIGNED NOT NULL DEFAULT 1,
    `creator`           VARCHAR(64) NOT NULL,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE (major)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS college(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `college`           VARCHAR(64) NOT NULL,
    `status`            TINYINT UNSIGNED NOT NULL DEFAULT 1,
    `creator`           VARCHAR(64) NOT NULL,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE (college)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS major_course(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `major_id`          INT UNSIGNED NOT NULL,
    `course_id`         INT UNSIGNED NOT NULL,
    `status`            TINYINT UNSIGNED NOT NULL DEFAULT 1,
    `creator`           VARCHAR(64) NOT NULL,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE KEY (major_id, course_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS course(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `course`            VARCHAR(64) NOT NULL,
    `head_id`           INT UNSIGNED NOT NULL,
    `status`            TINYINT UNSIGNED NOT NULL DEFAULT 1,
    `creator`           VARCHAR(64) NOT NULL,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE (course)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS pre_course(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `course_id`         INT UNSIGNED NOT NULL,
    `pre_course_id`     INT UNSIGNED NOT NULL,
    `status`            TINYINT UNSIGNED NOT NULL DEFAULT 1,
    `creator`           VARCHAR(64) NOT NULL,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE (course_id, pre_course_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS student_course(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `semester_id`       INT UNSIGNED,
    `student_id`        INT UNSIGNED NOT NULL,
    `course_id`         INT UNSIGNED NOT NULL,
    `grade`             FLOAT(2,2),
    `attendance`        FLOAT(2,2),
    `status`            TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '0 Dropped / 1 In progress / 2 Passed / 3 Failed',
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE (student_id, course_id, semester_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS student_class(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `student_id`        INT UNSIGNED NOT NULL,
    `class_id`          INT UNSIGNED NOT NULL,
    `status`            TINYINT UNSIGNED NOT NULL DEFAULT 1,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE (student_id, class_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS class(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `course_id`         INT UNSIGNED NOT NULL,
    `staff_id`          INT UNSIGNED NOT NULL,
    `semester_id`       INT UNSIGNED NOT NULL,
    `week_day`          INT UNSIGNED NOT NULL COMMENT '1 Monday / 2 Tuesday / 3 Wednesday / 4 Thursday / Friday',
    `start_time`        TIME NOT NULL,
    `end_time`          TIME NOT NULL,
    `maximum`           INT UNSIGNED NOT NULL,
    `version`           INT UNSIGNED NOT NULL COMMENT 'Optimistic lock version number',
    `status`            TINYINT UNSIGNED NOT NULL DEFAULT 1,
    `creator`           VARCHAR(64) NOT NULL,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE (course_id, staff_id, semester_id, week_day, start_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS semester_class(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `class_id`          INT UNSIGNED NOT NULL,
    `week_no`           TINYINT UNSIGNED NOT NULL,
    `date`              DATE NOT NULL,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE (class_id, week_no)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS attendance(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `semester_class_id` INT UNSIGNED NOT NULL,
    `student_id`        INT UNSIGNED NOT NULL,
    `status`            TINYINT UNSIGNED NOT NULL DEFAULT 1,
    `taken_by`          VARCHAR(64) NOT NULL,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE (semester_class_id, student_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS homework(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `semester_class_id` INT UNSIGNED NOT NULL,
    `homework`          JSON NOT NULL,
    `deadline`          DATETIME NOT NULL,
    `status`            TINYINT UNSIGNED NOT NULL DEFAULT 1,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE (semester_class_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS student_homework(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `homework_id`       INT UNSIGNED NOT NULL,
    `student_id`        INT UNSIGNED NOT NULL,
    `answers`           JSON NOT NULL,
    `grade`             FLOAT(2,2) NOT NULL,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE (homework_id, student_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS semester(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `year`              INT UNSIGNED NOT NULL,
    `semester`          INT UNSIGNED NOT NULL COMMENT '1 First semester / 2 Second semester',
    `start_date`        DATE NOT NULL,
    `end_date`          DATE NOT NULL,
    `num_of_weeks`      TINYINT UNSIGNED NOT NULL,
    `status`            TINYINT UNSIGNED NOT NULL DEFAULT 1,
    `creator`           VARCHAR(64) NOT NULL,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE (year, semester)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS assesment(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `semester_id`       INT UNSIGNED NOT NULL,
    `staff_id`          INT UNSIGNED NOT NULL,
    `result`            FLOAT(2,2) NOT NULL,
    `assesed_by`        VARCHAR(64) NOT NULL,
    `status`            TINYINT UNSIGNED NOT NULL DEFAULT 1,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    UNIQUE (semester_id, staff_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

CREATE TABLE IF NOT EXISTS date_config(
    `id`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `date`              JSON NOT NULL,
    `create_time`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`       DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;