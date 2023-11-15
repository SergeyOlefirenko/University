package university.project.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import university.project.command.CreateStudentCommand;
import university.project.datamodel.Course;
import university.project.datamodel.Student;
import university.project.info.LoggerInfo;
import university.project.repositories.CourseRepository;
import university.project.repositories.StudentRepository;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ApplicationContext applicationContext;

    @GetMapping
    public String index(Model model) {
        LoggerInfo loggerInfo = new LoggerInfo();
        loggerInfo.loggerMethod();

        List<Student> getStudents = studentRepository.findAll();
        List<Course> getCourses = courseRepository.findAll();
        model.addAttribute("students", getStudents);
        model.addAttribute("courses", getCourses);
        model.addAttribute("profileName", applicationContext.getBean("profileName", String.class));
        return "index";
    }

    @ModelAttribute("student")
    public Student createStudent() {
        return new Student();
    }

    @ModelAttribute("studentCommand")
    public CreateStudentCommand createStudentCommand() {
        return new CreateStudentCommand();
    }

    @ModelAttribute("course")
    public Course createCourse() {
        return new Course();
    }

    @Transactional
    @PostMapping("/add-student")
    public String create(@Validated CreateStudentCommand createStudentCommand,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error(bindingResult.toString());
            model.addAttribute("createStudentCommand", createStudentCommand);
            model.addAttribute("bindingResult", bindingResult );
            return "error";
        }
        Student student = createStudentCommand.toEntity();
        Course course = courseRepository.findById(createStudentCommand.getCourseId()).orElseThrow();
        student.addCourse(course);
        studentRepository.save(student);
        return "redirect:/";
    }
    @GetMapping("/find")
    public String findByPriority(@RequestParam Long id, Model model) {
        model.addAttribute("students", studentRepository
                .findById(id));
        model.addAttribute("courses", courseRepository
                .findById(id));
        return "index";
    }
    @PostMapping("/add-course")
    public String addCourse(@Validated Course course,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error(bindingResult.toString());
            return "error";
        }
        courseRepository.save(course);
        return "redirect:/";
    }
}
