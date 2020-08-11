package bio;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.tools.javac.util.Assert;
import interfaces.IProvider;

/**
 * @author ZhouPan
 * @date 2020-08-10
 */
public class SimpleBioProvider implements IProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleBioProvider.class);

	@Override
	public void expose(Object service, int port) {
		Assert.checkNonNull(service, "service is null");
		if (port <= 0 || port >= 65536) {
			throw new IllegalArgumentException("port is not valid");
		}
		LOGGER.info("service: {} starting.", service.getClass().getName());
		try (ServerSocket socket = new ServerSocket(port)) {
			for (; ; ) {
				Socket accept = socket.accept();
				new Thread(() -> {
					try (ObjectInputStream objectInput = new ObjectInputStream(accept.getInputStream())) {
						String methodName = objectInput.readUTF();
						Object[] arguments = (Object[]) objectInput.readObject();
						Class<?>[] parameterTypes = (Class<?>[]) objectInput.readObject();
						ObjectOutputStream output = new ObjectOutputStream(accept.getOutputStream());
						try {
							Method method = service.getClass().getMethod(methodName, parameterTypes);
							Object result = method.invoke(service, arguments);
							output.writeObject(result);
						} catch (Throwable t) {
							output.writeObject(t);
						} finally {
							output.close();
						}
					} catch (IOException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				}).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
