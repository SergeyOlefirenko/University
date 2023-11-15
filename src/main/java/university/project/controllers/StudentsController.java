package university.project.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import university.project.datamodel.Course;
import university.project.datamodel.Student;
import university.project.repositories.StudentRepository;

import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StudentsController {
    private final StudentRepository studentRepository;
    private final StudentRepository courseRepository;
    private final ApplicationContext applicationContext;
}
//
//        @PostMapping("/add-student")
//        public String addStudent(@ModelAttribute("student") Student student, Model model) {
//            Long courseId = Long.valueOf(Objects.requireNonNull(student.getCourse().getId()));
//            Student selectedCourse = courseRepository.findById(courseId).orElse(null);
//            model.addAttribute("pro fileName", applicationContext.getBean("profileName", String.class));
//
//            if (selectedCourse != null) {
//                student.setCourse(selectedCourse);
//                studentRepository.save(student);
//                return "redirect:/";
//            } else {
//                return "redirect:/error-page";
//            }
//        }
//    }
