//package ifactory.config;
//
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//import ifactory.mqtt.ServerMQTT;
//
//@Configuration
//@ComponentScan(basePackages = {"ifactory.mqtt;"})
//public class MqttConfig {
//	
//	private String HOST = "tcp://127.0.0.1:1883";  
//	private String TOPIC = "/mqtt/topic/0";  
//	private String clientid = "ifactoryId";  
//    
//    private MqttConnectOptions options;  
//    private String userName = "username1";  
//    private String passWord = "aaaa";  
//	
//	
//	@Bean
//    public ServerMQTT serverMQTT() throws MqttException {
//    	 ServerMQTT client = new ServerMQTT();  
//         client.start();  
//        return client;
//    }
//	
//	
//	@Bean
//    public MqttClient getMqttClinet() {
//		MqttClient client = new MqttClient(HOST, clientid, new MemoryPersistence()); 
//        return client;
//    }
//	
//	@Bean
//    public MqttConnectOptions getMqttConnectOptions() {
//		MqttConnectOptions options = new MqttConnectOptions();  
//		return options;
//    }
//	
//}
