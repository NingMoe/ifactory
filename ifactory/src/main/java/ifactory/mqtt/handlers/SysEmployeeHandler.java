package ifactory.mqtt.handlers;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import ifactory.module.employee.Sysemployee;
import ifactory.module.statistics.StatisticsService;
import ifactory.mqtt.MqttMessageBase;

public class SysEmployeeHandler extends MqttMessageHandler{
	
	@Autowired
	private StatisticsService statisticsService;
	
	@Override
	public void messageHandler(MqttMessageBase msg) throws Exception {
		// TODO Auto-generated method stub
		if(msg.getCmd().equals("employee"))
		{
			System.out.println("TestHandler 收到的消息为 : " + msg.getData()); 
			Sysemployee sysEmployee =  Gson.class.newInstance().fromJson(msg.getData().toString(), Sysemployee.class);
	    }
   }

}
