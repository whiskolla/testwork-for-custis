package whiskolla.custis.testwork.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import whiskolla.custis.testwork.models.Course;
import whiskolla.custis.testwork.services.CourseService;

import java.util.List;

@RestController
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/newcourse")
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        courseService.create(course);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> readCourses(){
        List<Course> courses = courseService.readAll();

        return ((courses != null) && !courses.isEmpty())
            ? new ResponseEntity<>(courses, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Course> readCourse(@PathVariable("id") int id){
        Course course = courseService.read(id);
        return (course != null)
            ? new ResponseEntity<>(course, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/courses/update{id}")
    public ResponseEntity<?> updateCourse(@PathVariable("id") int id, @RequestBody Course course){
        boolean ifUpdated = courseService.update(course, id);
        return (ifUpdated)
            ? new ResponseEntity<>(HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/courses/delete{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") int id){
        boolean ifDeleted = courseService.delete(id);
        return (ifDeleted)
            ? new ResponseEntity<>(HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
