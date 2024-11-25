package StudentManagement.repository;

import StudentManagement.data.Student;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * 受講生情報を扱うリポジトリ。
 *
 * 全権検索や単一条件での検索、コース情報の検索が行えるクラスです。
 */
@Mapper
public interface StudentRepository {


  /**
   * 全権検索します。
   *
   * @return　全権検索した受講生のリスト。
   */
  @Select("SELECT * FROM students" )
  List<Student> searchStudent();

}
