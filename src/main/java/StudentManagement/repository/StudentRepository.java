package StudentManagement.repository;

import StudentManagement.data.Student;
import StudentManagement.data.studentCourse;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくRepositoryです。
 */
@Mapper
public interface StudentRepository {

  /**
   * 受講生の全権検索を行います。
   *
   * @return　受講生一覧（全件）
   */
  @Select("SELECT * FROM students WHERE is_deleted = false" )
  List<Student> search();

  /**
   * 受講生の検索を行います。
   *
   * @param studentId　受講生ID
   * @return 受講生
   */
  @Select("SELECT * FROM students WHERE student_id = #{studentId}")
  Student searchStudent(@Param("studentId") String studentId);

  /**
   * 受講生のコース情報の全件検索を行います。
   *
   * @return　受講生のコース情報（全件）
   */
  @Select("SELECT * FROM students_courses" )
  List<studentCourse> searchStudentCourseList();

  /**
   * 受講生IDに紐づく受講生コース情報を検索します。
   *
   * @param studentId　受講生ID
   * @return 受講生IDに紐づく受講生コース情報
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<studentCourse> searchStudentCourse(String studentId);

  /**
   * 受講生を新規登録します。
   * IDに関しては自動採番を行う。
   * @param student　受講生
   */
  @Insert("INSERT INTO students(fullname, hurigana, nickname, email, address, age, gender, remark, is_deleted )"
      + " VALUES (#{fullname}, #{hurigana}, #{nickname}, #{email}, #{address}, #{age}, #{gender}, #{remark},false)")
  @Options(useGeneratedKeys = true, keyProperty = "studentId")
  void registerStudent(Student student);

  /**
   *受講生コース情報を新規登録します。
   * IDに関しては自動採番を行う。
   *
   * @param studentCourse　受講生コース情報
   */
  @Insert("INSERT INTO students_courses(course_id, student_id, course_name, start_date, expected_date)"
      + " VALUES(#{courseId}, #{studentId}, #{courseName}, #{startDate}, #{expectedDate})")
  @Options(useGeneratedKeys = true, keyProperty = "courseId")
  void registerStudentCourse(studentCourse studentCourse);

  /**
   * 受講生を更新します。
   *
   * @param student　受講生
   */
  @Update("UPDATE students SET fullname = #{fullname}, hurigana = #{hurigana},nickname = #{nickname},"
      + "email = #{email},address = #{address},age = #{age},gender = #{gender},remark = #{remark},is_deleted = #{isDeleted} WHERE student_id = #{studentId}")
  void updateStudent(Student student);

  /**
   * 受講生コース情報のコース名を更新します。
   *
   * @param studentCourse　受講生コース情報
   */
  @Update("UPDATE students_courses SET course_name = #{courseName} WHERE student_id = #{studentId}" )
  void updateStudentCourse(studentCourse studentCourse);
}
