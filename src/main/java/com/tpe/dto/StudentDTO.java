package com.tpe.dto;

import com.tpe.domain.Student;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter//lombok kütüphanesiyle geldi
@Setter
@AllArgsConstructor //id ve date otomatik üretilecek bunun yerine RequiredCons
@NoArgsConstructor
public class StudentDTO {

    private Long id;

    @NotNull(message = "first name can not be null")
    @NotBlank(message = "first name can not be white space")
    @Size(min = 2,max = 25,message = "first name `${validateValue}` must be between {min} and {max}")
    private String name;

    private String lastName;

     @Email
    private String email;

    private String phoneNumber;
    private Integer grade;

    private LocalDateTime createDate=LocalDateTime.now();

    public StudentDTO(Student student){
        this.id=student.getId();
        this.name=student.getFirstName();
        this.lastName=student.getLastName();
        this.grade=student.getGrade();
        this.phoneNumber=student.getPhoneNumber();
        this.email=student.getEmail();
        this.createDate=student.getCreateDate();
    }
}
