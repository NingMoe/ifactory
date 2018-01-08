package ifactory.mqtt.handlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import ifactory.module.secretary.Secretary;
import ifactory.module.secretary.SecretaryExample;
import ifactory.module.secretary.SecretaryService;
import ifactory.mqtt.MqttMessageBase;

public class SecretaryHandler extends MqttMessageHandler {

	@Autowired
	private SecretaryService secretaryService;
	
	@Override
	public void messageHandler(MqttMessageBase msg) throws Exception {
		// TODO Auto-generated method stub
		if(msg.getCmd().equals("device"))
		{
			System.out.println("SecretaryHandler 收到的消息为 : " + msg.getData());  
			try {
	        	//将客户端收到的信息序列化成消息对象
	        	//发送的message格式为{serectId:'XXXX',cmd:'XXXXX',data:'XXXXXXdata也为一个对象的json格式XXXX'}
				Secretary secretary =  Gson.class.newInstance().fromJson(msg.getData().toString(), Secretary.class);
				SecretaryExample condition = new SecretaryExample();
				condition.or().andDeviceIdEqualTo(secretary.getDeviceId());
				List<Secretary> list = secretaryService.query(condition);
				if(list!=null&&list.size()>0){
					//设备信息修改
					secretaryService.equidUpdate(secretary);
				}else{
					//设备注册
					secretaryService.insert(secretary);
				}
			} catch (Exception e) {
				System.out.println("接收消息内容111 : " + new String(msg.getData().toString()));
			}
	    }
   }

}
