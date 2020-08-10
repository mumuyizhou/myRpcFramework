import factories.BioProviderFactory;
import factories.IProviderFactory;
import impls.GoodNightImpl;
import impls.HelloServiceImpl;
import interfaces.IProvider;

/**
 * @author ZhouPan
 * @date 2020-08-10
 */
public class ProviderApplication {
	public static void main(String[] args) {
		IProviderFactory factory = new BioProviderFactory();
		IProvider provider = factory.getProvider();
		provider.expose(new HelloServiceImpl(), 8999);
		provider.expose(new GoodNightImpl(),9000);
	}
}
