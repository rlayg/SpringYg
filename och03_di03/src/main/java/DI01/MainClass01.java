package DI01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass01 {

	public static void main(String[] args) {
		String conFigLocation = "classpath:applicationCTX01.xml"; // classpath는 리소스를 가르킨다라고 생각해도 된다
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(conFigLocation);
//		MyCalculator myCalculator = (MyCalculator) ctx.getBean("myCalculator"); 방법 1 casting 하기
		MyCalculator myCalculator = ctx.getBean("myCalculator", MyCalculator.class); // 방법2 MyCalculator.class 뒤에 넣기, 캐스팅 안하고
		
		myCalculator.add();
		myCalculator.sub();
		myCalculator.mul();
		myCalculator.div();
		
		// 현장과제 :  applicationCTX01.xml Setting 
		// bean id="calculator"  --> Calculator
		// bean id="myCalulator" -->  MyCalculator
		//     1) calculator  --> calculator 참조
		//     2) firstNum    --> 20
		//     3) secondNum   -->  2
		
		System.out.println("-------------Classic-------");
// 		Classic
//		MyCalculator myCalculator03 = new MyCalculator();
//		Calculator   calculator03   = new Calculator(); 
//		myCalculator03.setCalculator(calculator03);
//		myCalculator03.setFirstNum(50);
//		myCalculator03.setSecondNum(5);
//		myCalculator03.add();
//		myCalculator03.sub();
//		myCalculator03.mul();
//		myCalculator03.div();

		
	}

}
