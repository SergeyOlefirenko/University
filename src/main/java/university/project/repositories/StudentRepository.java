package university.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.project.datamodel.Student;

import java.util.List;
import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findById(Long id);
    void deleteById(Long id);

    boolean existsByEmailOrPhone(String email, String phone);
    List<Student> findBySubjectsContains(String subject);

}
