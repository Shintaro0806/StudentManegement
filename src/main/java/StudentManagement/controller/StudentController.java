package StudentManagement.controller;


import StudentManagement.controller.handler.ExceptionHandler;
import StudentManagement.domain.StudentDetail;
import StudentManagement.exception.TestException;
import StudentManagement.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 受講生の検索や登録、更新などを行うREST APIとして受付るControllerです。
 */

@Validated
@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * 受講生詳細一覧検索です。
   * 全権検索を行うので、条件指定は行わないものになります。
   * @return　受講生一覧（全件）
   */
  @Operation(summary = "一覧検索", description = "受講生の一覧を検索します。")
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    return service.searchStudentList();
  }

  /**
   * 受講生詳細検索です。
   * IDに紐づく任意の受講生の情報を取得します。
   *
   * @param id　受講生ID
   * @return 受講生
   */
  @Operation(
      summary = "受講生詳細検索",
      description = "IDに紐づく受講生情報を取得します。",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "正常に受講生情報を取得しました",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = StudentDetail.class)
              )
          ),
          @ApiResponse(responseCode = "404", description = "指定されたIDの受講生が見つかりませんでした")
      }
  )
  @GetMapping("/student/{id}")
  public StudentDetail getStudent(@PathVariable @Size(min = 1, max = 3) String id) {
    return service.searchStudent(id);
  }

  /**
   * 受講生詳細の登録を行います。
   *
   * @param studentDetail　受講生詳細
   * @return　実行結果
   */
  @Operation(
      summary = "受講生登録",
      description = "受講生を登録します。",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "受講生の登録が成功しました",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = StudentDetail.class)
              )
          ),
          @ApiResponse(responseCode = "400", description = "リクエストが無効です（バリデーションエラーなど）"),
          @ApiResponse(responseCode = "500", description = "サーバーエラーが発生しました")
      }
  )
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody @Valid StudentDetail studentDetail) {
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 受講生詳細の更新を行います。
   * キャンセルフラグの更新もここで行います。（論理削除）
   *
   * @param studentDetail　受講生詳細
   * @return　実行結果
   */
  @Operation(
      summary = "受講生更新",
      description = "受講生詳細の更新を行います。",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "受講生詳細の更新が成功しました"
          ),
          @ApiResponse(responseCode = "400", description = "リクエストが無効です（バリデーションエラーなど）"),
          @ApiResponse(responseCode = "500", description = "サーバーエラーが発生しました")
      }
  )  @PutMapping ("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました。");
  }

  /**
   * 例外処理のテスト用のメソッドです。
   * @param id　受講生ID
   * @throws TestException エラーメッセージ
   */
  @Operation(summary = "例外処理のテスト", description = "テスト用に例外を発生させます"
  )
  @GetMapping("/testException/{id}")
  public void throwTestException(@PathVariable int id) throws TestException {
    throw new TestException("テスト用のメッセージです。");
  }
}

