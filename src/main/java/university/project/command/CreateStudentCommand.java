package university.project.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import university.project.datamodel.Student;
import university.project.validation.CustomValidation;

import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class CreateStudentCommand {
    @NonNull
    @NotBlank(message = "Поле не может быть пустым")
    @Length(max = 50)
    private String firstName;
    @NonNull
    @NotBlank(message = "Поле не может быть пустым")
    @Length(max = 50)
    private String lastName;
    //    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Дата рождения должна быть в прошлом")
    @Column(columnDefinition = "date")
    private Date birthDate;
    @NonNull
    @NotBlank(message = "Поле не может быть пустым")
    @Pattern(regexp = "\\+380\\d{9}", message = "Неправильный формат номера телефона (Украина)")
    @CustomValidation
    private String phone;
    @NonNull
    @NotBlank(message = "Поле не может быть пустым")
    @Email(message = "Неправильный формат электронной почты")
    @CustomValidation
    private String email;
    @NonNull
    @NotBlank(message = "Поле не может быть пустым")
    @Length(min = 1, max = 50, message = "Длина не может быть менее 1 и более 50 символов")
    private String studentTicketNumber;
    @Max(50)
    private Long courseId;

    public Student toEntity() {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setPhone(phone);
        student.setEmail(email);
        student.setBirthDate(birthDate);
        student.setStudentTicketNumber(studentTicketNumber);
        return student;
    }
}
