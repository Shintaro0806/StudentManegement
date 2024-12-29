package StudentManagement.service;


import StudentManagement.data.Student;
import StudentManagement.data.StudentCourse;
import StudentManagement.domain.StudentDetail;
import StudentManagement.repository.StudentRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

  private StudentRepository repository;


  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }


  public List<Student> searchStudentList() {

    return repository.search();

  }


  public StudentDetail searchStudent(String id){
    Student student = repository.searchStudent(id);
    List<StudentCourse> studentCourses = repository.searchStudentCourses(student.getStudentId());

    if (student == null) {
      student = new Student();
      student.setDeleted(false);
    }

    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourses(studentCourses);
    return studentDetail;
  }


  @Transactional
  public void registerStudent(StudentDetail studentDetail) {
    repository.registerStudent(studentDetail.getStudent());

    for(StudentCourse studentCourse : studentDetail.getStudentCourses()){
      studentCourse.setStudentId(studentDetail.getStudent().getStudentId());
      studentCourse.setStartDate(LocalDate.now());
      studentCourse.setExpectedDate(LocalDate.now().plusYears(1));
     repository.registerStudentCourses(studentCourse);
    }
  }

  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    for(StudentCourse studentCourse : studentDetail.getStudentCourses()){
      studentCourse.setStudentId(studentDetail.getStudent().getStudentId());
      repository.updateStudentCourses(studentCourse);
}
  }

  public List<StudentCourse> searchStudentCourseList() {
    return repository.searchStudentCoursesList();
  }

  @Transactional
  public void logicalDeleteStudent(String studentId) {
    Student student = repository.searchStudent(studentId);
    if (student == null || student.isDeleted()) {
      throw new RuntimeException("Student not found or already deleted with id: " + studentId);
    }
    repository.logicalDeleteStudent(studentId);
  }


}

