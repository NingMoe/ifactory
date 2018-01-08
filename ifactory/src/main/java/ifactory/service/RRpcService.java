package ifactory.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.iot.model.v20170420.RRpcRequest;
import com.aliyuncs.iot.model.v20170420.RRpcResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

@Service
//发消息给设备并同步返回响应
public class RRpcService {
	public  RRpcResponse rrResponse(RRpcRequest rrpcRequest) throws Exception{
		String accessKey = "LTAIzItBFhIjZVap";
		String accessSecret = "PBkeUad9zd25b6L9KG50sjcrH9jQyF";
		DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Iot", "iot.cn-shanghai.aliyuncs.com");
		IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, accessSecret);
		DefaultAcsClient client = new DefaultAcsClient(profile); //初始化SDK客户端
		rrpcRequest.setProductKey("NGr7z3T3vnR"); //设备所属产品的Key
		rrpcRequest.setDeviceName("devicetest"); //设备名称
		rrpcRequest.setRequestBase64Byte(Base64.encodeBase64String("{\"action\":unlock}".getBytes())); //发给设备的数据，要求二进制数据做一次Base64编码
		rrpcRequest.setTimeout(1000); //超时时间，单位毫秒，如果超过这个时间设备没反应则返回"TIMEOUT"
		RRpcResponse rrpcResponse = client.getAcsResponse(rrpcRequest); //得到设备返回的数据信息
		System.out.println(rrpcResponse.getPayloadBase64Byte()); //得到的数据是设备返回二进制数据然后再经过Base64编码之后的字符串，需要转换一下才能拿到原始的二进制数据
		System.out.println(rrpcResponse.getRrpcCode()); //对应的响应码(UNKNOW/SUCCESS/TIMEOUT/OFFLINE/HALFCONN等)
		return rrpcResponse;
	}
}
