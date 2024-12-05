package StudentManagement.repository;

import StudentManagement.data.Student;
import StudentManagement.data.StudentCourse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface StudentRepository {



  @Select("SELECT * FROM students" )
  List<Student> searchStudent();

  @Select("SELECT * FROM students_courses" )
  List<StudentCourse> searchStudentCourses();

}
