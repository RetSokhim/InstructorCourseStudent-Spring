package org.example.homework002.repository;

import org.apache.ibatis.annotations.*;
import org.example.homework002.model.Request.StudentRequest;
import org.example.homework002.model.Students;

import java.util.List;

@Mapper
public interface StudentsRepo {

    @Select("""
    SELECT * FROM students_tb ORDER BY student_id LIMIT #{size} OFFSET #{offset};
    """)
    @Results(id = "studentsMapping", value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "email", column = "student_email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "courses", column = "student_id",
                    many = @Many(select = "org.example.homework002.repository.CoursesRepo.getCoursesByStudentId")
            ),

    })
    List<Students> getAllStudents(@Param("size") int size, @Param("offset") int offset);

    @Select("""
    SELECT count(*) FROM students_tb;
    """)
    int getTotalStudent();

    @Select("""
    SELECT * FROM students_tb WHERE student_id = #{id}
    """)
    @ResultMap("studentsMapping")
    Students getStudentById(int id);
    @Select("""
    DELETE FROM students_tb WHERE student_id = #{id} RETURNING *;
    """)
    @ResultMap("studentsMapping")
    Students deleteStudentById(int id);
    @Select("""
    INSERT INTO students_tb(student_name, student_email, phone_number)  VALUES (#{student.studentName},#{student.email},#{student.phoneNumber}) RETURNING *;
    """)
    @ResultMap("studentsMapping")
    Students insertStudent(@Param("student") StudentRequest studentRequest);

    @Select("""
    UPDATE students_tb SET student_name = #{student.studentName},student_email = #{student.email},phone_number = #{student.phoneNumber} WHERE student_id = #{id} RETURNING *;
    """)
    @ResultMap("studentsMapping")
    Students updateStudentById(int id,@Param("student") StudentRequest studentRequest);
    @Delete("DELETE FROM student_course WHERE student_id = #{id}")
    void deleteStudentCourses(int id);
    @Select("""
    INSERT INTO student_course(student_id, courses_id) VALUES (#{studentId},#{courseId})
    """)
    @ResultMap("studentsMapping")
    void insertIntoStudentCourse (int studentId,int courseId);
}
