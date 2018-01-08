package ifactory.module.worker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.iot.model.v20170620.RegistDeviceRequest;
import com.aliyuncs.iot.model.v20170620.RegistDeviceResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

@Service
public class WorkerService {

	@Autowired
	private WorkerMapper workMapper;

//	private static long shadowVersion = 1;

	public List<Worker> query(WorkerExample condition) {
		return workMapper.selectByExample(condition);
	}

	public Integer insert(Worker worker) {
		return workMapper.insert(worker);
	}
	// 设备注册
	@Transactional
	public String signIn(String productKey, String deviceName) {
		String secretKey = "";
		return secretKey;
	}

	public static String registDeviceTest(String productKey, String deviceName) {
		RegistDeviceRequest request = new RegistDeviceRequest();
		request.setProductKey(productKey);
		request.setDeviceName(deviceName);
		RegistDeviceResponse response = (RegistDeviceResponse) executeTest(request);
		if (response != null && response.getSuccess() != false) {
			System.out.println(
					"创建设备成功！deviceName:" + response.getDeviceName() + ",deviceSecret:" + response.getDeviceSecret());
			return response.getDeviceName();
		} else {
			System.out.println("创建设备失败！requestId:" + response.getRequestId() + "原因：" + response.getErrorMessage());
		}
		return null;
	}

	public static AcsResponse executeTest(RegistDeviceRequest request) {
		AcsResponse response = null;
		try {
			String accessKey = "LTAIzItBFhIjZVap";
			String accessSecret = "PBkeUad9zd25b6L9KG50sjcrH9jQyF";
			DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Iot", "iot.cn-shanghai.aliyuncs.com");
			IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, accessSecret);
			DefaultAcsClient client = new DefaultAcsClient(profile);
			response = client.getAcsResponse(request);
		} catch (Exception e) {
			System.out.println("执行失败：e:" + e.getMessage());
		}
		return response;
	}
}
