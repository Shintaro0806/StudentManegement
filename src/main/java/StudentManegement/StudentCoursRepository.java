package StudentManegement;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentCoursRepository {

  @Select("SELECT * FROM students_courses" )
  List<StudentCours> search();

}
