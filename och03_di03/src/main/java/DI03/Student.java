package DI03;

public class Student {
	private String name;
	private int age;
	private String grageNum;
	private String classNum;
	
	public Student(String name, int age, String gradeNum, String classNum) {
		this.name = name;
		this.age = age;
		this.grageNum = gradeNum;
		this.classNum = classNum;
		System.out.println("Student Construct 시작...");
	}

//	생성자와 세터 있으면 생성자를 xml에서 먼저 타야해 생성자가 만들어져있으면 지혼자 안만듦
	
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

	public String getGrageNum() {
		return grageNum;
	}

	public void setGrageNum(String grageNum) {
		this.grageNum = grageNum;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	
}
