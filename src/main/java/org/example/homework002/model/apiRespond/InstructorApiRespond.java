package org.example.homework002.model.apiRespond;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.example.homework002.model.Instructors;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InstructorApiRespond {
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Instructors payload;
    private HttpStatus status;
    private LocalDateTime time;
}
