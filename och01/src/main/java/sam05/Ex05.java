package sam05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex05 {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean05.xml");
		MessageBean mb = (MessageBean) ac.getBean("mb5");
		mb.sayHello();
		
	}

}
