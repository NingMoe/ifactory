package ifactory.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20170620.QueryApplyStatusRequest;
import com.aliyuncs.iot.model.v20170620.QueryApplyStatusResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

//查询批量设备的申请状态
public class QueryApplyStatusService {
	public static void main(String... strings) throws Exception{
		String accessKey = "LTAIzItBFhIjZVap";
		String accessSecret = "PBkeUad9zd25b6L9KG50sjcrH9jQyF";
		DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Iot", "iot.cn-shanghai.aliyuncs.com");
		IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, accessSecret);
		DefaultAcsClient client = new DefaultAcsClient(profile);
		QueryApplyStatusRequest request = new QueryApplyStatusRequest();
		request.setApplyId(64L);//有错
		QueryApplyStatusResponse response =  null;
		  try {
			  response = client.getAcsResponse(request);
		  } catch (ClientException e) {
		      e.printStackTrace();
		  }
		if(response != null){
		System.out.println("Response requestId:"+response.getRequestId()+" isSuccess:"+response.getSuccess() +" Error:"+response.getErrorMessage());
		}
	}
}
