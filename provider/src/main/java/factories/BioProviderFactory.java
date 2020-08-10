package factories;

import bio.SimpleBioProvider;
import interfaces.IProvider;

/**
 * @author ZhouPan
 * @date 2020-08-10
 */
public class BioProviderFactory implements IProviderFactory {
	@Override
	public IProvider getProvider() {
		return new SimpleBioProvider();
	}
}
