package ifactory.mqtt.handlers;

import org.springframework.stereotype.Component;

import ifactory.mqtt.MqttMessageBase;

@Component
public class HellowWorldHandler extends MqttMessageHandler {

	@Override
	public void messageHandler(MqttMessageBase msg) throws Exception {
		// TODO Auto-generated method stub
		if(msg.getCmd().equals("Hello"))
		{
		//	System.out.println("HellowWorldHandler 收到的消息为 : " + msg.getData());  
		}
	}
}
