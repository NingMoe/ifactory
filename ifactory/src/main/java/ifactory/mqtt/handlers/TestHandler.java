package ifactory.mqtt.handlers;

import ifactory.mqtt.MqttMessageBase;

public class TestHandler extends MqttMessageHandler {

	@Override
	public void messageHandler(MqttMessageBase msg) throws Exception {
		// TODO Auto-generated method stub
		if(msg.getCmd().equals("device"))
		{
			System.out.println("TestHandler 收到的消息为 : " + msg.getData());  
	    }
   }

}
