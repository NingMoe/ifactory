package ifactory.mqtt.handlers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.iot.model.v20170620.RegistDeviceRequest;
import com.aliyuncs.iot.model.v20170620.RegistDeviceResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import ifactory.module.employee.Sysemployee;
import ifactory.module.employee.SysemployeeExample;
import ifactory.module.employee.SysemployeeMapper;
import ifactory.module.secretary.Secretary;
import ifactory.module.secretary.SecretaryExample;
import ifactory.module.secretary.SecretaryMapper;

@Service
public class SecretaryService2 {
	
	@Autowired
	public SecretaryMapper secretaryMapper;
	
	@Autowired
	public SysemployeeMapper sysemployeeMapper;
	
	//初始化IOT平台链接
	public static AcsResponse execute(RegistDeviceRequest request) {
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
	
	//IOT平台·设备注册接口
	public static String registDevice(String productKey, String deviceName) {
		RegistDeviceRequest request = new RegistDeviceRequest();
		request.setProductKey(productKey);
		request.setDeviceName(deviceName);
		RegistDeviceResponse response = (RegistDeviceResponse) execute(request);
		if (response != null && response.getSuccess() != false) {
			System.out.println(
					"创建设备成功！deviceName:" + response.getDeviceName() + ",deviceSecret:" + response.getDeviceSecret());
			return response.getDeviceSecret();
		} else {
			System.out.println("创建设备失败！requestId:" + response.getRequestId() + "原因：" + response.getErrorMessage());
		}
		return null;
	}
	
	//设备注册
	public String registDevice(Secretary sec){
//		registDevice(sec.get)
		String result = "";
		secretaryMapper.insert(sec);
		return result;
	}
	
	//终端信息修改
	public void equidUpdate(Secretary secretary){
		if(secretary!=null){
			secretaryMapper.updateByPrimaryKeySelective(secretary);
		}
	}
	
	//返回系统当前时间
	public String getTime(){
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return df.format(day).toString(); 
	}
	
	//获取工号姓名信息
	public Sysemployee getEmployeeInfo(String deviceId,String rfid){
		SecretaryExample condition = new SecretaryExample();
		Secretary sec = new Secretary();
		condition.or().andDeviceIdEqualTo(deviceId);
		List<Secretary> secretaryList = secretaryMapper.selectByExample(condition);
		if(secretaryList!=null&&secretaryList.size()>0){
			sec = secretaryList.get(0);
		}else{
			return null;
		}
		SysemployeeExample example = new SysemployeeExample();
		example.or().andRfidEqualTo(rfid).andCompanycodeEqualTo(sec.getCompanyCode());
		List<Sysemployee> list = sysemployeeMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public List<Secretary> query(SecretaryExample condition){
		return secretaryMapper.selectByExample(condition);
	}
	
	public Integer insert(Secretary secretary){
		return secretaryMapper.insert(secretary);
	}
	
	/*
	 * 模糊查询
	 * */
	public List<Secretary> queryByCondition(SecretaryExample condition){
		return secretaryMapper.selectByExample(condition);
	}
}
