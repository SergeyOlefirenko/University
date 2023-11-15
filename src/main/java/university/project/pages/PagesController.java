package university.project.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {
    @GetMapping("/students")
    String about(Model model) {
        return "students";
    }
    @GetMapping("/courses")
    String contacts(Model model) {
        return "courses";
    }
}

