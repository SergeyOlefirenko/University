package university.project.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import university.project.repositories.CourseRepository;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CoursesController {
    private final CourseRepository courseRepository;
    private final ApplicationContext applicationContext;
}

//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.validation.BindingResult;
//
//import jakarta.validation.Valid;
//import university.project.datamodel.Course;
//import university.project.repositories.CourseRepository;
//
//@Controller
//@RequestMapping("/courses")
//public class CoursesController {
//    private final CourseRepository courseRepository;
//
//    public CoursesController(CourseRepository courseRepository) {
//        this.courseRepository = courseRepository;
//    }
//
//    @GetMapping
//    public String listCourses(Model model) {
//        model.addAttribute("courses", courseRepository.findAll());
//        return "course/list";
//    }
//
//    @GetMapping("/{id}")
//    public String viewCourse(@PathVariable Long id, Model model) {
//        Course course = (Course) courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid course id: " + id));
//        model.addAttribute("course", course);
//        return "course/view";
//    }
//
//    @GetMapping("/add")
//    public String showAddCourseForm(Course course) {
//        return "course/add";
//    }
//
//    @PostMapping("/add")
//    public String addCourse(@Valid Course course, BindingResult result) {
//        if (result.hasErrors()) {
//            return "course/add";
//        }
//
//        courseRepository.save(course);
//        return "redirect:/courses";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String showEditCourseForm(@PathVariable Long id, Model model) {
//        Course course = (Course) courseRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid course id: " + id));
//        model.addAttribute("course", course);
//        return "course/edit";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String editCourse(@PathVariable Long id, @Valid Course course, BindingResult result) {
//        if (result.hasErrors()) {
//            return "course/edit";
//        }
//
//        course.setId(id);
//        courseRepository.save(course);
//        return "redirect:/courses";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteCourse(@PathVariable Long id) {
//        courseRepository.deleteById(id);
//        return "redirect:/courses";
//    }
//}
