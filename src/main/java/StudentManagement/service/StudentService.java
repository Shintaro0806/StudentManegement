package StudentManagement.service;


import StudentManagement.data.Student;
import StudentManagement.data.StudentCourse;
import StudentManagement.repository.StudentCourseRepository;
import StudentManagement.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private StudentRepository repository;
  private StudentCourseRepository repository2;

  @Autowired
  public StudentService(StudentRepository repository, StudentCourseRepository repository2) {
    this.repository = repository;
    this.repository2 = repository2;
  }




  public List<Student> searchStudentList() {

    return repository.searchStudent().stream()
        .filter(student -> student.getAge() >= 30 && student.getAge() <= 39)
        .toList();
  }

  public List<StudentCourse> searchStudentCourseList() {
    return repository2.searchStudentCourses().stream()
        .filter(course -> course.getCoursName().contains("Javaコース")).toList();

  }
}
