package org.example.homework002.model.apiRespond.list;

import lombok.*;
import org.example.homework002.model.Students;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentsListApiRespond {
    private String message;
    private List<Students> students;
    private HttpStatus status;
    private LocalDateTime time;
}
