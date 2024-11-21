package StudentManagement;


import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCourse {

  private String courseId;
  private String studentId;
  private String coursName;
  private LocalDate startDate;
  private LocalDate expectedDate;
}
