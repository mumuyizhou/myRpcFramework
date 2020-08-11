import factories.IProviderFactory;
import factories.NioProviderFactory;
import impls.GoodNightImpl;
import impls.HelloServiceImpl;
import interfaces.IProvider;
import services.RpcServices;

/**
 * @author ZhouPan
 * @date 2020-08-10
 */
public class ProviderApplication {

	public static void main(String[] args) {
		RpcServices.register(new HelloServiceImpl());
		RpcServices.register(new GoodNightImpl());
		IProviderFactory factory = new NioProviderFactory();
		IProvider provider = factory.getProvider();
		provider.expose(new HelloServiceImpl(), 8999);
		provider.expose(new GoodNightImpl(), 8999);
	}
}
