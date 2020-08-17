package nio;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import entity.RpcRequest;
import entity.RpcResponse;
import interfaces.IConsumer;

/**
 * @author ZhouPan
 * @date 2020-08-11
 */
public class SimpleNioConsumer implements IConsumer {
	@Override
	@SuppressWarnings("unchecked")
	public <T> T refer(Class<T> interfaceClass, String host, int port) {
		RpcRequest request = new RpcRequest();
		request.setRequestId(UUID.randomUUID().toString());
		request.setInterfaceName(interfaceClass);
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Object result = null;
				request.setMethodName(method.getName());
				Class<?>[] parameterTypes = method.getParameterTypes();
				request.setParamTypes(parameterTypes);
				request.setParams(args);
				try {
					SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(host, port));
					socketChannel.configureBlocking(true);
					ByteBuffer buf = ByteBuffer.allocate(2048);
					buf.put(JSONObject.toJSON(request).toString().getBytes());

					buf.flip();

					while (buf.hasRemaining()) {
						socketChannel.write(buf);
					}

					buf.clear();
					ByteBuffer responseBuf = ByteBuffer.allocate(2048);
					socketChannel.read(responseBuf);
					responseBuf.flip();
					RpcResponse response;
					if (responseBuf.hasRemaining()) {
						String s = new String(responseBuf.array());
						response = JSONObject.parseObject(s, RpcResponse.class);
						if (!request.getRequestId().equals(response.getRequestId())) {
							throw new IllegalArgumentException(
									"返回的id与发出的id不匹配");
						}
						result = response.getResult();
						if (result instanceof Throwable) {
							throw (Throwable) result;
						}
					}
					socketChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return result;
			}
		});
	}
}
