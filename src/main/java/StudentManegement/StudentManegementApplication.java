package StudentManegement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManegementApplication {

  @Autowired
  private StudentRepository repository;
  @Autowired
  private StudentCoursRepository repository2;


  public StudentManegementApplication() {

  }

  public static void main(String[] args) {
    SpringApplication.run(StudentManegementApplication.class, args);
  }

  @GetMapping("/studentList")
  public List<Student> getStudentList() {
    return repository.searchStudent();
  }

  @GetMapping("/studentcoursList")
  public List<StudentCours> getStudentCoursList() {
    return repository2.searchStudentCours();

}
}







