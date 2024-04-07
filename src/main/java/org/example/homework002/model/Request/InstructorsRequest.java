package org.example.homework002.model.Request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InstructorsRequest {
    private String instructorName;
    private String email;
}
