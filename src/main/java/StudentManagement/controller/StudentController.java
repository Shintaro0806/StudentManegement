package StudentManagement.controller;


import StudentManagement.data.Student;
import StudentManagement.data.StudentCourse;
import StudentManagement.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/studentList")
  public List<Student> getStudentList() {
    return service.searchStudentList();
  }

  @GetMapping("/studentcourseList")
  public List<StudentCourse> getStudentCourseList() {
    return service.searchStudentCourseList();

  }
}

