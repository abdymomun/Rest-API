package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.StudentResponse;
import peaksoft.entity.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("select new peaksoft.dto.StudentResponse(s.id,s.firstName,s.lastName,s.age,s.email,s.createdDate,s.graduationDate,s.isBlocked,s.phoneNumber)from Student s ")
    List<StudentResponse> getAllStudents();
    Optional<StudentResponse> getStudentsById(Long id);

    @Query("select new peaksoft.dto.StudentResponse(s.id,s.firstName,s.lastName,s.age,s.email,s.createdDate,s.graduationDate,s.isBlocked)from Student s ")
    Page<StudentResponse> getAllStudents(Pageable pageable);
}
