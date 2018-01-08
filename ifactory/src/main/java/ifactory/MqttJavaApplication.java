//package ifactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.integration.dsl.IntegrationFlows;
//import org.springframework.integration.endpoint.MessageProducerSupport;
//import org.springframework.integration.handler.LoggingHandler;
//import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
//import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
//import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
//import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
//import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
//import org.springframework.integration.stream.CharacterStreamReadingMessageSource;
//import org.springframework.messaging.MessageHandler;
//
///**
// * Starts the Spring Context and will initialize the Spring Integration message flow.
// *
// * @author Gary Russell
// *
// */
//@SpringBootApplication
//public class MqttJavaApplication {
//
//	private static final Log LOGGER = LogFactory.getLog(MqttJavaApplication.class);
//
//	/**
//	 * Load the Spring Integration Application Context
//	 *
//	 * @param args - command line arguments
//	 */
//	public static void main(final String... args) {
//
//		System.out.println("\n========================================================="
//				  + "\n                                                         "
//				  + "\n          Welcome to Spring Integration!                 "
//				  + "\n                                                         "
//				  + "\n    For more information please visit:                   "
//				  + "\n    https://spring.io/projects/spring-integration        "
//				  + "\n                                                         "
//				  + "\n=========================================================" );
//
//		System.out.println("\n========================================================="
//				  + "\n                                                          "
//				  + "\n    This is the MQTT Sample -                             "
//				  + "\n                                                          "
//				  + "\n    Please enter some text and press return. The entered  "
//				  + "\n    Message will be sent to the configured MQTT topic,    "
//				  + "\n    then again immediately retrieved from the Message     "
//				  + "\n    Broker and ultimately printed to the command line.    "
//				  + "\n                                                          "
//				  + "\n=========================================================" );
//
//		SpringApplication.run(MqttJavaApplication.class, args);
//	}
//
//	@Bean
//	public MqttPahoClientFactory mqttClientFactory() {
//		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
//		factory.setServerURIs("tcp://127.0.0.1:1883");
//		factory.setUserName("username1");
//		factory.setPassword("aaaa");
//		return factory;
//	}
//
//	// publisher
//
////	@Bean
////	public IntegrationFlow mqttOutFlow() {
////		return IntegrationFlows.from(CharacterStreamReadingMessageSource.stdin(),
////						e -> e.poller(Pollers.fixedDelay(1000)))
////				.transform(p -> p + " sent to MQTT")
////				.handle(mqttOutbound())
////				.get();
////	}
////
////	@Bean
////	public MessageHandler mqttOutbound() {
////		MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("siSamplePublisher", mqttClientFactory());
////		messageHandler.setAsync(true);
////		messageHandler.setDefaultTopic("/mqtt/topic/0");
////		return messageHandler;
////	}
//
//	// consumer
//
//	@Bean
//	public IntegrationFlow mqttInFlow() {
//		return IntegrationFlows.from(mqttInbound())
//				.transform(p -> p + ", received from MQTT")
//				.handle(logger())
//				.get();
//	}
//
//	private LoggingHandler logger() {
//		LoggingHandler loggingHandler = new LoggingHandler("INFO");
//		loggingHandler.setLoggerName("siSample");
//		return loggingHandler;
//	}
//
//	@Bean
//	public MessageProducerSupport mqttInbound() {
//		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("ifactoryId",
//				mqttClientFactory(), "/mqtt/topic/0");
//		adapter.setCompletionTimeout(5000);
//		adapter.setConverter(new DefaultPahoMessageConverter());
//		adapter.setQos(1);
//		return adapter;
//	}
//
//}