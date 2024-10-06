package whiskolla.custis.testwork.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import whiskolla.custis.testwork.models.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {}
