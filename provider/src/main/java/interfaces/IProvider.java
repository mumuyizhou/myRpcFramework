package interfaces;

/**
 * @author ZhouPan
 * @date 2020-08-10
 */
public interface IProvider {
	/**
	 * 暴露服务
	 */
	void expose(Object service, int port);
}
