package DI02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass02 {

	public static void main(String[] args) {
		String conFigLocation = "classpath:applicationCTX02.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(conFigLocation);
		MyInfo myInfo = ctx.getBean("myInfo", MyInfo.class);
		myInfo.getInfo();
		ctx.close();
		
		// 현장 과제
		// bean bmiCalcaulator   --> BMICalculator
		//   1)  lowWeight  --> 18.5
		//   2)  normal     --> 23
		//   3)  overWeight --> 25
		//   4)  obesity    --> 30
		//  
		// bean myInfo      --> MyInfo
		//   1)  name       --> 김춘추
		//   2)  height     --> 170
		//   3)  weight     --> 72
		//   4)  		<property name="hobbys">
		//					<list>
		//					  <value>말타기</value>
		//					  <value>활쏘기</value>
		//				  </list>
		//			    </property>
		//   5) bmiCalculator -->  bean bmiCalcaulator
		
		
		
	}

}
