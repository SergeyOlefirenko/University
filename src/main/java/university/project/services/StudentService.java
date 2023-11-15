package university.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import university.project.datamodel.Student;
import university.project.repositories.CourseRepository;
import university.project.repositories.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student != null) {
            courseRepository.deleteAll(student.getCourses());
            studentRepository.delete(student);
        }
    }
    public List<Student> findStudentsBySubject(String subject) {
        return studentRepository.findBySubjectsContains(subject);
    }
}


