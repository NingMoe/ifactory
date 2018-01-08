package ifactory.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.iot.model.v20170620.PubRequest;
import com.aliyuncs.iot.model.v20170620.PubResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class PubService {
	public static void main(String... strings) throws Exception{
		String accessKey = "LTAIzItBFhIjZVap";
		String accessSecret = "PBkeUad9zd25b6L9KG50sjcrH9jQyF";
		DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Iot", "iot.cn-shanghai.aliyuncs.com");
		IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, accessSecret);
		DefaultAcsClient client = new DefaultAcsClient(profile);
		PubRequest pub = new PubRequest();
		pub.setProductKey("NGr7z3T3vnR");
		pub.setTopicFullName("/NGr7z3T3vnR/devicetest/get"); //消息发送到的Topic全名.
		pub.setMessageContent("aGVsbG8gd29ybGQ="); //hello world Base64 String.
		pub.setQos(0); //设置Qos为1的话，设备如果不在线，重新上线会收到离线消息，消息最多在IoT套件中保存7天.
		PubResponse response = client.getAcsResponse(pub);
		System.out.println(response.getRequestId());
		System.out.println(response.getSuccess());
		System.out.println(response.getErrorMessage());
		System.out.println(response.getMessageId());
	}

}
