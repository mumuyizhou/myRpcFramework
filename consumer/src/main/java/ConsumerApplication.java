import entity.RpcHost;
import factories.IConsumerFactory;
import factories.NioConsumerFactory;
import interfaces.GoodNight;
import interfaces.HelloService;
import interfaces.IConsumer;

/**
 * 消费者节点启动入口
 *
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
		for(int i = 0; i < 100000; i++){

			System.out.println(hello.saySomething("ok" + i));
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(good.sayGoodNight("z" + i));

		}
		}
}
