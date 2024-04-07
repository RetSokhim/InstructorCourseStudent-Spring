CREATE TABLE IF NOT EXISTS students_tb(
    student_id SERIAL PRIMARY KEY,
    student_name VARCHAR(300) NOT NULL,
    student_email VARCHAR(300) NOT NULL,
    phone_number VARCHAR(300) NOT NULL
);
CREATE TABLE IF NOT EXISTS courses_tb(
    courses_id SERIAL PRIMARY KEY ,
    courses_name VARCHAR(300),
    description VARCHAR(300),
    instructor_id INT REFERENCES instructor_tb(instructor_id) ON DELETE CASCADE

);
CREATE TABLE IF NOT EXISTS instructor_tb(
    instructor_id SERIAL PRIMARY KEY,
    instructor_name VARCHAR(300),
    instructor_email VARCHAR(300)
);
CREATE TABLE IF NOT EXISTS student_course(
    id SERIAL PRIMARY KEY ,
    student_id INT REFERENCES students_tb(student_id) ON DELETE CASCADE,
    courses_id INT REFERENCES courses_tb(courses_id) ON DELETE CASCADE
);
