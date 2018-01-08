package ifactory.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.iot.model.v20170620.RegistDeviceRequest;
import com.aliyuncs.iot.model.v20170620.RegistDeviceResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class RegistDeviceService {

	public RegistDeviceResponse RDRsponse(RegistDeviceRequest request)throws Exception {
		String accessKey = "LTAIzItBFhIjZVap";
		String accessSecret = "PBkeUad9zd25b6L9KG50sjcrH9jQyF";
		DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Iot", "iot.cn-shanghai.aliyuncs.com");
		IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, accessSecret);
		DefaultAcsClient client = new DefaultAcsClient(profile);
		request.setProductKey("xxxxxx");
		request.setDeviceName("xxxxx");// 可以设空，如果名称为空则由阿里云生成设备名称默认与设备id一致,设备名称在产品内唯一
		RegistDeviceResponse resp = client.getAcsResponse(request);
		System.out.println(resp.getSuccess());
		System.out.println(resp.getErrorMessage());
		System.out.println(resp.getDeviceSecret());
		System.out.println(resp.getDeviceId());
		System.out.println(resp.getDeviceName());
		return resp;
	}
}
