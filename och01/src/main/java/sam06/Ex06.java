package sam06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex06 {

	public static void main(String[] args) {

		// Classic
//		VehicleImpl vh3 =  new VehicleImpl("김태산");
//		vh3.setName("김태산3");
//		vh3.setRider("오토바이");
//		vh3.setSpeed(330);
//
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean06.xml");	// xml을 만들어주는 코드
		Vehicle vh = (Vehicle) ac.getBean("vh6");	//xml안에 Bean을 만들어주는 코드
		vh.ride();
	}

}
