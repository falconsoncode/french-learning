CREATE DATABASE postgres;

CREATE SCHEMA public;

CREATE TABLE IF NOT EXISTS student (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(1500),
  last_name VARCHAR(1500),
  email VARCHAR(100),
  phone_number VARCHAR(20),
  UNIQUE(email)
);

CREATE TABLE IF NOT EXISTS teacher (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(1500),
  last_name VARCHAR(1500),
  email VARCHAR(100),
  phone_number VARCHAR(20),
  qualification VARCHAR(50),
  UNIQUE(email)
);

CREATE TABLE IF NOT EXISTS course (
  id SERIAL PRIMARY KEY,
  course_name VARCHAR(100),
  teacher_id INT REFERENCES teacher(id),
  щкшсу
  course_description TEXT
);

CREATE TABLE IF NOT EXISTS course_student (
    id SERIAL PRIMARY KEY,
    student_id INT REFERENCES student(id),
    course_id INT REFERENCES course(id)
);

CREATE TABLE IF NOT EXISTS groups (
  id SERIAL PRIMARY KEY,
  group_name VARCHAR(100),
--  course_id INT REFERENCES course(id),
);

CREATE TABLE IF NOT EXISTS groups_student (
  id SERIAL PRIMARY KEY,
  group_id INT REFERENCES group(id),
  student_id INT REFERENCES student(id)
);

CREATE TABLE IF NOT EXISTS lesson (
  id SERIAL PRIMARY KEY,
  schedule TIMESTAMP,
  start_time TIME,
  end_time TIME,
  course_id INT REFERENCES course(id),
  teacher_id INT REFERENCES teacher(id)
);

CREATE TABLE IF NOT EXISTS grades (
  id SERIAL PRIMARY KEY,
  student_id INT REFERENCES student(id),
  lesson_id INT REFERENCES lesson(id),
  grade INT
);

CREATE TABLE IF NOT EXISTS payment (
  id SERIAL PRIMARY KEY,
  student_id INT REFERENCES student(id),
  course_id INT REFERENCES course(id),
  amount DECIMAL(10, 2),
  payment_date TIMESTAMP
);