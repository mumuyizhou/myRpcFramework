package factories;

import interfaces.IProvider;
import nio.SimpleNioProvider;

/**
 * @author ZhouPan
 * @date 2020-08-11
 */
public class NioProviderFactory implements IProviderFactory {
	@Override
	public IProvider getProvider() {
		return new SimpleNioProvider();
	}
}
