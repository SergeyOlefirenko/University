package university.project.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import java.util.HashSet;
import java.util.Set;

import lombok.*;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "students")
@ToString(exclude = "students")

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @NotBlank(message = "Поле 'Name' не может быть пустым")
    @Length(max = 50, message = "Длина не может быть более 50 символов")
    private String name;
    @NonNull
    @NotBlank(message = "Поле 'Code' не может быть пустым")
    @Length(min = 1, max = 50, message = "Длина не может быть менее 1 и более 50 символов")
    private String code;
    @JsonIgnore
    @ManyToMany
    private Set<Student> students = new HashSet<>();
}


