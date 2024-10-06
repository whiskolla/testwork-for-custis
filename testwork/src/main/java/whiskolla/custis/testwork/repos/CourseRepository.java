package whiskolla.custis.testwork.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import whiskolla.custis.testwork.models.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {}
