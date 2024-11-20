package StudentManegement;


import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCours {

  private String courseId;
  private String studentId;
  private String coursName;
  private LocalDate startDate;
  private LocalDate expectedDate;
}
