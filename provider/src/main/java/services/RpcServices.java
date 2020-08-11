package services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZhouPan
 * @date 2020-08-11
 */
public class RpcServices {
	private static Map<String, Object> servicesMap = new ConcurrentHashMap<>();

	public static boolean register(Object service){
		servicesMap.put(service.getClass().getName(), service);
		return true;
	}

	public static Object getService(String interfaceName){
		return servicesMap.get(interfaceName);
	}
}
