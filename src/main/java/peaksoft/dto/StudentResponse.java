package peaksoft.dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private int age ;
    private String email;
    private LocalDate createdDate;
    private LocalDate graduationDate;
    private boolean isBlocked;
    private String phoneNumber;

    public StudentResponse(Long id, String firstName, String lastName, int age, String email, LocalDate createdDate, LocalDate graduationDate, boolean isBlocked,String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.createdDate = createdDate;
        this.graduationDate = graduationDate;
        this.isBlocked = isBlocked;
        this.phoneNumber = phoneNumber;
    }
}
