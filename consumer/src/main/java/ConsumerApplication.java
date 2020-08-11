import factories.IConsumerFactory;
import factories.NioConsumerFactory;
import interfaces.HelloService;
import interfaces.IConsumer;

/**
 * @author ZhouPan
 * @date 2020-08-10
 */
public class ConsumerApplication {
	public static void main(String[] args) {
		IConsumerFactory consumerFactory = new NioConsumerFactory();
		IConsumer consumer = consumerFactory.getConsumer();
		HelloService hello = consumer.refer(HelloService.class, "127.0.0.1", 8999);
		System.out.println(hello.saySomething("ok"));
//		GoodNight goodNight = consumer.refer(GoodNight.class, "127.0.0.1", 8999);
//		System.out.println(goodNight.sayGoodNight("little zhou"));
	}
}
