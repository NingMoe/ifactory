package ifactory.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Collections;

@Configuration
@PropertySource("classpath:database.properties")
public class DataSourceConfig {

    @Value("${driverClassName}")
    private String mysqlDriverClassName;
    @Value("${url}")
    private String url;
    @Value("${dbusername}")
    private String username;
    @Value("${password}")
    private String password;
    @Value("${maxActive}")
    private int maxActive;
    @Value("${initialSize}")
    private int initialSize;
    @Value("${maxWait}")
    private long maxWait;
    @Value("${minIdle}")
    private int minIdle;
    @Value("${timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMills;
    @Value("${testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${testOnReturn}")
    private boolean testOnReturn;
    @Value("${validationQuery}")
    private String validationQuery;
    @Value("${logAbandoned}")
    private boolean logAbandoned;
    @Value("${removeAbandoned}")
    private boolean removeAbandoned;
    @Value("${removeAbandonedTimeout}")
    private int removeAbandonedTimeout;

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(mysqlDriverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(maxActive);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxWait(maxWait);
        dataSource.setMinIdle(minIdle);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMills);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setLogAbandoned(logAbandoned);
        dataSource.setRemoveAbandoned(removeAbandoned);
        dataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        dataSource.setProxyFilters(Collections.singletonList(statFilter()));
        return dataSource;
    }

    private Filter statFilter() {
        StatFilter statFilter = new StatFilter();
        statFilter.setMergeSql(true);
        return statFilter;
    }
}
