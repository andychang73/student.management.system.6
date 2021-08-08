ALTER TABLE student_course RENAME COLUMN grade TO homework_average_grade;
ALTER TABLE student_course MODIFY COLUMN homework_average_grade FLOAT(6,2);
ALTER TABLE student_course MODIFY COLUMN attendance FLOAT(6,2);
ALTER TABLE student_course ADD COLUMN final_grade FLOAT(6,2) AFTER num_of_completed_pre_course;