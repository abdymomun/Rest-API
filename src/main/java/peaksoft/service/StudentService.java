package peaksoft.service;

import peaksoft.dto.PaginationResponse;
import peaksoft.dto.SimpleResnonse;
import peaksoft.dto.StudentRequest;
import peaksoft.dto.StudentResponse;
import peaksoft.entity.Student;

import java.util.List;

public interface StudentService {
    SimpleResnonse saveStudent(StudentRequest student);
    List<StudentResponse> getAllStudents();
    StudentResponse getStudentById(Long id);
    SimpleResnonse updateStudent(Long id,StudentRequest student);
    SimpleResnonse deleteStudent(Long id);

    Student getIsBlooked(Boolean isBlocked);

    PaginationResponse getAllByPagination(int currentPage, int pageSize);
}
