package nio;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import entity.RpcRequest;
import entity.RpcResponse;
import interfaces.IProvider;
import services.RpcServices;

/**
 * @author ZhouPan
 * @date 2020-08-10
 */
public class SimpleNioProvider implements IProvider {
	@Override
	public void expose(Object service, int port) {
		try (Selector selector = Selector.open()) {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress(port));
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			for (; ; ) {
				int readyChannels = selector.selectNow();
				if (readyChannels == 0) continue;
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
				while (keyIterator.hasNext()) {
					SelectionKey key = keyIterator.next();
					if (key.isAcceptable()) {
						ServerSocketChannel channel1 = (ServerSocketChannel) key.channel();
						SocketChannel socketChannel = channel1.accept();
						ByteBuffer buf = ByteBuffer.allocate(2048);
						while (buf.hasRemaining()) {
							socketChannel.read(buf);
							buf.flip();
							String received = new String(buf.array());
							RpcRequest request = JSONObject.parseObject(received, RpcRequest.class);
							System.out.println(received);
							Object service1 = RpcServices.getService(request.getInterfaceName().getSimpleName());
							Object result = null;
							try {
								Method method = service1.getClass().getMethod(request.getMethodName(), request.getParamTypes());
								result = method.invoke(service1, request.getParams());
							} catch (Throwable t) {
								t.printStackTrace();
								result = t;
							} finally {
								byte[] responseBytes = JSONObject.toJSON(
										new RpcResponse(request.getRequestId(), result)).toString().getBytes();
								for (byte responseByte : responseBytes) {
									System.out.print((char) responseByte);
								}
								buf.clear();
								ByteBuffer buf1 = ByteBuffer.allocate(2048);
								buf1.put(responseBytes);
								buf1.flip();
								while (buf1.hasRemaining()) {
									socketChannel.write(buf);
								}
							}

							socketChannel.close();
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
