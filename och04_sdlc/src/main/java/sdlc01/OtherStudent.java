package sdlc01;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class OtherStudent {
	private String name;
	private int age;
	
	// 생성자 생성이후
	@PostConstruct
	public void initMethod() {
		System.out.println("OtherStudent의 initMethod() 생성자 생성이후");
	}
	
	// 소멸자 소멸전
	@PreDestroy
	public void destroyMethod() {
		System.out.println("OtherStudent의 destroyMethod() 소멸자가 소멸되기 전..");
	}
	
	public OtherStudent(String name, int age) {
		this.name = name;
		this.age = age;
		System.out.println("OtherStudent 생성자...");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
