package ifactory.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.iot.model.v20170620.PubBroadcastRequest;
import com.aliyuncs.iot.model.v20170620.PubBroadcastResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

//发布广播消息
public class PubBroadcastService {
	public static void main(String[] args) {
		try {
			String accessKey = "LTAIzItBFhIjZVap";
			String accessSecret = "PBkeUad9zd25b6L9KG50sjcrH9jQyF";
			DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Iot", "iot.cn-shanghai.aliyuncs.com");
			IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, accessSecret);
			DefaultAcsClient client = new DefaultAcsClient(profile); //初始化SDK客户端
			PubBroadcastRequest request = new PubBroadcastRequest();
			request.setProductKey("NGr7z3T3vnR");
			request.setMessageContent("aGVsbG93b3JsZA=="); //Hello world base64 String
			request.setTopicFullName("/broadcast/NGr7z3T3vnR/data"); //消息发送到的Topic
			PubBroadcastResponse response = client.getAcsResponse(request);
			System.out.println(response.getRequestId()); //当前请求的ID
			System.out.println(response.getSuccess()); //请求是否成功
			System.out.println(response.getErrorMessage());
		} catch (ServerException e) {
			if (e.getErrCode().equals("QueueNotExist"))
            {
                System.out.println("Queue is not exist.Please create queue before use");
            } else if (e.getErrCode().equals("TimeExpired"))
            {
                System.out.println("The request is time expired. Please check your local machine timeclock");
            }
		} catch (ClientException e) {
			System.out.println("Something wrong with the network connection between client and MNS service."
                    + "Please check your network and DNS availablity.");
            e.printStackTrace();
		}
	}
}
