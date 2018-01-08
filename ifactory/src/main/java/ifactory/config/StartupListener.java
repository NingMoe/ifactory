package ifactory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import ifactory.mqtt.ServerMQTT;

@Service
public class StartupListener implements
           ApplicationListener<ContextRefreshedEvent > {

      @Autowired
      ServerMQTT serverMQTT;

      @Override
      public void onApplicationEvent(ContextRefreshedEvent event) {

           if ( event.getApplicationContext (). getParent() == null) {
               // TODO 这里写下将要初始化的内容
        	   serverMQTT.start();
           }
      }
}
