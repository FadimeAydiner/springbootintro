package com.tpe.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter//lombok kütüphanesiyle geldi
@Setter

@AllArgsConstructor //id ve date otomatik üretilecek bunun yerine RequiredCons
@NoArgsConstructor
//@RequiredArgsConstructor//bunu ekleyince constructora eklemek istediğiniz alanalrı final yapmak gerekiyor
@Entity
@Table(name="t_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)

    private Long id;

    @NotNull(message = "first name can not be null")
    @NotBlank(message = "first name can not be white space")
    @Size(min = 2,max = 25,message = "first name `${validateValue}` must be between {min} and {max}")
    @Column(nullable = false,length = 25)//yukardakiler controllerda yapıyor bu işlemleri. amam @Column Repository de yani db de yapıyor
    private String firstName;

    @Column(nullable = false,length = 25)
    private String lastName;

    @Column(nullable = false,length = 50,unique = true)
    @Email
    private String email;

    private String phoneNumber;
    private Integer grade;
    @Setter(AccessLevel.NONE)
    private LocalDateTime createDate=LocalDateTime.now();




}
