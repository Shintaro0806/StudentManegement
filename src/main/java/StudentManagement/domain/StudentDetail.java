package StudentManagement.domain;

import StudentManagement.data.Student;
import StudentManagement.data.StudentCourse;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDetail {

  private Student student;
  private List<StudentCourse> studentCourses;

}
