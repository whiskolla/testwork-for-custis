package whiskolla.custis.testwork.controllers;

import org.slf4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import whiskolla.custis.testwork.models.Course;
import whiskolla.custis.testwork.models.Student;
import whiskolla.custis.testwork.services.CourseService;
import whiskolla.custis.testwork.services.StudentService;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;
    private static Lock lock = new ReentrantLock();
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @PostMapping("/newstudent")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        studentService.create(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> readStudents(){
        List<Student> students = studentService.readAll();

        return ((students != null) && !students.isEmpty())
            ? new ResponseEntity<>(students, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/student{id}")
    public ResponseEntity<Student> readStudent(@PathVariable("id") int id){
        Student student = studentService.read(id);

        return(student != null)
                ?  new ResponseEntity<>(student, HttpStatus.OK)
                :  new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/students/delete{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id){
        boolean ifDeleted = studentService.delete(id);

        return(ifDeleted)
                ?  new ResponseEntity<>(HttpStatus.OK)
                :  new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("/students/{studentId}/addcourse{courseId}")
    public ResponseEntity<?> addCourse(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        lock.lock();
        try {
            logger.info("locked st" + studentId);
            Course course = courseService.read(courseId);
            if (course.getIsAvailable()) {
                course.setPlaces(course.getPlaces() - 1);
                if (course.getPlaces().equals(0))
                    course.setIsAvailable(false);
                return new ResponseEntity<>(studentService.addCourse(studentId, course), HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        } finally {
            lock.unlock();
            logger.info("unlocked st" + studentId);
        }
    }

    @DeleteMapping("/students/{studentId}/removecourse{courseId}")
    public ResponseEntity<?> removeCourse(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        if(courseService.read(courseId) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Course course = courseService.read(courseId);
        if (course.getPlaces().equals(0))
            course.setIsAvailable(true);
        course.setPlaces(course.getPlaces() + 1);
        return new ResponseEntity<>(studentService.removeCourse(studentId, courseId), HttpStatus.OK);
    }
}
