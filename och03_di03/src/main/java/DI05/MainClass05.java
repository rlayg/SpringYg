package DI05;

import java.util.Map;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass05 {

	public static void main(String[] args) {
		String configLocation = "classpath:applicationCTX05.xml";
		AbstractApplicationContext ac = new GenericXmlApplicationContext(configLocation);
		CollectionBean collectionBean = ac.getBean("collectionBean", CollectionBean.class);
		Map<String, String> addressList = collectionBean.getAddressList();
		System.out.println("홍길동 주소 : " + addressList.get("산골")); // get("산골") 이라는 key를 얻는거
		System.out.println("산골 주소 : " + addressList.get("동굴"));
		
	}

}
