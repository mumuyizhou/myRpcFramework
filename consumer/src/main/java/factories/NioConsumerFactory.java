package factories;

import interfaces.IConsumer;
import nio.SimpleNioConsumer;

/**
 * @author ZhouPan
 * @date 2020-08-11
 */
public class NioConsumerFactory implements IConsumerFactory {
	@Override
	public IConsumer getConsumer() {
		return new SimpleNioConsumer();
	}
}
