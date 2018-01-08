package ifactory.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;


public class PushCallback implements MqttCallback {  
  
	
	MqttChannel mqttchannels;
	
	@Autowired
	public PushCallback()
	{
		mqttchannels = new MqttChannel();
	}
	
    public void connectionLost(Throwable cause) {  
        // 连接丢失后，一般在这里面进行重连  
        System.out.println("连接断开，可以做重连");  
        
//        try {
//			//new ServerMQTT();
//		} catch (MqttException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }  
  
    public void deliveryComplete(IMqttDeliveryToken token) {  
        System.out.println("deliveryComplete---------" + token.isComplete());  
    }  
  
    public void messageArrived(String topic, MqttMessage message) throws Exception {  
        // subscribe后得到的消息会执行到这里面  
        System.out.println("接收消息主题 111: " + topic);  
        System.out.println("接收消息Qos111 : " + message.getQos());  
        System.out.println("接收消息内容 : " + new String(message.getPayload()));  
        String content = new String(message.getPayload());
        
        HandleMessage(content);
    }  
    
    public void HandleMessage(String message){
    	
    	String messagee = null;
    	
    	if(!message.equals("close")){
            
        	String[] ss = message.split("#");
        
        	String result = message.substring(message.indexOf("#")+1, message.length());
        
        	messagee = result.substring(result.indexOf("#")+1, result.length());
        
        	
        	try{
        		String msgg = "{"+"serectId: '"+ss[1]+"',"+"cmd: '"+ss[0]+"', "+"data: '"+messagee+"'"+"}";
        		
        		//将客户端收到的信息序列化成消息对象
        		//发送的message格式为{serectId:'XXXX',cmd:'XXXXX',data:'XXXXXXdata也为一个对象的json格式XXXX'}
        		
        		MqttMessageBase msg =  Gson.class.newInstance().fromJson(msgg, MqttMessageBase.class);
        		mqttchannels.handlerMsg(msg);
        		
        	}catch(Exception e){
        		e.printStackTrace();
        	}
    	}
    	
    }
}