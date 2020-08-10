package bio;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.Socket;

import com.sun.tools.javac.util.Assert;
import interfaces.IConsumer;

/**
 * @author ZhouPan
 * @date 2020-08-10
 */
public class SimpleSocketReferer implements IConsumer {
	@Override
	@SuppressWarnings("unchecked")
	public <T> T refer(Class<T> interfaceClass, String host, int port) {
		Assert.checkNonNull(interfaceClass, "interfaceClass can not be null");
		Assert.check(interfaceClass.isInterface(), interfaceClass.getName() + "must be an interface");
		Assert.check(port > 0 && port <= 65535, "port:{" + port + "} is not valid");
		Assert.check(host != null && host.length() != 0, "host ip:{" + host + "} is not valid");
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
				new Class<?>[]{interfaceClass}, (proxy, method, args) -> {
					try (Socket socket = new Socket(host, port)) {
						try (ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {
							output.writeUTF(method.getName());
							output.writeObject(args);
							output.writeObject(method.getParameterTypes());
							try (ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
								Object result = input.readObject();
								if (result instanceof Throwable) {
									throw (Throwable) result;
								}
								return result;
							}
						}
					}
				});
	}


}
