package sam07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
//		/sam07/하는 이유는 resource에 안넣는 사람 가끔 있어서 그래 resource에 넣는게 맞아 그냥 한번 해본거래
		ApplicationContext ac = new ClassPathXmlApplicationContext("/sam07/bean07.xml");
		MessageBean mb = (MessageBean) ac.getBean("mb7");
		mb.sayHello();
	}

}
