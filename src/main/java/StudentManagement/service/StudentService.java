package StudentManagement.service;


import StudentManagement.controller.converter.StudentConverter;
import StudentManagement.data.Student;
import StudentManagement.data.studentCourse;
import StudentManagement.domain.StudentDetail;
import StudentManagement.repository.StudentRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 受講生情報を取り扱うサービスです。
 * 受講生の検索や登録・更新処理を行います。
 */
@Service
public class StudentService {

  private StudentRepository repository;
  private StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    this.repository = repository;
    this.converter =converter;
  }

  /**
   * 受講生詳細の一覧検索です。
   * 全権検索を行うので、条件指定は行いません。
   *
   * @return　受講生一覧（全件）
   */
  public List<StudentDetail> searchStudentList() {
    List<Student> studentList = repository.search();
    List<studentCourse> studentCourseList = repository.searchStudentCourseList();
    return converter.convertStudentDetails(studentList,studentCourseList);
  }

  /**
   * 受講生詳細検索です。
   * IDに紐づく受講生情報を取得した後、その受講生に紐づく受講生コース情報を取得して設定します。
   *
   * @param id　受講生ID
   * @return 受講生
   */
  public StudentDetail searchStudent(String id){
    Student student = repository.searchStudent(id);
    List<studentCourse> studentCourses = repository.searchStudentCourse(student.getStudentId());
    return new StudentDetail(student, studentCourses);
  }

  /**
   * 受講生詳細の登録を行います。
   * 受講生と受講生コース情報を個別に登録し、受講生コース情報には受講生情報を紐づける値とコース開始日、コース終了日を設定します。
   *
   * @param studentDetail　受講生詳細
   * @return　登録情報を付与した受講生詳細
   */
  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();

    repository.registerStudent(student);
    studentDetail.getStudentCourseList().forEach(studentCourse -> {
      initStudentsCourse(studentCourse, student);
      repository.registerStudentCourse(studentCourse);
    });
    return studentDetail;
  }

  /**
   * 受講生コース情報を登録する際の正気情報を設定する。
   *
   * @param studentCourse　受講生コース情報
   * @param student　受講生
   */
  private void initStudentsCourse(studentCourse studentCourse, Student student) {
    LocalDate now = LocalDate.now();

    studentCourse.setStudentId(student.getStudentId());
    studentCourse.setStartDate(now);
    studentCourse.setExpectedDate(now.plusYears(1));
  }

  /**
   * 受講生詳細の更新を行います。
   * 受講生と受講生コース情報をそれぞれ更新します。
   *
   * @param studentDetail 受講生詳細
   */
  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    for(StudentManagement.data.studentCourse studentCourse : studentDetail.getStudentCourseList()){
      studentCourse.setStudentId(studentDetail.getStudent().getStudentId());
      repository.updateStudentCourse(studentCourse);
}
  }

}

