package entity;

/**
 * @author ZhouPan
 * @date 2020-08-11
 */
public class RpcResponse {
	private String requestId;
	private Object result;

	public RpcResponse(String requestId, Object result) {
		this.requestId = requestId;
		this.result = result;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
