package ifactory.mqtt.handlers;

import ifactory.mqtt.MqttMessageBase;

public abstract class MqttMessageHandler {

//	protected String cmdType;
//	
//	public  void handlerRead(MqttMessageBase msg) throws Exception {        
//            if (msg.cmd.equals(cmdType)) {
//            	messageHandler(msg);
//            }
//    }
	
	public abstract void messageHandler(MqttMessageBase msg) throws Exception;
	
}
