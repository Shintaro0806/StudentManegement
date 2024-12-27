package StudentManagement.data;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Setter
public class Student {

  private String studentId;
  private String fullname;
  private String hurigana;
  private String nickname;
  private String email;
  private String address;
  private int age;
  private String gender;
  private String remark;
 課題提出用
  private boolean isDeleted;

  private boolean isDaleted;
 master
}

