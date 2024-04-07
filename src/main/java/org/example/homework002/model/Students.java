package org.example.homework002.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Students {
    private int studentId;
    private String studentName;
    private String email;
    private String phoneNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Courses> courses;
}