package StudentManagement.service;


import StudentManagement.data.Student;
import StudentManagement.data.StudentCourse;
import StudentManagement.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private StudentRepository repository;


  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }


  public List<Student> searchStudentList() {

    return repository.searchStudent();

  }

  public List<StudentCourse> searchStudentCourseList() {
    return repository.searchStudentCourses();

  }
}
