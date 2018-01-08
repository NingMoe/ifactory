package ifactory.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20170620.QueryDeviceByNameRequest;
import com.aliyuncs.iot.model.v20170620.QueryDeviceByNameResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

//根据设备名称查询设备信息
public class QueryDeviceByNameService {
	public static void main(String... strings) throws Exception{
		String accessKey = "LTAIzItBFhIjZVap";
		String accessSecret = "PBkeUad9zd25b6L9KG50sjcrH9jQyF";
		DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Iot", "iot.cn-shanghai.aliyuncs.com");
		IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, accessSecret);
		DefaultAcsClient client = new DefaultAcsClient(profile);
		QueryDeviceByNameRequest request = new QueryDeviceByNameRequest();
		request.setProductKey("NGr7z3T3vnR");
		request.setDeviceName("devicetest");
		QueryDeviceByNameResponse response =  null;
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
