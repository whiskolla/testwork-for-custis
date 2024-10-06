package whiskolla.custis.testwork.services;

import org.springframework.stereotype.Service;
import whiskolla.custis.testwork.models.Course;
import whiskolla.custis.testwork.models.Student;
import whiskolla.custis.testwork.repos.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student read(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> readAll(){
        return (List<Student>) studentRepository.findAll();
    }

    public boolean update(Student student, int id) {
        if (studentRepository.existsById(id)) {
            student.setId(id);
            studentRepository.save(student);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Student addCourse(Integer studentId, Course course) {
        Student student = read(studentId);
        if (student != null) {
            student.addCourse(course);
            return create(student);
        }
        return null;
    }
    public Student removeCourse(Integer studentId, Integer courseId) {
        Student student = read(studentId);
        if (student != null) {
            Course course = student.getCourses().stream().filter(c -> c.getId().equals(courseId)).findFirst().orElse(null);
            if (course != null) {
                student.removeCourse(course);
                return create(student);
            }
        }
        return null;
    }

}
