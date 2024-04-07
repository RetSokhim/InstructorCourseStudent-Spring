package org.example.homework002.model.apiRespond.list;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.example.homework002.model.Courses;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseListApiRespond {
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Courses> payload;
    private HttpStatus status;
    private LocalDateTime time;
}
