package org.example.homework002.model.Request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CoursesRequest {
    private String courseName;
    private String description;
    private int instructorId;
}
