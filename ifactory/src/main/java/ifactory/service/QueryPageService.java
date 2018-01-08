package ifactory.service;

import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.iot.model.v20170620.QueryPageByApplyIdRequest;
import com.aliyuncs.iot.model.v20170620.QueryPageByApplyIdResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

//查询批量生成的设备信息
public class QueryPageService {
	public static void main(String... strings) throws Exception{
		String accessKey = "LTAIzItBFhIjZVap";
		String accessSecret = "PBkeUad9zd25b6L9KG50sjcrH9jQyF";
		DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Iot", "iot.cn-shanghai.aliyuncs.com");
		IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, accessSecret);
		DefaultAcsClient client = new DefaultAcsClient(profile); //初始化SDK客户端
		QueryPageByApplyIdRequest request = new QueryPageByApplyIdRequest();
		request.setApplyId(1L);
		request.setCurrentPage(1);
		request.setPageSize(10);
		QueryPageByApplyIdResponse response =  null;
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
