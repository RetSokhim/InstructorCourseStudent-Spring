package org.example.homework002.repository;

import org.apache.ibatis.annotations.*;
import org.example.homework002.model.Courses;
import org.example.homework002.model.Request.CoursesRequest;

import java.util.List;

@Mapper
public interface CoursesRepo {

    @Select("""
    SELECT * FROM courses_tb ORDER BY courses_id LIMIT #{size} OFFSET #{offset};
    """)
    @Results(id = "coursesMapping", value = {
            @Result(property = "courseId", column = "courses_id"),
            @Result(property = "courseName", column = "courses_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "instructors", column = "instructor_id",
                    one = @One(select = "org.example.homework002.repository.InstructorsRepo.searchInstructorById")
            )
    })
    List<Courses> getAllCourse(@Param("size") int size, @Param("offset") int offset);

    @Select("""
    SELECT COUNT(*) FROM courses_tb;
    """)
    int getTotalCourses();

    @Select("""
    SELECT * FROM courses_tb WHERE courses_id = #{id};
    """)
    @ResultMap("coursesMapping")
    Courses getCourseById(int id);
    @Select("""
    INSERT INTO courses_tb (courses_name, description, instructor_id)  VALUES (#{courses.courseName},#{courses.description},#{courses.instructorId}) RETURNING *;
    """)
    @ResultMap("coursesMapping")
    Courses insertCourse (@Param("courses") CoursesRequest coursesRequest);
    @Select("""
    DELETE FROM courses_tb WHERE courses_id = #{id};
    """)
    @ResultMap("coursesMapping")
    Courses deleteCourseById (int id);
    @Select("""
    UPDATE courses_tb SET courses_name = #{courses.courseName},description = #{courses.description},instructor_id = #{courses.instructorId} WHERE courses_id = #{id} RETURNING *
    """)
    @ResultMap("coursesMapping")
    Courses updateCourseById (int id,@Param("courses") CoursesRequest coursesRequest);

    @Select("""
    SELECT c.* FROM courses_tb c
    JOIN student_course sc ON c.courses_id = sc.courses_id
    WHERE sc.student_id = #{id};
    """)
    @ResultMap("coursesMapping")
    List<Courses> getCoursesByStudentId(int id);

    @Select("""
    SELECT * FROM courses_tb WHERE courses_id = #{id};
    """)
    @ResultMap("coursesMapping")
    List<Courses> getListOfCourses(int id);
}

