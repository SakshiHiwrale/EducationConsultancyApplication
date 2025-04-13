package com.infosys.educationConsultancyApplication.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.infosys.educationConsultancyApplication.bean.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
    
    @Query("SELECT MAX(s.registrationNum) FROM Student s")
    String getMaxRegistrationNum();
    
    List<Student> findByStudentLevel(String studentLevel);
    
    default List<Student> getStudentsByStudentLevel(String studentLevel) {
        return findByStudentLevel(studentLevel);
    }
    
    @Query("SELECT s FROM Student s WHERE s.status = 'true'")
    List<Student> getCurrentStudents();

    @Query("SELECT s.status FROM Student s WHERE s.username = ?1")
    String getStudentStatusByUsername(String username);
    
    @Query("SELECT s FROM Student s WHERE s.username = ?1")
    Student getStudentByUsername(String username);
}
