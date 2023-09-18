package peaksoft.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.validation.PhoneNumberValidation;

import java.time.LocalDate;

@Getter@Setter
public class StudentRequest {
    private String firstName;
    private String lastName;
    private int age ;
    private String email;
    private LocalDate graduationDate;
    @PhoneNumberValidation
    private String phoneNumber;
}
