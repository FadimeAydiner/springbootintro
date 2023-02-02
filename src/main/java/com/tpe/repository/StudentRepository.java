package com.tpe.repository;

import com.tpe.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//optional because of JpaRepository
public interface StudentRepository extends JpaRepository<Student,Long> {
//JpaRepository<Student,Long> burdaki Student entity Long ise Student class ın id sinin veri türü
// JpaRepository<Student,Long> SpringJpa ve bu Jpa in üstünde çalışır bununla maç boiledcoding ten kurtulmak bir sürü işi kendi yapıyor biz daha az kod yazmış oluyoruz
    //bunun içinde DB ile yapacağımız baplantıda kullnacağımız tüm metodlar var biz örneğin delete() metodu yazmayacağız zaten bunun için de vra direk kullanacağız
    //JpaRepository extends ettiğimiz için Spring Boot bu sınıfın Repository olduğunu zaten nlar. sınıfın başına @Repository yazmasak da olur



    boolean existsByEmail(String email);

}
