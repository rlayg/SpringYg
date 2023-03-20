package sam06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex06 {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean06.xml");	// xml을 만들어주는 코드
		Vehicle vh = (Vehicle) ac.getBean("vh6");	//xml안에 Bean을 만들어주는 코드
		vh.ride();
	}

}
