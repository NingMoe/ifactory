package ifactory.mqtt.handlers;

import java.text.SimpleDateFormat;
import java.util.List;

import org.aspectj.weaver.ast.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ifactory.module.call.Callrecord;
import ifactory.module.push.PushService;
import ifactory.module.push.Secsubscription;
import ifactory.module.push.SecsubscriptionExample;
import ifactory.module.push.SecsubscriptionService;
import ifactory.module.secretary.Secretary;
import ifactory.module.secretary.SecretaryExample;
import ifactory.mqtt.MqttMessageBase;
import net.sf.json.JSONObject;

//呼叫
@Component
public class CallHandler extends MqttMessageHandler{

	@Autowired
	private PushService pushService;
	
	@Autowired
	private SecsubscriptionService secsubscriptionService;
	
	@Autowired
	private CallService callService;
	
	@Autowired
	private SecretaryService2 secretaryService;
	
	@Override
	public void messageHandler(MqttMessageBase msg) throws Exception {
		// TODO Auto-generated method stub
		if(typeFilter(msg))
		{
			System.out.println("SecretaryHandler 收到的消息为 : " + msg.getData());  
			try {
	        	//将客户端收到的信息序列化成消息对象
	        	//发送的message格式为{serectId:'XXXX',cmd:'XXXXX',data:'XXXXXXdata也为一个对象的json格式XXXX'}
				Callrecord callCmd =  new Callrecord();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				String data = msg.getData().toString();
				String[] strArry = data.split("#");
				callCmd.setRfid(strArry[0]);
				callCmd.setCallTime(df.parse(strArry[1]));
				callCmd.setCallType(msg.getCmd());
				callCmd.setSecretKey(msg.getSerectId());
				callService.insert(callCmd);

				//故障推送到手机端
				SecsubscriptionExample example = new SecsubscriptionExample();
				example.or().andSecretKeyEqualTo(msg.getSerectId());
				List<Secsubscription> subList = secsubscriptionService.query(example);
				for(Secsubscription item : subList){
					if(item.getType().contains(callCmd.getCallType())&&"02,03,05,06".contains(callCmd.getCallType())){
						item.setType(callCmd.getCallType());
						pushService.push(item.getDeviceId(), JSONObject.fromObject(item).toString());
					}
				}
				
				SecretaryExample condition = new SecretaryExample();
				condition.or().andSecretKeyEqualTo(msg.getSerectId());
				List<Secretary> list = secretaryService.query(condition);
				for(Secretary item:list){
					item.setState(msg.getCmd());
					secretaryService.equidUpdate(item);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("接收消息内容111 : " + new String(msg.getData().toString()));
			}
	    }
	}
	
	private boolean typeFilter(MqttMessageBase msg){
		String typeSum = "02,03,05,06,09,10,12,13";
		if(typeSum.indexOf(msg.getCmd())>-1){
			return true;
		}else{
			return false;
		}
	}
}
