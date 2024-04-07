package org.example.homework002.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Instructors {
    private int instructorId;
    private String instructorName;
    private String email;
}
