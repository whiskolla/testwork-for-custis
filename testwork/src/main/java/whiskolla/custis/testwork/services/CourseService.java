package whiskolla.custis.testwork.services;

import org.springframework.stereotype.Service;
import whiskolla.custis.testwork.models.Course;
import whiskolla.custis.testwork.repos.CourseRepository;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void create(Course course) {
        courseRepository.save(course);
    }

    public Course read(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    public List<Course> readAll(){
        return (List<Course>) courseRepository.findAll();
    }

    public boolean update(Course course, int id) {
        if (courseRepository.existsById(id)) {
            course.setId(id);
            courseRepository.save(course);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
