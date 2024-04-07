package org.example.homework002.repository;

import org.apache.ibatis.annotations.*;
import org.example.homework002.model.Instructors;
import org.example.homework002.model.Request.InstructorsRequest;
import java.util.List;

@Mapper
public interface InstructorsRepo {
    @Select("""
    INSERT INTO instructor_tb(instructor_name, instructor_email)  VALUES(#{instructor.instructorName},#{instructor.email}) RETURNING *;
    """)
    @Results(id = "instructorMapping",value = {
            @Result(property = "instructorId",column = "instructor_id"),
            @Result(property = "instructorName",column = "instructor_name"),
            @Result(property = "email",column = "instructor_email")
    })
    Instructors insertInstructor(@Param("instructor") InstructorsRequest instructorsRequest);
    @Select("""
    SELECT * FROM instructor_tb ORDER BY instructor_id LIMIT #{size} OFFSET #{offset};
    """)
    @ResultMap("instructorMapping")
    List<Instructors> findAllInstructor(@Param("size") int size, @Param("offset") int offset);

    @Select("""
    SELECT COUNT(*) FROM instructor_tb;
    """)
    int getTotalInstructors();

    @Select("""
    SELECT * FROM instructor_tb WHERE instructor_id = #{id}
    """)
    @ResultMap("instructorMapping")
    Instructors searchInstructorById(int id);

    @Select("""
    DELETE FROM instructor_tb WHERE instructor_id = #{id} RETURNING *;
    """)
    @ResultMap("instructorMapping")
    Instructors deleteInstructorById(int id);
    @Select("""
    UPDATE instructor_tb SET instructor_email = #{instructors.email},instructor_name = #{instructors.instructorName} WHERE instructor_id = #{id} RETURNING *;
    """)
    @ResultMap("instructorMapping")
    Instructors updateInstructorById(int id,@Param("instructors") InstructorsRequest instructorsRequest);
}
