ALTER TABLE student_course DROP COLUMN homework_average_grade;
ALTER TABLE student_course RENAME COLUMN attendance TO final_attendance;
ALTER TABLE student_class ADD COLUMN homework_average_grade FLOAT(6,2) AFTER class_id;
ALTER TABLE student_class ADD COLUMN attendance FLOAT(6,2) AFTER homework_average_grade;