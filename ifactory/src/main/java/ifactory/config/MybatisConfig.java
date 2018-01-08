package ifactory.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = {"ifactory.module.**"})
public class MybatisConfig {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SqlSessionFactory sessionFactory() throws Exception {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("mybatis.properties"));
        factoryBean.setConfigurationProperties(propertiesFactoryBean.getObject());
        factoryBean.setTypeAliasesPackage("ifactory.module");
        factoryBean.setPlugins(new Interceptor[]{pageHelper()});
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:Mapper/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("dialect", "mysql");
        pageHelper.setProperties(props);
        return pageHelper;
    }
}
