package ifactory.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class MqttAnswer  {
	
	//tcp://MQTT安装的服务器地址:MQTT定义的端口号  
    public static final String HOST = "tcp://172.20.70.59:1883";  
    //定义MQTT的ID，可以在MQTT服务配置中指定  
    private static final String clientid = "ifactoryId1";  
    
    private String userName = "username1";  
    
    private String passWord = "aaaa";  
  
    private MqttClient client;  
    
    private MqttTopic topic11; 
    
    private MqttMessage message; 
    
    public MqttAnswer(String topic, String data) throws MqttException {  
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存  
        client = new MqttClient(HOST, clientid, new MemoryPersistence());  
        connect(topic);  
    }
    
    public void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,  
    MqttException {  
    	MqttDeliveryToken token = topic.publish(message);  
    	token.waitForCompletion();  
    	System.out.println("message is published completely! "  
        + token.isComplete());  
    }  
    
    private void connect(String topic) {  
    	MqttConnectOptions options = new MqttConnectOptions();  
    	options.setCleanSession(false);  
        options.setUserName(userName);  
        options.setPassword(passWord.toCharArray());  
        // 设置超时时间  
        options.setConnectionTimeout(10);  
        // 设置会话心跳时间  
        options.setKeepAliveInterval(20);  
        
        
        try {  
            options.setCleanSession(false);  
            
            client.connect(options);    
            
            topic11 = client.getTopic(topic);  
            
            sendMessage();
    
        } catch (MqttException me) {  
        	System. out .println( "reason " + me.getReasonCode());  
            System. out .println( "msg " + me.getMessage());  
            System. out .println( "loc " + me.getLocalizedMessage());  
            System. out .println( "cause " + me.getCause());  
            System. out .println( "excep " + me);  
            me.printStackTrace();
        }  
    } 
    
    public void sendMessage(){
    	message = new MqttMessage();  
        message.setQos(1);  
        message.setRetained(true);  
        message.setPayload("ceshijkgdfjjjjjjjjwewqerweqrwqerrrrrrrrrrwerqererewrerrewewewrerererererererererererererrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrjrwqrwqerwqerrrrrrrrrrrrrrrrrrrrrrrrrrrrrjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjdkgkdjgksdjgkdgdjfgkdogjksdfjgkdjsfgjsdjgjdfgjdgdgjdkfjgkldjgkldklgdgldgkldgdjgjdkgjdkjgdfgjd".getBytes());  
        try {
			publish(topic11 , message);
		} catch (MqttPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
    }

}