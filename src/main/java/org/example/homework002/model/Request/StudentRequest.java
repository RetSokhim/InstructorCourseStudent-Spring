package org.example.homework002.model.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.example.homework002.model.Courses;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentRequest {
    private String studentName;
    private String email;
    private String phoneNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Integer> coursesId;
}
