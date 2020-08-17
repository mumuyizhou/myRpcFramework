package entity;

/**
 * @author ZhouPan
 * @date 2020-08-17
 */
public class RpcHost {
	private String ip;
	private int port;

	public RpcHost() {
	}

	public RpcHost(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
