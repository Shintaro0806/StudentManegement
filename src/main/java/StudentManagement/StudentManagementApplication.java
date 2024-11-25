package StudentManagement;

import StudentManagement.data.Student;
import StudentManagement.data.StudentCourse;
import StudentManagement.repository.StudentCourseRepository;
import StudentManagement.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

  public StudentManagementApplication() {

  }

  public static void main(String[] args) {
    SpringApplication.run(StudentManagementApplication.class, args);
  }
}









