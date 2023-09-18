package peaksoft.controller;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.PaginationResponse;
import peaksoft.dto.SimpleResnonse;
import peaksoft.dto.StudentRequest;
import peaksoft.dto.StudentResponse;
import peaksoft.entity.Student;
import peaksoft.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentApi {
private final StudentService studentService;
   @PermitAll
    @GetMapping
    public List<StudentResponse> getAllStudents(){
    return studentService.getAllStudents();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResnonse saveStudent(@RequestBody @Valid StudentRequest student){
       return studentService.saveStudent(student);
    }
    @GetMapping("/{studentId}")
    @PermitAll
    public StudentResponse getById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }
    @GetMapping("/is")
    public Student sortedIsBooked(@RequestParam boolean is){
        return studentService.getIsBlooked(is);
    }
    @PutMapping("/{studentId}")
    public SimpleResnonse updateStudent(@PathVariable Long studentId,@RequestBody StudentRequest studentRequest){
        return studentService.updateStudent(studentId,studentRequest);
    }
    @DeleteMapping("/{studentId}")
    public SimpleResnonse deleteStudent(@PathVariable Long studentId){
        return  studentService.deleteStudent(studentId);

    }
    @GetMapping("/pagination")
    public PaginationResponse pagination(@RequestParam int currentPage,
                                         @RequestParam int pageSize){
       return studentService.getAllByPagination(currentPage,pageSize);

 }


}
