package ifactory.mqtt.handlers;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ifactory.module.report.Secclockrecord;
import ifactory.module.secretary.Secretary;
import ifactory.module.secretary.SecretaryExample;
import ifactory.mqtt.MqttMessageBase;

@Component
public class ReportHandler extends MqttMessageHandler {

	@Autowired
	private ReportService2 reportService;

	@Autowired
	private SecretaryService2 secretaryService;
	
	@Override
	public void messageHandler(MqttMessageBase msg) throws Exception {
		// TODO Auto-generated method stub
		//问题上报保存到数据库
		if (typeFilter(msg)) {
			System.out.println("TestHandler 收到的消息为 : " + msg.getData());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			Secclockrecord record = new Secclockrecord();
			String data = msg.getData().toString();
			String[] strArry = data.split("#");
			record.setRfid(strArry[0]);
			record.setsTime(df.parse(strArry[1]));
			record.setCompanycode("1111");
			record.setCmd(msg.getCmd());
			record.setSerectId(msg.getSerectId());
			if(strArry.length>2){
				record.seteTime(df.parse(strArry[2]));
			}
			SecretaryExample condition = new SecretaryExample();
			
			condition.or().andSecretKeyEqualTo(msg.getSerectId());
			List<Secretary> list = secretaryService.query(condition);
			String companyCode = "";
			if (list != null && list.size() > 0) {
				companyCode = list.get(0).getCompanyCode();
				record.setCompanycode(companyCode);
				list.get(0).setState(msg.getCmd());
				secretaryService.equidUpdate(list.get(0));
			}
			reportService.insert(record);
		}
	}

	private boolean typeFilter(MqttMessageBase msg){
		String typeSum = "01,04,07,11";
		if(typeSum.indexOf(msg.getCmd())>-1){
			return true;
		}else{
			return false;
		}
	}
}
