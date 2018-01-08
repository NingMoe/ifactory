package ifactory.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.aliyun.oss.OSSClient;


@Configuration
@PropertySource(value = {"classpath:config/aliyun_oss.properties"})
@ComponentScan(basePackages = {"ifactory.config", "ifactory.module","ifactory.mqtt;"})
public class ApplicationConfig {
    @Value("${oss.endpoint}")
    private String ossEndpoint;
    @Value("${oss.accessKeyId}")
    private String ossAccessKeyId;
    @Value("${oss.accessKeySecret}")
    private String ossAccessKeySecret;

    @Bean
    public OSSClient ossClient() {
        return new OSSClient(ossEndpoint, ossAccessKeyId, ossAccessKeySecret);
    }
}
