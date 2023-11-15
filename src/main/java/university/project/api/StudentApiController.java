package university.project.api;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import university.project.command.CreateStudentCommand;
import university.project.datamodel.Student;
import university.project.repositories.CourseRepository;
import university.project.repositories.StudentRepository;
import university.project.services.StudentService;

import java.util.List;
import java.util.Optional;

@Data
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentApiController {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentService studentService;

//    @GetMapping
//    public List<Student> list() {
//        return studentRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public Optional<Student> show(@PathVariable Long id) {
//        return studentRepository.findById(id);
//    }
//
//    @PostMapping()
//    public Student save(@RequestBody CreateStudentCommand command) {
//        return studentRepository.save(command.toEntity());
//    }

@PostMapping
public ResponseEntity<Long> createStudent(@RequestBody Student student) {
    Student createdStudent = studentRepository.save(student);
    return new ResponseEntity<>(createdStudent.getId(), HttpStatus.CREATED);
}
    @PutMapping("/{student_id}")
    public ResponseEntity<Void> updateStudent(@PathVariable("student_id") Long studentId, @RequestBody Student updatedStudent) {
        if (studentRepository.existsById(studentId)) {
            updatedStudent.setId(studentId);
            studentRepository.save(updatedStudent);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//    @DeleteMapping("/{student_id}")
//    public ResponseEntity<Void> deleteStudent(@PathVariable("student_id") Long studentId) {
//        if (studentRepository.existsById(studentId)) {
//            studentRepository.deleteById(studentId);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
    @DeleteMapping("/{student_id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("student_id") Long studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//    @GetMapping("/{student_id}")
//    public ResponseEntity<Student> getStudent(@PathVariable("student_id") Long studentId) {
//        Optional<Student> student = studentRepository.findById(studentId);
//        if (student.isPresent()) {
//            return new ResponseEntity<>(student.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
    @GetMapping("/{student_id}")
    public ResponseEntity<Student> getStudent(@PathVariable("student_id") Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

//    @GetMapping(params = "subject")
//    public ResponseEntity<List<Student>> getStudentsBySubject(@RequestParam("subject") String subject) {
//        List<Student> students = studentRepository.findAllBySubjectsContaining(subject);
//        return new ResponseEntity<>(students, HttpStatus.OK);
//    }


@GetMapping("/subject/{subject}")
public ResponseEntity<List<Student>> findStudentsBySubject(@PathVariable String subject) {
    List<Student> students = studentService.findStudentsBySubject(subject);

    if (students.isEmpty()) {
        return ResponseEntity.notFound().build();
    } else {
        return ResponseEntity.ok(students);
    }
}
}


