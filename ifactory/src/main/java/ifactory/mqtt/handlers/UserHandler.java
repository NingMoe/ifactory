package ifactory.mqtt.handlers;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import ifactory.module.user.User;
import ifactory.module.user.UserExample;
import ifactory.module.user.UserService;
import ifactory.mqtt.MqttMessageBase;

public class UserHandler extends MqttMessageHandler {

	@Autowired
	private UserService userService;
	
	@Override
	public void messageHandler(MqttMessageBase msg) throws Exception {
		// TODO Auto-generated method stub
		if(msg.getCmd().equals("user"))
		{
			System.out.println("HellowWorldHandler 收到的消息为 : " + msg.getData()); 
			//消息处理
	        try {
	        	//将客户端收到的信息序列化成消息对象
	        	//发送的message格式为{serectId:'XXXX',cmd:'XXXXX',data:'XXXXXXdata也为一个对象的json格式XXXX'}
	        	User user =  Gson.class.newInstance().fromJson(msg.getData().toString(), User.class);
	    	    if(userService.checkUserInfo(user)){
	    	    	UserExample condition = new UserExample();
	    	    	condition.or().andUsernameEqualTo(user.getUsername());
	    	    	User userInDatabese = userService.queryUserByCondition(condition);
	    	    	if(user.getPassword().equals(userInDatabese.getPassword())){
	    	    		
	    	    	}else{
	    	    		
	    	    	}
	    	    }
	        	
			} catch (Exception e) {
				System.out.println("接收消息内容111 : " + new String(msg.getData().toString()));
			}
		}
	}
}
