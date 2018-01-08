package ifactory.mqtt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ifactory.mqtt.handlers.FacStaticsHandler;
import ifactory.mqtt.handlers.HellowWorldHandler;
import ifactory.mqtt.handlers.MqttMessageHandler;
import ifactory.mqtt.handlers.ReportHandler;
import ifactory.mqtt.handlers.SecretaryHandler;
import ifactory.mqtt.handlers.StatisticsHandler;
import ifactory.mqtt.handlers.TestHandler;

import ifactory.mqtt.handlers.UserHandler;

@Component
public class MqttChannel {
	
	@Autowired
	FacStaticsHandler facStaticsHandler;
	
	@Autowired
	ReportHandler reportHandler;

	@Autowired
	private List<MqttMessageHandler> handlers;
	
	@Autowired
	public MqttChannel()
	{		
		initializer();
	}
	
	
	public void handlerMsg(MqttMessageBase msg)
	{
		for (MqttMessageHandler mqttMessageHandler : handlers) {
			try {
				mqttMessageHandler.messageHandler(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
    public void initializer() {        
        handlers = new ArrayList<>();
        handlers.add(facStaticsHandler);  
//        handlers.add(new TestHandler());  
//        handlers.add(new HellowWorldHandler());
//        handlers.add(new HellowWorldHandler());  
//        handlers.add(new UserHandler());
//        handlers.add(new SecretaryHandler());
        handlers.add(new StatisticsHandler());
        handlers.add(reportHandler);
        System.out.println("MqttChannel 开始加载");  
    }
}
