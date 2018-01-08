package ifactory.service;

import java.util.ArrayList;
import java.util.List;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20170620.BatchGetDeviceStateRequest;
import com.aliyuncs.iot.model.v20170620.BatchGetDeviceStateResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
//批量获取设备状态
public class BatchGetDeviceStateService {
	public static void main(String... strings) throws Exception{
		String accessKey = "LTAIzItBFhIjZVap";
		String accessSecret = "PBkeUad9zd25b6L9KG50sjcrH9jQyF";
		DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Iot", "iot.cn-shanghai.aliyuncs.com");
		IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, accessSecret);
		DefaultAcsClient client = new DefaultAcsClient(profile);
		BatchGetDeviceStateRequest request = new BatchGetDeviceStateRequest();
		request.setProductKey("NGr7z3T3vnR");
		List<String> devices = new ArrayList<String>();
		devices.add("DVES_00F5EAB7");
		devices.add("DVES_00F5EAB5");
		devices.add("device001");
		devices.add("devicetest");
		request.setDeviceNames(devices);
		BatchGetDeviceStateResponse response =  null;
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
