package StudentManagement.domain;

import StudentManagement.data.Student;
import StudentManagement.data.studentCourse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetail {

  private Student student;
  private List<studentCourse> studentCourseList;

}
