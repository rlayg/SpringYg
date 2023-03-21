package sam07;

public class MessageBeanImpl implements MessageBean {
	
	private String name;
	private String greet;
	private Outputter outputter; //property에서 value를 ref로 해야해 콜바이레퍼런스, 참조변수
	
	public void sayHello() {
		String msg = name + "님!! " + greet;
		System.out.println(msg);
//		FileOutputter에 메시지 저장하겟다는거
		if(outputter != null) outputter.output(msg);
		
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGreet(String greet) {
		this.greet = greet;
	}

	public void setOutputter(Outputter outputter) {
		this.outputter = outputter;
	}
	
	

}
