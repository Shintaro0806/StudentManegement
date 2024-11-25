package StudentManagement.data;


import lombok.Getter;
import lombok.Setter;

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
}
