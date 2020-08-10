package interfaces;

/**
 * @author ZhouPan
 * @date 2020-08-10
 */
public interface IConsumer{
	/**
	 * @param interfaceClass 需要引用的接口
	 * @param host           服务提供方ip
	 * @param port           服务提供方主机端口
	 * @return 调用结果
	 */
	<T> T refer(Class<T> interfaceClass, String host, int port);
}
