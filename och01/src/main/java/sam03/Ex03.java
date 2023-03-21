package sam03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex03 {

	public static void main(String[] args) {
		// claasic  
		MessageBean cmb = new MessageBeanImpl("고니시", "열심히 해주세요");
		cmb.sayHello();
		// 전통적인 방식
		
		// DI 호출 객체   xml로 값을 설정한거
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean03.xml"); // ClassPath라 하면 일반적으로 리소스다
		MessageBean mb = (MessageBean) ac.getBean("mb3");
		mb.sayHello();
		
	}

}
