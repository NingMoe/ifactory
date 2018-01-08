package ifactory.module.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import ifactory.module.push.Secsubscription;
import ifactory.module.push.SecsubscriptionExample;
import ifactory.module.push.SecsubscriptionService;
import net.sf.json.JSONObject;

public class SubTest {
	
	@Autowired
	private SecsubscriptionService secsubscriptionService;

	public  PushPayload buildPushObject_all_alias_alert(String registrationId, String message) {

		return new Builder().setPlatform(Platform.all()).setAudience(Audience.registrationId(registrationId)).setMessage(Message.content(message)).build();
	}

	//推送框架
	public  void push(String deviceId, String message) {
		JPushClient jpushClient = new JPushClient("dabe070e2a0293b2fa7f240a", "624aaa38d9d9a2e4b9676893", null,
				ClientConfig.getInstance());
		// For push, all you need do is to build PushPayload object.
		try {
			PushPayload payload = buildPushObject_all_alias_alert(deviceId, message);
			PushResult result = jpushClient.sendPush(payload);
			System.out.println(result);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	public  void test(){
		SecsubscriptionExample example = new SecsubscriptionExample();
		example.or().andSecretKeyEqualTo("140fe1da9eae0bf0b2f");
		List<Secsubscription> subList = secsubscriptionService.query(example);
		for(Secsubscription item : subList){
			push(item.getDeviceId(), JSONObject.fromObject(item).toString());
		}
	}
}
