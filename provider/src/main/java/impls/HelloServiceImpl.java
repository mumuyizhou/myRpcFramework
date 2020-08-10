package impls;

import interfaces.HelloService;

/**
 * @author ZhouPan
 * @date 2020-07-07
 */
public class HelloServiceImpl implements HelloService {
	@Override
	public String saySomething(String message) {
		return "returned:" + message;
	}
}
