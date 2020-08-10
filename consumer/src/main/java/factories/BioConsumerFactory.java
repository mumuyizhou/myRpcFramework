package factories;


import bio.SimpleSocketReferer;
import interfaces.IConsumer;

/**
 * @author ZhouPan
 * @date 2020-08-10
 */
public class BioConsumerFactory implements IConsumerFactory {
	@Override
	public IConsumer getConsumer() {
		return new SimpleSocketReferer();
	}
}
