package sam06;

public class VehicleImpl implements Vehicle {
	
	
//	bean06.xml에서 생성자는 constructor-arg해서 value로, 게터세터는 property로 하면 될듯
//	생성자로 name있고, setter로도 name 있어
//	xml에서 생성자에 name 설정 했으면 setter에 설정 안해도 돼, 근데 수정하려면 setter에 설정한다
	
	private String name;
	private String rider;
	private int    speed;
	
	public VehicleImpl(String name) {
		this.name = name;
	}
	
	public void ride() {
		System.out.println(name + " 님은(는) " + rider + "를 이용 " + speed + "km 속도로 탄다");
		
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRider(String rider) {
		this.rider = rider;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
