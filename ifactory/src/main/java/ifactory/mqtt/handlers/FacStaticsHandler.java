package ifactory.mqtt.handlers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import ifactory.module.facCodeRecode.FacCodeRecodeExample;
import ifactory.module.facCodeRecode.FacCodeRecodeService;
import ifactory.module.test.TestModel;
import ifactory.module.test.TestService;
import ifactory.mqtt.MqttMessageBase;

public class FacStaticsHandler extends MqttMessageHandler{

	private FacCodeRecodeService facCodeRecodeService;
	
	@Autowired
	public FacStaticsHandler()
	{
		facCodeRecodeService = new FacCodeRecodeService();
	}
	
	@Override
	public void messageHandler(MqttMessageBase msg) throws Exception {
		
		if(msg.getCmd().equals("16"))
		{
			FacCodeRecodeExample fre = new 	FacCodeRecodeExample();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			
			String[] fcrDate = msg.getData().split("#"); 
			
			fre.setSecretId(msg.getSerectId());
			
			fre.setJobNumber(fcrDate[0]);
			
			fre.setCodeBar(fcrDate[1]);
			
			fre.setReportTime(sdf.parse(fcrDate[2]));
			
			fre.setCreateTime(new Date());
			
			fre.setStatus(0);
			
			facCodeRecodeService.add(fre);
		}
		
	}

}
