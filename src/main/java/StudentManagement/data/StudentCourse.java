package StudentManagement.data;


import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCourse {

  private String courseId;
  private String studentId;
  private String courseName;
  private LocalDate startDate;
  private LocalDate expectedDate;
}
