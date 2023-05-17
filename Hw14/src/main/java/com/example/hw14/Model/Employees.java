package com.example.hw14.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class Employees {

    @NotEmpty(message = "Id Cannot be null")
    @Size(min = 3, message = "ID length must be more than 2")
    private String id;

    @NotEmpty(message = "name Cannot be null")
    @Size(min = 5, message = "Name length must be more than 4")
    private String name;

    @NotNull(message = "Age cannot be null")
    @Positive(message = "age must be a number")
    @Min(value = 26, message = "Age must be more than 25")
    private int age;

    @NotEmpty(message = "position Cannot be null")
    //must be supervisor or coordinator only
    @Pattern(regexp = "supervisor|coordinator", message = "Position must be supervisor or coordinator only")
    private String position;

    @AssertFalse(message = "the employee is on leave")
    private boolean onLeave;

    @NotNull(message = "Employment year cannot be null")
    @PastOrPresent(message = "employment Year must be a valid year")
    private LocalDate employmentYear;

    @NotNull(message = "annualLeave Cannot be null")
    @Positive(message = "annualLeave has to be number")
    private int annualLeave;

}
