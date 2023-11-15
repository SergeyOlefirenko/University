package university.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import university.project.datamodel.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findById(Long id);
    void deleteById(Long id);
}

