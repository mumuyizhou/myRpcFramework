import entity.RpcHost;
import factories.IConsumerFactory;
import factories.NioConsumerFactory;
import interfaces.GoodNight;
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
		RpcHost host = new RpcHost("127.0.0.1", 8999);
		HelloService hello = consumer.refer(HelloService.class, "127.0.0.1", 8999);
		GoodNight good = consumer.refer(GoodNight.class, "127.0.0.1", 8999);
		System.out.println(hello.saySomething("ok"));
		System.out.println(good.sayGoodNight("z"));
	}
}
