package org.example.homework002.model.apiRespond;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.example.homework002.model.Courses;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseApiRespond {
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Courses payload;
    private HttpStatus status;
    private LocalDateTime time;
}
