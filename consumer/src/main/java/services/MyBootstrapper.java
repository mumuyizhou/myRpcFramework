package services;

import annotations.Boot;
import annotations.MyReference;
import interfaces.HelloService;

/**
 * 实际上的启动类
 *
 * @author ZhouPan
 * @date 2020-08-17
 */
@Boot
public class MyBootstrapper {
	@MyReference
	public HelloService helloService;

	public void doMain(){
		helloService.saySomething("good");
	}
}
