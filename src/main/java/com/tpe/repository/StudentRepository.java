package com.tpe.repository;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository//optional because of JpaRepository
public interface StudentRepository extends JpaRepository<Student,Long> {
//JpaRepository<Student,Long> burdaki Student entity Long ise Student class ın id sinin veri türü
// JpaRepository<Student,Long> SpringJpa ve bu Jpa in üstünde çalışır bununla maç boiledcoding ten kurtulmak bir sürü işi kendi yapıyor biz daha az kod yazmış oluyoruz
    //bunun içinde DB ile yapacağımız baplantıda kullnacağımız tüm metodlar var biz örneğin delete() metodu yazmayacağız zaten bunun için de vra direk kullanacağız
    //JpaRepository extends ettiğimiz için Spring Boot bu sınıfın Repository olduğunu zaten nlar. sınıfın başına @Repository yazmasak da olur



    boolean existsByEmail(String email);

    List<Student> findStudentByLastName(String lastName);

    //JPQL
    @Query("SELECT s FROM Student s WHERE s.grade=:pGrade" )
    List<Student> findStudentByGrade(@Param("pGrade")Integer grade);

    //SQL version of above code
    @Query(value = "SELECT * FROM t_student s WHERE s.grade=:pGrade",nativeQuery = true)//nativeQuery = true means this is a sql query
    List<Student> findStudentByGradeWithSQL(@Param("pGrade")Integer grade);

    @Query("SELECT new com.tpe.dto.StudentDTO(s) From Student s WHERE s.id=:id")
    Optional<StudentDTO> findStudentDTOById(@Param("id") Long id);
}
