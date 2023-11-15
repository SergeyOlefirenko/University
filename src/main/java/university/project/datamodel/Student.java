package university.project.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Data
@Entity
@EqualsAndHashCode(exclude = "courses")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NonNull
//    @NotBlank(message = "Поле 'firstName' не может быть пустым")
    @Length(max = 50, message = "Длина не может быть более 50 символов")
    private String firstName;
//    @NonNull
//    @NotBlank(message = "Поле 'lastName' не может быть пустым")
    @Length(max = 50, message = "Длина не может быть более 50 символов")
    private String lastName;
//    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @Past(message = "Дата рождения должна быть в прошлом")
    @Column(columnDefinition = "date")
    private Date birthDate;
//    @NonNull
//    @NotBlank(message = "Поле 'phone' не может быть пустым")
    @Pattern(regexp = "\\+380\\d{9}", message = "Неправильный формат номера телефона (Украина)")
    private String phone;
//    @NonNull
//    @NotBlank(message = "Поле 'email' не может быть пустым")
    @Email(message = "Неправильный формат электронной почты")
    private String email;
//    @NonNull
//    @NotBlank(message = "Поле 'studentTicketNumber' не может быть пустым")
    @Length(min = 1, max = 50, message = "Длина не может быть менее 1 и более 50 символов")
    private String studentTicketNumber;
    @ElementCollection
    private List<String> subjects;
    @JsonIgnore
    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();


    public Student() {
    }
    public void addCourse(Course course) {
        course.getStudents().add(this);
        courses.add(course);
    }
}
