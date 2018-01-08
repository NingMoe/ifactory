package ifactory.mqtt;

import java.io.Serializable;
import java.util.Date;

public class MqttMessageBase implements Serializable {

	//终端唯一标识
	String serectId;
	//指令
	String cmd;
	
	String data;
	
	public String getSerectId() {
		return serectId;
	}

	public void setSerectId(String serectId) {
		this.serectId = serectId;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
	
}
