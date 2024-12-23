package StudentManagement.repository;

import StudentManagement.data.Student;
import StudentManagement.data.StudentCourse;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students" )
  List<Student> search();

  @Select("SELECT * FROM students WHERE student_id = #{studentId}" )
  Student searchStudent(String studentId);

  @Select("SELECT * FROM students_courses" )
  List<StudentCourse> searchStudentCoursesList();

  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId} ")
  List<StudentCourse> searchStudentCourses(String studentId);


  @Insert("INSERT INTO students(fullname, hurigana, nickname, email, address, age, gender, remark, is_deleted )"
      + " VALUES (#{fullname}, #{hurigana}, #{nickname}, #{email}, #{address}, #{age}, #{gender}, #{remark},false)")
  @Options(useGeneratedKeys = true, keyProperty = "studentId")
  void registerStudent(Student student);

  @Insert("INSERT INTO students_courses(course_id, student_id, course_name, start_date, expected_date)"
      + " VALUES(#{courseId}, #{studentId}, #{courseName}, #{startDate}, #{expectedDate})")
  @Options(useGeneratedKeys = true, keyProperty = "courseId")
  void registerStudentCourses(StudentCourse studentCourse);

  @Update("UPDATE students SET(fullname = #{fullname}, hurigana = #{hurigana},nickname = #{nickname},"
      + "email = #{email},address = #{address},age = #{age},gender = #{gender},remark = #{remark},is_deleted = #{isDeleted}) WHERE student_id = #{studentId}")

  void updateStudent(Student student);

  @Update("UPDATE students_courses SET(course_name = #{courseName}) WHERE student_id = #{studentId}" )

  void updateStudentCourses(StudentCourse studentCourse);


}
