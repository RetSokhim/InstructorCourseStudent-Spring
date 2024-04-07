package org.example.homework002.model.apiRespond.list;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.example.homework002.model.Instructors;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InstructorListApiRespond {
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Instructors> payload;
    private HttpStatus status;
    private LocalDateTime time;
}
