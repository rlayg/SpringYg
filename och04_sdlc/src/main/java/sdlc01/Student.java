package sdlc01;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Student implements InitializingBean, DisposableBean {

	private String name;
	private int age;
	
		//	소멸자 소멸전 실행
	public void destroy() throws Exception {
		System.out.println("Student의 destroy() --> 소멸자가 소멸되기 전 실행..");
		
	}

		// 생성자 생성이후 실행
	public void afterPropertiesSet() throws Exception {
		System.out.println("Student afterPropertiesSet -->  생성자 생성이후 실행");
	}

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
		System.out.println("Student 생성자...");
	}
}
