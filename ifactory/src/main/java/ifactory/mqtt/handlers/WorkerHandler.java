package ifactory.mqtt.handlers;

import com.google.gson.Gson;

import ifactory.module.worker.Worker;
import ifactory.mqtt.MqttMessageBase;

public class WorkerHandler extends MqttMessageHandler {

	@Override
	public void messageHandler(MqttMessageBase msg) throws Exception {
		// TODO Auto-generated method stub
		if(msg.getCmd().equals("Worker"))
		{
			System.out.println("WorkerHandler 收到的消息为 : " + msg.getData());  
			try {
	        	//将客户端收到的信息序列化成消息对象
	        	//发送的message格式为{serectId:'XXXX',cmd:'XXXXX',data:'XXXXXXdata也为一个对象的json格式XXXX'}
				Worker worker =  Gson.class.newInstance().fromJson(msg.getData().toString(), Worker.class);
	    	    
	        	
			} catch (Exception e) {
				System.out.println("接收消息内容111 : " + new String(msg.getData().toString()));
			}
	    }
   }

}
