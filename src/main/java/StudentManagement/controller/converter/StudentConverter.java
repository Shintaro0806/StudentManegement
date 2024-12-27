package StudentManagement.controller.converter;

import StudentManagement.data.Student;
import StudentManagement.data.StudentCourse;
import StudentManagement.domain.StudentDetail;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {

  public List<StudentDetail> convertStudentDetails(List<Student> students,
      List<StudentCourse> studentCourses) {
    List<StudentDetail> studentDetails = new ArrayList<>();
 課題提出用
    students.forEach(student -> {
    for(Student student : students){
 master
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);

      List<StudentCourse> convertStudentCourse = studentCourses.stream()
          .filter(studentCourse -> student.getStudentId().equals(studentCourse.getStudentId()))
          .collect(Collectors.toList());
 課題提出用

      studentDetail.setStudentCourses(convertStudentCourse);
      studentDetails.add(studentDetail);
    });

      studentDetail.setStudentCourses(convertStudentCourse);
      studentDetails.add(studentDetail);
    }
 master
    return studentDetails;
  }

}
