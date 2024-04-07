package org.example.homework002.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Courses {
    private int courseId;
    private String courseName;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Instructors instructors;

}
