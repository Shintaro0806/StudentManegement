package StudentManagement.repository;

import StudentManagement.data.Student;
import StudentManagement.data.StudentCourse;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students" )
  List<Student> searchStudent();

  @Select("SELECT * FROM students_courses" )
  List<StudentCourse> searchStudentCourses();


  @Insert("INSERT INTO students(fullname, hurigana, nickname, email, address, age, gender, remark, is_daleted )"
      + " VALUES (#{fullname}, #{hurigana}, #{nickname}, #{email}, #{address}, #{age}, #{gender}, #{remark},false)")
  @Options(useGeneratedKeys = true, keyProperty = "studentId")
  void registerStudent(Student student);

}
