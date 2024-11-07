package StudentManegement;

import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManegementApplication {

	@Autowired
	private StudentRepository repository;



	public StudentManegementApplication(){




	}

	public static void main(String[] args) {
		SpringApplication.run(StudentManegementApplication.class, args);
	}

	@GetMapping("/students")
  public String getStudentInfo() {
		Student student = repository.searchByName("Tuyoshi Shinjo");
		return student.getName() + " " + student.getAge() + "歳";
	}

	@PostMapping("/students")
	public void registerStudent(String name, int age){
		repository.registerStudent(name, age);
	}


	@PatchMapping("/students")
	public void updateStudentName(String  name, int age){
		repository.updateStudent(name,age);
	}

@DeleteMapping("/students")
public void deleteStudent(String name) {
	repository.deleteStudent(name);
}
	}






