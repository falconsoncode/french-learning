package com.freanchlearning.models.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String qualification;
}
