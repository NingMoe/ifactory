package ifactory.mqtt.handlers;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ifactory.module.statistics.Sectimesum;
import ifactory.mqtt.MqttMessageBase;

//上班下班统计
@Component
public class StatisticsHandler extends MqttMessageHandler {
	
	@Autowired
	private StatisticsService2 statisticsService;
	
	@Override
	public void messageHandler(MqttMessageBase msg) throws Exception {
		// TODO Auto-generated method stub
		if(msg.getCmd().equals("08")||msg.getCmd().equals("14"))
		{
			try {
				Sectimesum secTimeSum = new Sectimesum();
				String data = msg.getData().toString();
				String[] strArry = data.split("#");
				secTimeSum.setRfid(strArry[0]);
				secTimeSum.setRealTime(strArry[1]);
				statisticsService.insert(secTimeSum);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
   }

}
