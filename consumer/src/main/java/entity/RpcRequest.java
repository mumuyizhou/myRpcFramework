package entity;

import java.util.Arrays;

/**
 * @author ZhouPan
 * @date 2020-08-11
 */

public class RpcRequest {
	private String requestId;
	private int serverVersion;
	private Class<?> interfaceName;
	private String methodName;
	private Object[] params;
	private Class<?>[] paramTypes;

	public Class<?> getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(Class<?> interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public int getServerVersion() {
		return serverVersion;
	}

	public void setServerVersion(int serverVersion) {
		this.serverVersion = serverVersion;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public Class<?>[] getParamTypes() {
		return paramTypes;
	}

	public void setParamTypes(Class<?>[] paramTypes) {
		this.paramTypes = paramTypes;
	}

	@Override
	public String toString() {
		return "RpcRequest{" +
				"requestId='" + requestId + '\'' +
				", serverVersion=" + serverVersion +
				", interfaceName=" + interfaceName +
				", methodName='" + methodName + '\'' +
				", params=" + Arrays.toString(params) +
				", paramTypes=" + Arrays.toString(paramTypes) +
				'}';
	}
}
