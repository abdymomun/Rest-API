package peaksoft.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.PaginationResponse;
import peaksoft.dto.SimpleResnonse;
import peaksoft.dto.StudentRequest;
import peaksoft.dto.StudentResponse;
import peaksoft.entity.Student;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    @Override
    public SimpleResnonse saveStudent(StudentRequest studentRequest) {
        Student student1 = new Student();
        student1.setFirstName(studentRequest.getFirstName());
        student1.setLastName(studentRequest.getLastName());
        student1.setEmail(studentRequest.getEmail());
        student1.setAge(studentRequest.getAge());
        student1.setCreatedDate(LocalDate.now());
        student1.setGraduationDate(studentRequest.getGraduationDate());
        student1.setBlocked(false);
        student1.setPhoneNumber(studentRequest.getPhoneNumber());
        studentRepository.save(student1);
        log.info("Student successfully saved !");
        return new SimpleResnonse(HttpStatus.OK,String.format("Student with id:"+student1.getId()+" succesfuly saved !"));

    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        return studentRepository.getStudentsById(id).orElseThrow(() -> {
            String message = "Student with id: " + id  +  " not found !";
            log.error(message);
                    return new NotFoundException(message);
        });

    }

    @Override
    public SimpleResnonse updateStudent(Long id, StudentRequest studentRequest) {
        Student student1 = studentRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Student with id: " + id + "not found !"));

        student1.setFirstName(studentRequest.getFirstName());
        student1.setLastName(studentRequest.getLastName());
        student1.setAge(studentRequest.getAge());
        student1.setEmail(studentRequest.getEmail());
        student1.setGraduationDate(studentRequest.getGraduationDate());
        studentRepository.save(student1);
        return new SimpleResnonse(HttpStatus.OK,String.format("Student with id: %s succesfuly updated !" + student1.getId()));
    }

    @Override
    public SimpleResnonse deleteStudent(Long id) {
        if (!studentRepository.existsById(id)){
            throw  new NotFoundException("Student with id: "+ id + "not found !");
        }
        studentRepository.deleteById(id);
     return new SimpleResnonse(HttpStatus.OK,"student with id :" + id +"succesfuly deleted !");

    }

    @Override
    public PaginationResponse getAllByPagination(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        Page<StudentResponse> allStudents = studentRepository.getAllStudents(pageable);
        return PaginationResponse.builder()
                .students(allStudents.getContent())
                .currentPage(allStudents.getNumber())
                .pageSize(allStudents.getTotalPages())
                .build();
    }

    @Override
    public Student getIsBlooked(Boolean isBlocked) {
       return studentRepository.findAll()
                .stream()
                .findFirst()
                .orElse(null);
        }


    }


