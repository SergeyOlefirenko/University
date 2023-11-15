package university.project.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import university.project.repositories.StudentRepository;

@Component
//@RequiredArgsConstructor
//@NoArgsConstructor
//@Service
@Slf4j
public class CustomValidator implements ConstraintValidator<CustomValidation, String> {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    public CustomValidator(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public CustomValidator(){
    }

    @Override
    public void initialize(CustomValidation constraintAnnotation) {
        String message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !studentRepository.existsByEmailOrPhone(value, value);
    }
}
