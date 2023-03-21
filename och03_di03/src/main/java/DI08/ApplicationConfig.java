package DI08;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	@Bean	
	public Student student1() {
		ArrayList<String> hobbys = new ArrayList<String>();
		hobbys.add("장기");
		hobbys.add("바둑");
		
		Student student = new Student("이세돌", 22, hobbys);
		student.setHeight(179);
		student.setWeight(70);
		
		return student;
	}
}
