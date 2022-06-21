package br.com.utilities.utils;

public class Hosts {

	public String hostName;

	public String hostAddress;

	public Hosts(String hostName, String hostAddress) {
		this.hostName = hostName;
		this.hostAddress = hostAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostAddress() {
		return hostAddress;
	}

	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}

}