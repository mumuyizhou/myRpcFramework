package services;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZhouPan
 * @date 2020-08-11
 */
public class RpcServices {
	private static Map<String, Object> servicesMap = new ConcurrentHashMap<>();

	public static boolean register(Object service) {
		Class<?>[] interfaces = service.getClass().getInterfaces();

		if (interfaces.length == 0) {
			throw new IllegalArgumentException(service.getClass().getName() + " must implement at least one interface");
		}
		for (Class<?> anInterface : interfaces) {
			servicesMap.put(anInterface.getSimpleName(), service);
		}
		return true;
	}

	public static Object getService(String interfaceName) {
		return servicesMap.get(interfaceName);
	}

	public static Set<Map.Entry<String, Object>> getEntrySet() {
		return servicesMap.entrySet();
	}
}
